package com.github.e13mort.stf.adapter;

import com.github.e13mort.stf.api.DevicesApi;
import com.github.e13mort.stf.api.UserApi;
import com.github.e13mort.stf.model.DeviceListResponse;
import com.github.e13mort.stf.model.DeviceResponse;
import com.github.e13mort.stf.model.device.Device;
import io.reactivex.*;
import retrofit2.Response;

import java.util.List;

public class RxFarm {
    public static final String DEVICES_FIELDS = "";
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
        }, BackpressureStrategy.BUFFER);
    }
}
