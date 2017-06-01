package com.github.e13mort.stf.adapter.filters;

import com.github.e13mort.stf.model.device.Device;
import com.github.e13mort.stf.model.device.Provider;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;

import java.util.List;

public class ProviderPredicate implements Predicate<Device> {

    private final Type type;
    private final List<String> templates;

    ProviderPredicate(Type type, List<String> templates) {
        this.type = type;
        this.templates = templates;
    }

    @Override
    public boolean test(@NonNull Device device) throws Exception {
        boolean include = type == Type.INCLUDE;
        Provider provider = device.getProvider();
        return !include && provider == null
                || provider != null && testName(provider.getName(), include);
    }

    private boolean testName(String name, boolean include) {
        if (name == null) {
            return !include;
        }
        for (String template : templates) {
            if (name.toLowerCase().contains(template.toLowerCase())) {
                return include;
            }
        }
        return !include;
    }

    public enum Type {INCLUDE, EXCLUDE}

}
