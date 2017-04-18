package com.github.e13mort.stf.adapter.filters;

import com.github.e13mort.stf.model.device.Device;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;

class NamePredicate implements Predicate<Device> {

    private final String name;

    NamePredicate(String name) {
        this.name = name;
    }

    @Override
    public boolean test(@NonNull Device device) throws Exception {
        if (name == null) {
            throw new NullPointerException("Name should not be a null");
        }
        return device.getName() != null && device.getName().toLowerCase().contains(name.toLowerCase());
    }
}
