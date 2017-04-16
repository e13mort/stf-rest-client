package com.github.e13mort.stf.adapter.filters;

import com.github.e13mort.stf.model.device.Device;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;

class AbiPredicate implements Predicate<Device> {

    private final String abi;

    AbiPredicate(String abi) {
        this.abi = abi;
    }

    @Override
    public boolean test(@NonNull Device device) throws Exception {
        if (abi == null) {
            throw new NullPointerException("The abi shouldn't be null");
        }
        return device != null && device.getAbi() != null && device.getAbi().toLowerCase().contains(abi.toLowerCase());
    }
}
