package com.github.e13mort.stf.adapter.filters;

import com.github.e13mort.stf.model.device.Device;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SerialNumberPredicateTest extends SingleMockedStringFieldTest{

    @Test
    void testExcludeInvalidStringWillEmmitAllValues() {
        test(create(InclusionType.EXCLUDE, "invalid"), 5);
    }

    @Test
    void testIncludeInvalidStringWontEmmitAnyValue() {
        test(create(InclusionType.INCLUDE, "invalid"), 0);
    }

    @Test
    void testIncludeSerial1WillEmmitTwoValues() {
        test(create(InclusionType.INCLUDE, "serial1"), 2);
    }

    @Test
    void testExcludeSerial1WillEmmitTwoValues() {
        test(create(InclusionType.EXCLUDE, "serial1"), 3);
    }

    @Test
    void testExcludeAllExceptSerial4WillEmmitOneValue() {
        test(create(InclusionType.EXCLUDE, "serial1", "serial2", "serial4"), 1);
    }

    private SerialNumberPredicate create(InclusionType exclude, String... strings) {
        return new SerialNumberPredicate(Arrays.asList(strings), exclude);
    }

    @Override
    protected String[] getStrings() {
        return new String[]{"serial1", "serial2", "serial3", "serial1", "serial4"};
    }

    @Override
    protected Device mockDevice(String param) {
        Device device = mock(Device.class);
        when(device.getSerial()).thenReturn(param);
        return device;
    }
}