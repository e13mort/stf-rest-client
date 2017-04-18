package com.github.e13mort.stf.adapter.filters;

import com.github.e13mort.stf.model.device.Device;
import io.reactivex.observers.TestObserver;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AbiPredicateTest extends SingleMockedStringFieldTest {

    @Test
    public void testNullFilterEmmitTheNullPointerException() {
        getDeviceTestObserver(null).assertError(NullPointerException.class);
    }

    @Test
    public void testAbi2FilterReturns2Values() throws Exception {
        getDeviceTestObserver("abi2").assertValueCount(2);
    }

    @Test
    public void testGeneralAbiReturns5Values() throws Exception {
        getDeviceTestObserver("abi").assertValueCount(5);
    }

    private TestObserver<Device> getDeviceTestObserver(String abi) {
        return testDataObservable.filter(new AbiPredicate(abi)).test();
    }

    @Override
    protected String[] getStrings() {
        return new String[]{"abi1", "abi2", "abi2",
                null, "another_abi", "abi"};
    }

    @Override
    protected Device mockDevice(String param) {
        Device mock = mock(Device.class);
        when(mock.getAbi()).thenReturn(param);
        return mock;
    }
}