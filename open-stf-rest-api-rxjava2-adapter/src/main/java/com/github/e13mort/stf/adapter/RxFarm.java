package com.github.e13mort.stf.adapter;

import com.github.e13mort.stf.api.DevicesApi;
import com.github.e13mort.stf.api.UserApi;
import com.github.e13mort.stf.model.AddUserDevicePayload;
import com.github.e13mort.stf.model.DeviceListResponse;
import com.github.e13mort.stf.model.DeviceResponse;
import com.github.e13mort.stf.model.RemoteConnectUserDeviceResponse;
import com.github.e13mort.stf.model.device.Device;
import io.reactivex.*;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.operators.maybe.MaybeError;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

public class RxFarm {
    private static final String DEVICES_FIELDS = "";
    private final DevicesApi devicesApi;
    private final UserApi userApi;

    public RxFarm(DevicesApi devicesApi, UserApi userApi) {
        this.devicesApi = devicesApi;
        this.userApi = userApi;
    }

    public Single<Device> getDevice(final String serial) {
        return Single.create(new SingleOnSubscribe<Device>() {
            @Override
            public void subscribe(SingleEmitter<Device> emitter) throws Exception {
                Response<DeviceResponse> response = devicesApi.getDeviceBySerial(serial, DEVICES_FIELDS).execute();
                if (!response.isSuccessful()) {
                    emitter.onError(new ResponseException(response));
                } else {
                    emitter.onSuccess(response.body().getDevice());
                }
            }
        });
    }

    public Flowable<Device> getAllDevices() {
        return Flowable.create(new FlowableOnSubscribe<Device>() {
            @Override
            public void subscribe(final FlowableEmitter<Device> emitter) throws Exception {
                Response<DeviceListResponse> response = devicesApi
                        .getDevices(DEVICES_FIELDS)
                        .execute();
                wrapDevices(emitter, response);
            }
        }, BackpressureStrategy.BUFFER);
    }

    public Flowable<Device> getConnectedDevices() {
        return Flowable.create(new FlowableOnSubscribe<Device>() {
            @Override
            public void subscribe(FlowableEmitter<Device> e) throws Exception {
                Response<DeviceListResponse> response = userApi
                        .getUserDevices(DEVICES_FIELDS)
                        .execute();
                wrapDevices(e, response);
            }
        }, BackpressureStrategy.BUFFER);
    }

    public Single<String> connect(final String serial, final int timeout) {
        return from(serial)
                .doOnEvent(addUserToDevice(serial, timeout))
                .map(connectToDevice());
    }

    public Single<String> disconnect(final String serial) {
        return from(serial)
                .doOnEvent(disconnectFromDevice())
                .doOnEvent(deleteDevice());
    }

    private void wrapDevices(FlowableEmitter<Device> emitter, Response<DeviceListResponse> response) throws IOException {
        if (!response.isSuccessful()) {
            emitter.onError(new Exception(response.errorBody().string()));
        } else {
            List<Device> devices = response.body().getDevices();
            for (Device device : devices) {
                if (!emitter.isCancelled()) {
                    emitter.onNext(device);
                }
            }
            emitter.onComplete();
        }
    }

    private BiConsumer<String, Throwable> deleteDevice() {
        return new BiConsumer<String, Throwable>() {
            @Override
            public void accept(@NonNull String s, @NonNull Throwable throwable) throws Exception {
                Response<Object> response = userApi.deleteUserDeviceBySerial(s).execute();
                if (!response.isSuccessful()) {
                    throw new ResponseException(response.code(), response.message());
                }
            }
        };
    }

    private BiConsumer<String, Throwable> disconnectFromDevice() {
        return new BiConsumer<String, Throwable>() {
            @Override
            public void accept(@NonNull String s, @NonNull Throwable throwable) throws Exception {
                Response<Object> response = userApi.remoteDisconnectUserDeviceBySerial(s).execute();
                if (!response.isSuccessful()) {
                    throw new ResponseException(response.code(), response.message());
                }
            }
        };
    }

    private Single<String> from(String serial) {
        return Single.just(serial)
                .filter(new Predicate<String>() {
                    @Override
                    public boolean test(@NonNull String s) throws Exception {
                        return s != null;
                    }
                })
                .switchIfEmpty(new MaybeError<String>(new NullPointerException("Serial is null")))
                .toSingle();
    }

    private DeviceConnectFunction connectToDevice() {
        return new DeviceConnectFunction();
    }

    private BiConsumer<String, Throwable> addUserToDevice(final String serial, final int timeout) {
        return new BiConsumer<String, Throwable>() {
            @Override
            public void accept(@NonNull String s, @NonNull Throwable throwable) throws Exception {
                AddUserDevicePayload payload = new AddUserDevicePayload();
                payload.setSerial(serial);
                payload.setTimeout(timeout);
                Response<Object> response = userApi.addUserDevice(payload).execute();
                if (!response.isSuccessful()) {
                    throw new ResponseException(response);
                }
            }
        };
    }

    public class DeviceConnectFunction implements Function<String, String> {
        @Override
        public String apply(@NonNull String serial) throws Exception {
            Response<RemoteConnectUserDeviceResponse> response = userApi.remoteConnectUserDeviceBySerial(serial).execute();
            if (response.isSuccessful()) {
                if (response.body() != null && response.body().getRemoteConnectUrl() != null) {
                    return response.body().getRemoteConnectUrl();
                } else {
                    throw new IllegalArgumentException("Connect was successful but response is invalid");
                }
            } else {
                throw new ResponseException(response);
            }
        }
    }
}
