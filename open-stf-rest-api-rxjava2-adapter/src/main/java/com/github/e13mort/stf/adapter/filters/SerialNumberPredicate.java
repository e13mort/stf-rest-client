package com.github.e13mort.stf.adapter.filters;

import com.github.e13mort.stf.model.device.Device;

import java.util.List;

public class SerialNumberPredicate extends BaseStringsPredicate {
    SerialNumberPredicate(List<String> templates, InclusionType type) {
        super(templates, type);
    }

    @Override
    protected String getComparableString(Device device) {
        return device.getSerial();
    }
}
