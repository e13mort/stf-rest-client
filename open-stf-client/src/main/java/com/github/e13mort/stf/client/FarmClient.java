package com.github.e13mort.stf.client;

import com.github.e13mort.stf.ApiClient;
import com.github.e13mort.stf.adapter.RxFarm;
import com.github.e13mort.stf.adapter.filters.Filter;
import com.github.e13mort.stf.api.DevicesApi;
import com.github.e13mort.stf.api.UserApi;
import com.github.e13mort.stf.model.device.Device;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.Notification;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;
import org.reactivestreams.Publisher;

@SuppressWarnings("unused")
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

    public Flowable<Notification<String>> connectToDevices(DevicesParams params) {
        return getDevices(params)
                .flatMap(new Function<Device, Publisher<String>>() {
                    @Override
                    public Publisher<String> apply(@NonNull Device device) throws Exception {
                        return rxFarm.connect(device.getSerial(), connectionTimeoutSec * 1000).toFlowable();
                    }
                })
                .flatMap(new Function<String, Publisher<Notification<String>>>() {
                    @Override
                    public Publisher<Notification<String>> apply(@NonNull String s) throws Exception {
                        return Flowable.just(Notification.createOnNext(s));
                    }
                });
    }

    public Flowable<Notification<String>> disconnectFromAllDevices() {
        return rxFarm.getConnectedDevices()
                .flatMap(new Function<Device, Publisher<String>>() {
                    @Override
                    public Publisher<String> apply(@NonNull Device device) throws Exception {
                        return rxFarm.disconnect(device.getSerial()).toFlowable();
                    }
                })
                .flatMap(new Function<String, Publisher<Notification<String>>>() {
                    @Override
                    public Publisher<Notification<String>> apply(@NonNull String s) throws Exception {
                        return Flowable.just(Notification.createOnNext(s));
                    }
                });
    }

    private static class DeviceParamsTransformer implements FlowableTransformer<Device, Device> {
        private final DevicesParams params;

        DeviceParamsTransformer(DevicesParams params) {
            this.params = params;
        }

        @Override
        public Publisher<Device> apply(Flowable<Device> upstream) {
            if (params == null) {
                return upstream;
            }
            if (params.getAbi() != null) {
                upstream = apply(upstream, Filter.abi(params.getAbi()));
            }
            if (params.getApiVersion() > 0) {
                upstream = apply(upstream, Filter.api(params.getApiVersion()));
            }
            if (params.getMinApiVersion() > 0) {
                upstream = apply(upstream, Filter.minApi(params.getMinApiVersion()));
            }
            if (params.getMaxApiVersion() > 0) {
                upstream = apply(upstream, Filter.maxApi(params.getMaxApiVersion()));
            }
            upstream = apply(upstream, Filter.available(!params.isAllDevices()));
            if (params.getNameFilterDescription() != null) {
                upstream = apply(upstream, Filter.name(params.getNameFilterDescription()));
            }
            if(params.getProviderFilterDescription() != null) {
                upstream = apply(upstream, Filter.provider(params.getProviderFilterDescription()));
            }
            if (params.getSerialFilterDescription() != null) {
                upstream = apply(upstream, Filter.serial(params.getSerialFilterDescription()));
            }
            if (params.getCount() > 0) {
                upstream = upstream.take(params.getCount());
            }
            return upstream;
        }

        private Flowable<Device> apply(Flowable<Device> upstream, Predicate<Device> filter) {
            return upstream.filter(filter);
        }
    }
}
