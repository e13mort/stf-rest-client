package com.github.e13mort.stf.adapter.filters;

import java.util.Arrays;
import java.util.List;

import static com.github.e13mort.stf.adapter.filters.ProviderPredicate.Type.EXCLUDE;
import static com.github.e13mort.stf.adapter.filters.ProviderPredicate.Type.INCLUDE;

public class ProviderStringParser {

    public static final String DEFAULT_NEGATIVE_SIGN = "~";
    public static final String DEFAULT_DELIMITER_SIGN = ",";
    private final String delimiterSign;
    private final String negativeSign;

    public ProviderStringParser() {
        this(DEFAULT_NEGATIVE_SIGN, DEFAULT_DELIMITER_SIGN);
    }

    public ProviderStringParser(String negativeSign, String delimiterSign) {
        this.delimiterSign = delimiterSign;
        this.negativeSign = negativeSign;
    }

    public ProviderDescription parse(String rawTemplate) {
        validate(rawTemplate);

        ProviderPredicate.Type type = getType(rawTemplate);
        List<String> templates = getTemplates(rawTemplate);

        return new ProviderDescription(type, templates);
    }

    private ProviderPredicate.Type getType(String rawTemplate) {
        return hasNegativeSign(rawTemplate) ? EXCLUDE : INCLUDE;
    }

    private List<String> getTemplates(final String rawTemplate) {
        String templates = rawTemplate;
        if (hasNegativeSign(templates)) {
            templates = rawTemplate.substring(1);
        }
        return Arrays.asList(templates.split(delimiterSign));
    }

    private boolean hasNegativeSign(String rawTemplate) {
        return rawTemplate.startsWith(negativeSign);
    }

    private void validate(String rawTemplate) {
        if (rawTemplate == null || rawTemplate.length() == 0 || rawTemplate.equals(negativeSign)) {
            throw new IllegalArgumentException("Raw template string is invalid: " + rawTemplate);
        }
    }

}
