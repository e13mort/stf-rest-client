package com.github.e13mort.stf.adapter.filters;

import com.github.e13mort.stf.model.device.Device;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;

import java.util.List;

public abstract class BaseStringsPredicate implements Predicate<Device> {
    private final InclusionType type;
    private final List<String> templates;

    BaseStringsPredicate(List<String> templates, InclusionType type) {
        this.templates = templates;
        this.type = type;
    }

    @Override
    public boolean test(@NonNull Device device) throws Exception {
        boolean include = type == InclusionType.INCLUDE;
        String name = getComparableString(device);
        return !include && name == null
                || name != null && testString(name, include);
    }

    protected abstract String getComparableString(@NonNull Device device);

    private boolean testString(String name, boolean include) {
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
}
