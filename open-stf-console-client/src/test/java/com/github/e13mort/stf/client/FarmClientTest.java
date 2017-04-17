package com.github.e13mort.stf.client;

import com.github.e13mort.stf.adapter.RxFarm;
import com.github.e13mort.stf.model.device.Device;
import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;
import io.reactivex.subscribers.TestSubscriber;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FarmClientTest {

    private FarmClient client;

    @Before
    public void setUp() throws Exception {
        RxFarm rxFarm = mock(RxFarm.class);
        ArrayList<Device> devices = createTestDevices();
        when(rxFarm.getAllDevices()).thenReturn(Flowable.fromIterable(devices));
        client = new FarmClient(rxFarm);
    }

    @Test
    public void testEmptyDeviceParamsReturnsActiveItems() throws Exception {
        DevicesParams params = new DevicesParams();
        client.getDevices(params).test().assertValueCount(3);
    }

    @Test
    public void testDisabledAvailabilityFlagReturnsAllItems() throws Exception {
        DevicesParams params = new DevicesParams();
        params.setAllDevices(true);
        client.getDevices(params).test().assertValueCount(4);
    }

    @Test
    public void testOneActiveAbiItem() throws Exception {
        DevicesParams params = new DevicesParams();
        params.setAbi("abi1");
        client.getDevices(params).test().assertValueCount(1);
    }

    @Test
    public void testAbiAndApiLevelReturnsTwoActiveDevices() throws Exception {
        DevicesParams params = new DevicesParams();
        params.setAbi("abi2");
        params.setOsVersion(21);
        client.getDevices(params).test().assertValueCount(2);
    }

    @Test
    public void testTakeOnly2FirstDevices() throws Exception {
        DevicesParams params = new DevicesParams();
        params.setAllDevices(true);
        params.setCount(2);
        TestSubscriber<Device> testSubscriber = client.getDevices(params).test();
        testSubscriber.assertValueCount(2);
        testSubscriber.assertValueAt(0, new TestNamePredicate("name1"));
        testSubscriber.assertValueAt(1, new TestNamePredicate("name2"));
    }

    private ArrayList<Device> createTestDevices() {
        ArrayList<Device> devices = new ArrayList<>();
        devices.add(mock(Device.class));
        devices.add(mock(Device.class));
        devices.add(mock(Device.class));
        devices.add(mock(Device.class));

        when(devices.get(0).getAbi()).thenReturn("abi1");
        when(devices.get(1).getAbi()).thenReturn("abi1");
        when(devices.get(2).getAbi()).thenReturn("abi2");
        when(devices.get(3).getAbi()).thenReturn("abi2");

        when(devices.get(0).getName()).thenReturn("name1");
        when(devices.get(1).getName()).thenReturn("name2");
        when(devices.get(2).getName()).thenReturn("name3");
        when(devices.get(3).getName()).thenReturn("name4");

        when(devices.get(0).getSdk()).thenReturn(19);
        when(devices.get(1).getSdk()).thenReturn(21);
        when(devices.get(2).getSdk()).thenReturn(21);
        when(devices.get(3).getSdk()).thenReturn(25);

        when(devices.get(0).getPresent()).thenReturn(true);
        when(devices.get(1).getPresent()).thenReturn(true);
        when(devices.get(2).getPresent()).thenReturn(true);
        when(devices.get(3).getPresent()).thenReturn(true);

        when(devices.get(0).getReady()).thenReturn(true);
        when(devices.get(1).getReady()).thenReturn(false);
        when(devices.get(2).getReady()).thenReturn(true);
        when(devices.get(3).getReady()).thenReturn(true);

        return devices;
    }


    private static class TestNamePredicate implements Predicate<Device> {

        private String name;

        TestNamePredicate(String name) {
            this.name = name;
        }

        @Override
        public boolean test(@NonNull Device device) throws Exception {
            return device.getName().equals(name);
        }
    }
}