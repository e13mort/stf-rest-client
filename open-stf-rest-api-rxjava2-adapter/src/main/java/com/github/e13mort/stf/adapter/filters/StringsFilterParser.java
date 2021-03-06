package com.github.e13mort.stf.adapter.filters;

import java.util.Arrays;
import java.util.List;

import static com.github.e13mort.stf.adapter.filters.InclusionType.EXCLUDE;
import static com.github.e13mort.stf.adapter.filters.InclusionType.INCLUDE;

public class StringsFilterParser {

    public static final String DEFAULT_NEGATIVE_SIGN = "~";
    public static final String DEFAULT_DELIMITER_SIGN = ",";
    private final String delimiterSign;
    private final String negativeSign;

    public StringsFilterParser() {
        this(DEFAULT_NEGATIVE_SIGN, DEFAULT_DELIMITER_SIGN);
    }

    public StringsFilterParser(String negativeSign, String delimiterSign) {
        this.delimiterSign = delimiterSign;
        this.negativeSign = negativeSign;
    }

    public StringsFilterDescription parse(String rawTemplate) {
        validate(rawTemplate);

        InclusionType type = getType(rawTemplate);
        List<String> templates = getTemplates(rawTemplate);

        return new StringsFilterDescription(type, templates);
    }

    private InclusionType getType(String rawTemplate) {
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
