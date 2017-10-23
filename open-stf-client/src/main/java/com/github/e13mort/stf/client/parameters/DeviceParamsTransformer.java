package com.github.e13mort.stf.client.parameters;

import com.github.e13mort.stf.adapter.filters.Filter;
import com.github.e13mort.stf.model.device.Device;
import io.reactivex.Flowable;
import io.reactivex.FlowableTransformer;
import io.reactivex.functions.Predicate;
import org.reactivestreams.Publisher;

public class DeviceParamsTransformer implements FlowableTransformer<Device, Device> {
    private final DevicesParams params;

    public DeviceParamsTransformer(DevicesParams params) {
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
