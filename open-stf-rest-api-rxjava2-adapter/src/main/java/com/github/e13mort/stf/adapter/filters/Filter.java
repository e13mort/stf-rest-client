package com.github.e13mort.stf.adapter.filters;

import com.github.e13mort.stf.model.device.Device;
import io.reactivex.functions.Predicate;

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

    public static Predicate<Device> name(String name) {
        validate(name, "name");
        return new NamePredicate(name);
    }

    public static Predicate<Device> provider(ProviderDescription description) {
        ProviderStringParser parser = new ProviderStringParser();
        return new ProviderPredicate(description.getType(), description.getTemplates());
    }

    private static void validate(String abi, String var) {
        if (abi == null) {
            throw new NullPointerException(var +" shouldn't be null");
        }
    }

}
