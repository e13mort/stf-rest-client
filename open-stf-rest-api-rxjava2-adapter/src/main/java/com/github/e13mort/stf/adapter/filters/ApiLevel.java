package com.github.e13mort.stf.adapter.filters;

import com.github.e13mort.stf.model.device.Device;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;

public class ApiLevel implements Predicate<Device> {

    private int minApiLevel;

    ApiLevel(int minApiLevel) {
        this.minApiLevel = minApiLevel;
    }

    @Override
    public boolean test(@NonNull Device device) throws Exception {
        return device != null && device.getSdk() >= minApiLevel;
    }
}
