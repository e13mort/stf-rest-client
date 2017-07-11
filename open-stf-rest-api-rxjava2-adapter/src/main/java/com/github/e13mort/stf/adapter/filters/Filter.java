package com.github.e13mort.stf.adapter.filters;

import com.github.e13mort.stf.model.device.Device;
import io.reactivex.functions.Predicate;

import java.util.List;

public class Filter {

    public static Predicate<Device> abi(String abi) {
        validate(abi, "Abi");
        return new AbiPredicate(abi);
    }

    public static Predicate<Device> api(int apiLevel) {
        return new ApiLevel(apiLevel, ApiLevel.Kind.EQUALS);
    }

    public static Predicate<Device> minApi(int apiLevel) {
        return new ApiLevel(apiLevel, ApiLevel.Kind.MIN);
    }

    public static Predicate<Device> maxApi(int apiLevel) {
        return new ApiLevel(apiLevel, ApiLevel.Kind.MAX);
    }

    public static Predicate<Device> available(boolean filter) {
        return new AvailabilityPredicate(filter);
    }

    public static Predicate<Device> name(List<String> names) {
        validate(names, "names");
        return new NamePredicate(names);
    }

    public static Predicate<Device> provider(StringsFilterDescription description) {
        return new ProviderPredicate(description.getType(), description.getTemplates());
    }

    private static void validate(Object o, String var) {
        if (o == null) {
            throw new NullPointerException(var + " shouldn't be null");
        }
    }

}
