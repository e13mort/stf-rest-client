package com.github.e13mort.stf.adapter.filters;

import com.github.e13mort.stf.model.device.Device;
import io.reactivex.functions.Predicate;

public class Filter {

    public static Predicate<Device> abi(String abi) {
        if (abi == null) {
            throw new NullPointerException("Abi shouldn't be null");
        }
        return new AbiPredicate(abi);
    }

    public static Predicate<Device> api(int minApiLevel) {
        return new ApiLevel(minApiLevel);
    }

    public static Predicate<Device> available(boolean filter) {
        return new AvailabilityPredicate(filter);
    }

}
