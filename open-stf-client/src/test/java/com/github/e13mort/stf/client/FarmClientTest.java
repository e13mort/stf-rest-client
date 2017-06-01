package com.github.e13mort.stf.client;

import com.github.e13mort.stf.adapter.RxFarm;
import com.github.e13mort.stf.adapter.filters.ProviderDescription;
import com.github.e13mort.stf.adapter.filters.ProviderPredicate;
import com.github.e13mort.stf.model.device.Device;
import com.github.e13mort.stf.model.device.Provider;
import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;
import io.reactivex.subscribers.TestSubscriber;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FarmClientTest {

    private static final int TIMEOUT_SEC = 60;
    private FarmClient client;

    @Before
    public void setUp() throws Exception {
        RxFarm rxFarm = mock(RxFarm.class);
        ArrayList<Device> devices = createTestDevices();
        when(rxFarm.getAllDevices()).thenReturn(Flowable.fromIterable(devices));
        client = new FarmClient(rxFarm, TIMEOUT_SEC);
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
        params.setApiVersion(21);
        client.getDevices(params).test().assertValueCount(1);
    }

    @Test
    public void testMinApiLevel9ReturnsFourActiveDevices() throws Exception {
        DevicesParams params = createTestParams();
        params.setMinApiVersion(9);
        client.getDevices(params).test().assertValueCount(4);
    }

    @Test
    public void testMaxApiLevel9ReturnsNoActiveDevices() throws Exception {
        DevicesParams params = createTestParams();
        params.setMaxApiVersion(9);
        client.getDevices(params).test().assertNoValues();
    }

    @Test
    public void testMaxApiLevel21Returns3ActiveDevices() throws Exception {
        DevicesParams params = createTestParams();
        params.setMaxApiVersion(21);
        client.getDevices(params).test().assertValueCount(3);
    }

    @Test
    public void testMin21AndMax25ApiLevelReturns3ActiveDevices() throws Exception {
        DevicesParams params = createTestParams();
        params.setMinApiVersion(21);
        params.setMaxApiVersion(25);
        client.getDevices(params).test().assertValueCount(3);
    }

    @Test
    public void testTakeOnly2FirstDevices() throws Exception {
        DevicesParams params = createTestParams();
        params.setCount(2);
        TestSubscriber<Device> testSubscriber = client.getDevices(params).test();
        testSubscriber.assertValueCount(2);
        testSubscriber.assertValueAt(0, new TestNamePredicate("name1"));
        testSubscriber.assertValueAt(1, new TestNamePredicate("name2"));
    }

    @Test
    public void testProviderIncludeStringProvider() throws Exception {
        DevicesParams params = setupProvider(createTestParams(), ProviderPredicate.Type.INCLUDE, "provider");
        TestSubscriber<Device> testSubscriber = client.getDevices(params).test();
        testSubscriber.assertValueCount(3);
        testSubscriber.assertValueAt(0, new ProviderNamePredicate("provider1"));
        testSubscriber.assertValueAt(1, new ProviderNamePredicate("provider2"));
        testSubscriber.assertValueAt(2, new ProviderNamePredicate("provider3"));
    }

    @Test
    public void testProviderIncludeString1() throws Exception {
        DevicesParams params = setupProvider(createTestParams(), ProviderPredicate.Type.INCLUDE, "1");
        TestSubscriber<Device> testSubscriber = client.getDevices(params).test();
        testSubscriber.assertValueCount(1);
        testSubscriber.assertValueAt(0, new ProviderNamePredicate("provider1"));
    }

    @Test
    public void testProviderExcludeString1() throws Exception {
        DevicesParams params = setupProvider(createTestParams(), ProviderPredicate.Type.EXCLUDE, "1");
        TestSubscriber<Device> testSubscriber = client.getDevices(params).test();
        testSubscriber.assertValueCount(3);
        testSubscriber.assertValueAt(0, new ProviderNamePredicate("provider2"));
        testSubscriber.assertValueAt(1, new ProviderNamePredicate("provider3"));
        testSubscriber.assertValueAt(2, new ProviderNamePredicate(null));
    }

    private DevicesParams setupProvider(DevicesParams params, ProviderPredicate.Type type, String... s) {
        params.setProviderDescription(new ProviderDescription(type, Arrays.asList(s)));
        return params;
    }

    private DevicesParams createTestParams() {
        DevicesParams params = new DevicesParams();
        params.setAllDevices(true);
        return params;
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

        when(devices.get(0).getProvider()).thenReturn(provider("provider1"));
        when(devices.get(1).getProvider()).thenReturn(provider("provider2"));
        when(devices.get(2).getProvider()).thenReturn(provider("provider3"));
        when(devices.get(3).getProvider()).thenReturn(null);

        return devices;
    }

    private Provider provider(String name) {
        Provider provider = new Provider();
        provider.setName(name);
        return provider;
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

    private static class ProviderNamePredicate implements Predicate<Device> {

        private String name;

        ProviderNamePredicate(String name) {
            this.name = name;
        }

        @Override
        public boolean test(@NonNull Device device) throws Exception {
            if (name == null) {
                return device.getProvider() == null || device.getProvider().getName() == null;
            }
            return device.getProvider().getName().equals(name);
        }
    }
}