package com.github.e13mort.stf.adapter.filters;

import com.github.e13mort.stf.model.device.Device;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AvailabilityPredicateTest {

    @Test
    public void testDisabledFilterDoesNotAffectOfFlow() throws Exception {
        TestObserver<Device> testObserver = Observable.fromArray(
                getMock(true, true),
                getMock(true, false),
                getMock(false, true),
                getMock(false, false)
        )
                .filter(new AvailabilityPredicate(false))
                .test();
        testObserver.assertValueCount(4);
    }

    @Test
    public void testEnabledFilterClearsFlow() throws Exception {
        TestObserver<Device> testObserver = Observable.fromArray(
                getMock(true, true),
                getMock(true, false),
                getMock(false, true),
                getMock(false, false)
        )
                .filter(new AvailabilityPredicate(true))
                .test();
        testObserver.assertValueCount(1);
    }

    private Device getMock(boolean ready, boolean present) {
        Device device = mock(Device.class);
        when(device.getReady()).thenReturn(ready);
        when(device.getPresent()).thenReturn(present);
        return device;
    }

}