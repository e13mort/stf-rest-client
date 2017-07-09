package com.github.e13mort.stf.adapter.filters;

import com.github.e13mort.stf.model.device.Device;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;

import java.util.Collections;
import java.util.List;

class NamePredicate implements Predicate<Device> {

    private final List<String> names;

    NamePredicate(String name) {
        this.names = Collections.singletonList(name);
    }

    NamePredicate(List<String> names) {
        this.names = names;
    }

    @Override
    public boolean test(@NonNull Device device) throws Exception {
        String deviceName = device.getName();
        if (deviceName == null) {
            return false;
        }
        for (String name : names) {
            if (deviceName.toLowerCase().contains(name.toLowerCase())) {
                return true;
            }
        }
        return false;
    }
}
