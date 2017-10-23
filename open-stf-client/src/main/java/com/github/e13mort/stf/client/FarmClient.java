package com.github.e13mort.stf.client;

import com.github.e13mort.stf.ApiClient;
import com.github.e13mort.stf.adapter.RxFarm;
import com.github.e13mort.stf.api.DevicesApi;
import com.github.e13mort.stf.api.UserApi;
import com.github.e13mort.stf.client.parameters.DeviceParamsTransformer;
import com.github.e13mort.stf.client.parameters.DevicesParams;
import com.github.e13mort.stf.model.device.Device;
import io.reactivex.Flowable;
import io.reactivex.Notification;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import org.reactivestreams.Publisher;

@SuppressWarnings({"unused", "WeakerAccess"})
public class FarmClient {
    private static final int DEFAULT_CONNECTION_TIMEOUT_SEC = 60;
    private final RxFarm rxFarm;
    private final int connectionTimeoutSec;

    public static FarmClient create(FarmInfo farmInfo) {
        if (farmInfo == null) {
            throw new NullPointerException("Farm info is null");
        }
        ApiClient client = new ApiClient(farmInfo.getUrl(), farmInfo.getAuthKey());
        int timeout = farmInfo.getTimeoutSec() > 0 ? farmInfo.getTimeoutSec() : DEFAULT_CONNECTION_TIMEOUT_SEC;
        return new FarmClient(new RxFarm(client.createService(DevicesApi.class), client.createService(UserApi.class)), timeout);
    }

    FarmClient(RxFarm rxFarm, int connectionTimeoutSec) {
        this.rxFarm = rxFarm;
        this.connectionTimeoutSec = connectionTimeoutSec;
    }

    public Flowable<Device> getDevices(DevicesParams params) {
        return rxFarm.getAllDevices().compose(new DeviceParamsTransformer(params));
    }

    public Flowable<Device> getMyDevices() {
        return rxFarm.getConnectedDevices();
    }

    public Flowable<Notification<String>> connectToDevices(DevicesParams params) {
        return getDevices(params)
                .flatMap(device -> rxFarm.connect(device.getSerial(), connectionTimeoutSec * 1000).toFlowable())
                .flatMap(s -> Flowable.just(Notification.createOnNext(s)));
    }

    public Flowable<Notification<String>> disconnectFromAllDevices() {
        return rxFarm.getConnectedDevices()
                .flatMap(device -> rxFarm.disconnect(device.getSerial()).toFlowable())
                .flatMap(s -> Flowable.just(Notification.createOnNext(s)));
    }

}
