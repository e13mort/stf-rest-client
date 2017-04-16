package com.github.e13mort.stf.adapter.filters;

import com.github.e13mort.stf.model.device.Device;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;

class AvailabilityPredicate implements Predicate<Device> {
    private final boolean filterUnavailableDevices;

    AvailabilityPredicate(boolean filterUnavailableDevices) {
        this.filterUnavailableDevices = filterUnavailableDevices;
    }

    @Override
    public boolean test(@NonNull Device device) throws Exception {
        return !filterUnavailableDevices || isDeviceAvailable(device);
    }

    private boolean isDeviceAvailable(@NonNull Device device) {
        return device.getReady() && device.getPresent();
    }
}
