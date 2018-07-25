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
import io.reactivex.Single;
import io.reactivex.functions.Function;

import java.util.List;

@SuppressWarnings({"unused", "WeakerAccess"})
public class FarmClient {
    private static final int DEFAULT_CONNECTION_TIMEOUT_SEC = 60;
    private final RxFarm rxFarm;
    private final int connectionTimeoutSec;
    private final Function<Single<String>, Flowable<Notification<String>>> notificationConversionFunction =
            stringSingle -> stringSingle
                    .toFlowable()
                    .map(Notification::createOnNext)
                    .onErrorReturn(Notification::createOnError);

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
                .flatMap(device -> rxFarm.connect(device.getSerial(), connectionTimeoutSec * 1000).to(notificationConversionFunction));
    }

    public Flowable<Notification<String>> disconnectFromAllDevices() {
        return rxFarm.getConnectedDevices()
                .flatMap(device -> rxFarm.disconnect(device.getSerial()).to(notificationConversionFunction));
    }

    public Flowable<Notification<String>> disconnectFromDevices(List<String> serialNumbers) {
        return Flowable.fromIterable(serialNumbers)
                .flatMap(serial -> rxFarm.disconnect(serial).to(notificationConversionFunction));
    }

}
