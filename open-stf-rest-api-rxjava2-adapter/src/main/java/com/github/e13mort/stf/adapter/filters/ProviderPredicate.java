package com.github.e13mort.stf.adapter.filters;

import com.github.e13mort.stf.model.device.Device;
import io.reactivex.annotations.NonNull;

import java.util.List;

class ProviderPredicate extends BaseStringsPredicate {

    ProviderPredicate(InclusionType type, List<String> templates) {
        super(templates, type);
    }

    @Override
    protected String getComparableString(@NonNull Device device) {
        return device.getProvider() != null ? device.getProvider().getName() : null;
    }
}
