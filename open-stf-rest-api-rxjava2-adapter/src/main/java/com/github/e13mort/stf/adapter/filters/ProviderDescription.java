package com.github.e13mort.stf.adapter.filters;

import java.util.List;

public class ProviderDescription {
    private final ProviderPredicate.Type type;
    private final List<String> templates;

    public ProviderDescription(ProviderPredicate.Type type, List<String> templates) {
        this.type = type;
        this.templates = templates;
    }

    public ProviderPredicate.Type getType() {
        return type;
    }

    public List<String> getTemplates() {
        return templates;
    }
}
