package com.github.e13mort.stf.adapter.filters;

import com.github.e13mort.stf.model.device.Device;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;

public class ApiLevel implements Predicate<Device> {

    private final int minApiLevel;
    private final Kind kind;

    ApiLevel(int minApiLevel, Kind kind) {
        this.minApiLevel = minApiLevel;
        this.kind = kind;
    }

    @Override
    public boolean test(@NonNull Device device) throws Exception {
        return device != null && kind.test(minApiLevel, device.getSdk());
    }

    public enum Kind {
        EQUALS(0), MIN(-1), MAX(1);

        private final int result;

        Kind(int result) {
            this.result = result;
        }

        boolean test(int origin, int target) {
            int compare = Integer.compare(origin, target);
            return compare == 0 || compare == result;
        }
    }
}
