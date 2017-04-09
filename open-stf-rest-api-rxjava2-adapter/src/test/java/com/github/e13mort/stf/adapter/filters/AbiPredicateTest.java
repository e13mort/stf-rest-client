package com.github.e13mort.stf.adapter.filters;

import com.github.e13mort.stf.model.device.Device;
import io.reactivex.Observable;
import io.reactivex.observers.TestObserver;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AbiPredicateTest {

    private Observable<Device> testDataObservable;

    @Before
    public void setup() {
        ArrayList<Device> devices = new ArrayList<>();
        devices.add(mockDevice("abi1"));
        devices.add(mockDevice("abi2"));
        devices.add(mockDevice("abi2"));
        devices.add(mockDevice(null));
        devices.add(mockDevice("another_abi"));
        devices.add(mockDevice("abi"));
        testDataObservable = Observable.fromIterable(devices);
    }

    @Test
    public void testNullFilterEmmitTheNullPointerException() {
        TestObserver<Device> observer = testDataObservable.filter(new AbiPredicate(null)).test();
        observer.assertError(NullPointerException.class);
    }

    @Test
    public void testAbi2FilterReturns2Values() throws Exception {
        TestObserver<Device> observer = testDataObservable.filter(new AbiPredicate("abi2")).test();
        observer.assertValueCount(2);
    }

    private Device mockDevice(String mockAbi) {
        Device mock = mock(Device.class);
        when(mock.getAbi()).thenReturn(mockAbi);
        return mock;
    }
}