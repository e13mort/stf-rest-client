package com.github.e13mort.stf.client;

import com.github.e13mort.stf.ApiClient;
import com.github.e13mort.stf.adapter.RxFarm;
import com.github.e13mort.stf.adapter.filters.Filter;
import com.github.e13mort.stf.api.DevicesApi;
import com.github.e13mort.stf.api.UserApi;
import com.github.e13mort.stf.model.device.Device;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.functions.Predicate;
import org.reactivestreams.Publisher;

public class FarmClient {
    private final RxFarm rxFarm;

    public static FarmClient create(FarmInfo farmInfo) {
        if (farmInfo == null) {
            throw new NullPointerException("Farm info is null");
        }
        ApiClient client = new ApiClient(farmInfo.getUrl(), farmInfo.getAuthKey());
        return new FarmClient(new RxFarm(client.createService(DevicesApi.class), client.createService(UserApi.class)));
    }

    FarmClient(RxFarm rxFarm) {
        this.rxFarm = rxFarm;
    }

    public Flowable<Device> getDevices(DevicesParams params) {
        return rxFarm.getAllDevices().compose(new DeviceParamsTransformer(params));
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
            if (params.getOsVersion() > 0) {
                upstream = apply(upstream, Filter.api(params.getOsVersion()));
            }
            upstream = apply(upstream, Filter.available(!params.isAllDevices()));
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
