package com.github.e13mort.stf.adapter;

import com.github.e13mort.stf.api.DevicesApi;
import com.github.e13mort.stf.api.UserApi;
import com.github.e13mort.stf.model.AddUserDevicePayload;
import com.github.e13mort.stf.model.DeviceListResponse;
import com.github.e13mort.stf.model.DeviceResponse;
import com.github.e13mort.stf.model.RemoteConnectUserDeviceResponse;
import com.github.e13mort.stf.model.device.Device;

import java.io.IOException;
import java.util.List;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiConsumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.operators.maybe.MaybeError;
import retrofit2.Response;

@SuppressWarnings("WeakerAccess")
public class RxFarm {
    private static final String DEVICES_FIELDS = "";
    private final DevicesApi devicesApi;
    private final UserApi userApi;

    public RxFarm(DevicesApi devicesApi, UserApi userApi) {
        this.devicesApi = devicesApi;
        this.userApi = userApi;
    }

    public Single<Device> getDevice(final String serial) {
        return Single.create(emitter -> {
            Response<DeviceResponse> response = devicesApi.getDeviceBySerial(serial, DEVICES_FIELDS).execute();
            if (!response.isSuccessful()) {
                emitter.onError(new ResponseException(response));
            } else {
                emitter.onSuccess(response.body().getDevice());
            }
        });
    }

    public Flowable<Device> getAllDevices() {
        return Flowable.create(emitter -> {
            Response<DeviceListResponse> response = devicesApi
                    .getDevices(DEVICES_FIELDS)
                    .execute();
            wrapDevices(emitter, response);
        }, BackpressureStrategy.BUFFER);
    }

    public Flowable<Device> getConnectedDevices() {
        return Flowable.create(e -> {
            Response<DeviceListResponse> response = userApi
                    .getUserDevices(DEVICES_FIELDS)
                    .execute();
            wrapDevices(e, response);
        }, BackpressureStrategy.BUFFER);
    }

    /**
     * Connect to device by its serial number. @{@link Deprecated} use connectToDevice() method instead
     * @param serial target device serial number
     * @param timeout connection timeout
     * @return remote connection url
     */
    @Deprecated
    public Single<String> connect(final String serial, final int timeout) {
        return from(serial)
                .doOnEvent(addUserToDevice(serial, timeout))
                .map(new DeviceConnectFunction());
    }

    /**
     * Connect to device by its serial number.
     * @param serial target device serial number
     * @param timeout connection timeout
     * @return RemoteConnectUserDeviceResponse with remote connection url and its serial number
     */
    public Single<ConnectedFarmDevice> connectToDevice(final String serial, final int timeout) {
        return from(serial)
                .doOnEvent(addUserToDevice(serial, timeout))
                .map(new ConnectedFarmDeviceConnectFunction());
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
        return (s, throwable) -> {
            Response<Object> response = userApi.deleteUserDeviceBySerial(s).execute();
            if (!response.isSuccessful()) {
                throw new ResponseException(response.code(), response.message());
            }
        };
    }

    private BiConsumer<String, Throwable> disconnectFromDevice() {
        return (s, throwable) -> {
            Response<Object> response = userApi.remoteDisconnectUserDeviceBySerial(s).execute();
            if (!response.isSuccessful()) {
                throw new ResponseException(response.code(), response.message());
            }
        };
    }

    private Single<String> from(String serial) {
        //noinspection Convert2MethodRef
        return Single.just(serial)
                .filter(s -> s != null)
                .switchIfEmpty(new MaybeError<>(new NullPointerException("Serial is null")))
                .toSingle();
    }

    private BiConsumer<String, Throwable> addUserToDevice(final String serial, final int timeout) {
        return (s, throwable) -> {
            AddUserDevicePayload payload = new AddUserDevicePayload();
            payload.setSerial(serial);
            payload.setTimeout(timeout);
            Response<Object> response = userApi.addUserDevice(payload).execute();
            if (!response.isSuccessful()) {
                throw new ResponseException(response);
            }
        };
    }

    private class DeviceConnectFunction implements Function<String, String> {
        @Override
        public String apply(@NonNull String serial) throws Exception {
            Response<RemoteConnectUserDeviceResponse> response = userApi.remoteConnectUserDeviceBySerial(serial).execute();
            if (response.isSuccessful()) {
                final RemoteConnectUserDeviceResponse body = response.body();
                if (body != null && body.getRemoteConnectUrl() != null) {
                    return body.getRemoteConnectUrl();
                } else {
                    throw new IllegalArgumentException("Connect was successful but response is invalid");
                }
            } else {
                throw new ResponseException(response);
            }
        }
    }

    private class ConnectedFarmDeviceConnectFunction implements Function<String, ConnectedFarmDevice> {
        @Override
        public ConnectedFarmDevice apply(@NonNull String serial) throws Exception {
            Response<RemoteConnectUserDeviceResponse> response = userApi.remoteConnectUserDeviceBySerial(serial).execute();
            if (response.isSuccessful()) {
                final RemoteConnectUserDeviceResponse body = response.body();
                if (body != null && body.getRemoteConnectUrl() != null) {
                    return new ConnectedFarmDevice(serial, body.getRemoteConnectUrl());
                } else {
                    throw new IllegalArgumentException("Connect was successful but response is invalid");
                }
            } else {
                throw new ResponseException(response);
            }
        }
    }
}
