package com.github.e13mort.stf.adapter.filters;

import com.github.e13mort.stf.model.device.Device;

import java.util.List;

class NamePredicate extends BaseStringsPredicate {

    NamePredicate(List<String> templates, InclusionType type) {
        super(templates, type);
    }

    @Override
    protected String getComparableString(Device device) {
        return device.getName();
    }
}
