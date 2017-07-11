package com.github.e13mort.stf.adapter.filters;

import java.util.List;

public class ProviderDescription {
    private final InclusionType type;
    private final List<String> templates;

    public ProviderDescription(InclusionType type, List<String> templates) {
        this.type = type;
        this.templates = templates;
    }

    public InclusionType getType() {
        return type;
    }

    public List<String> getTemplates() {
        return templates;
    }
}
