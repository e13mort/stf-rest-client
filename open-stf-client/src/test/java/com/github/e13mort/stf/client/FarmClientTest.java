package com.github.e13mort.stf.client;

import com.github.e13mort.stf.adapter.RxFarm;
import com.github.e13mort.stf.adapter.filters.InclusionType;
import com.github.e13mort.stf.adapter.filters.StringsFilterDescription;
import com.github.e13mort.stf.client.parameters.DevicesParamsImpl;
import com.github.e13mort.stf.model.device.Device;
import com.github.e13mort.stf.model.device.Provider;
import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;
import io.reactivex.subscribers.TestSubscriber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class FarmClientTest {

    private static final int TIMEOUT_SEC = 60;
    private FarmClient client;

    @BeforeEach
    void setUp() throws Exception {
        RxFarm rxFarm = mock(RxFarm.class);
        ArrayList<Device> devices = createTestDevices();
        when(rxFarm.getAllDevices()).thenReturn(Flowable.fromIterable(devices));
        client = new FarmClient(rxFarm, TIMEOUT_SEC);
    }

    @Test
    void testEmptyDeviceParamsReturnsActiveItems() throws Exception {
        DevicesParamsImpl params = new DevicesParamsImpl();
        client.getDevices(params).test().assertValueCount(3);
    }

    @Test
    void testDisabledAvailabilityFlagReturnsAllItems() throws Exception {
        DevicesParamsImpl params = new DevicesParamsImpl();
        params.setAllDevices(true);
        client.getDevices(params).test().assertValueCount(4);
    }

    @Test
    void testOneActiveAbiItem() throws Exception {
        DevicesParamsImpl params = new DevicesParamsImpl();
        params.setAbi("abi1");
        client.getDevices(params).test().assertValueCount(1);
    }

    @Test
    void testAbiAndApiLevelReturnsTwoActiveDevices() throws Exception {
        DevicesParamsImpl params = new DevicesParamsImpl();
        params.setAbi("abi2");
        params.setApiVersion(21);
        client.getDevices(params).test().assertValueCount(1);
    }

    @Test
    void testMinApiLevel9ReturnsFourActiveDevices() throws Exception {
        DevicesParamsImpl params = createTestParams();
        params.setMinApiVersion(9);
        client.getDevices(params).test().assertValueCount(4);
    }

    @Test
    void testMaxApiLevel9ReturnsNoActiveDevices() throws Exception {
        DevicesParamsImpl params = createTestParams();
        params.setMaxApiVersion(9);
        client.getDevices(params).test().assertNoValues();
    }

    @Test
    void testMaxApiLevel21Returns3ActiveDevices() throws Exception {
        DevicesParamsImpl params = createTestParams();
        params.setMaxApiVersion(21);
        client.getDevices(params).test().assertValueCount(3);
    }

    @Test
    void testMin21AndMax25ApiLevelReturns3ActiveDevices() throws Exception {
        DevicesParamsImpl params = createTestParams();
        params.setMinApiVersion(21);
        params.setMaxApiVersion(25);
        client.getDevices(params).test().assertValueCount(3);
    }

    @Test
    void testTakeOnly2FirstDevices() throws Exception {
        DevicesParamsImpl params = createTestParams();
        params.setCount(2);
        TestSubscriber<Device> testSubscriber = client.getDevices(params).test();
        testSubscriber.assertValueCount(2);
        testSubscriber.assertValueAt(0, new TestNamePredicate("name1"));
        testSubscriber.assertValueAt(1, new TestNamePredicate("name2"));
    }

    @Test
    void testProviderIncludeStringProvider() throws Exception {
        DevicesParamsImpl params = setupProvider(createTestParams(), InclusionType.INCLUDE, "provider");
        TestSubscriber<Device> testSubscriber = client.getDevices(params).test();
        testSubscriber.assertValueCount(3);
        testSubscriber.assertValueAt(0, new ProviderNamePredicate("provider1"));
        testSubscriber.assertValueAt(1, new ProviderNamePredicate("provider2"));
        testSubscriber.assertValueAt(2, new ProviderNamePredicate("provider3"));
    }

    @Test
    void testProviderIncludeString1() throws Exception {
        DevicesParamsImpl params = setupProvider(createTestParams(), InclusionType.INCLUDE, "1");
        TestSubscriber<Device> testSubscriber = client.getDevices(params).test();
        testSubscriber.assertValueCount(1);
        testSubscriber.assertValueAt(0, new ProviderNamePredicate("provider1"));
    }

    @Test
    void testProviderExcludeString1() throws Exception {
        DevicesParamsImpl params = setupProvider(createTestParams(), InclusionType.EXCLUDE, "1");
        TestSubscriber<Device> testSubscriber = client.getDevices(params).test();
        testSubscriber.assertValueCount(3);
        testSubscriber.assertValueAt(0, new ProviderNamePredicate("provider2"));
        testSubscriber.assertValueAt(1, new ProviderNamePredicate("provider3"));
        testSubscriber.assertValueAt(2, new ProviderNamePredicate(null));
    }

    @Test
    void testSerialIncludeSerial1() {
        DevicesParamsImpl params = setupSerial(createTestParams(), InclusionType.INCLUDE, "serial1");
        TestSubscriber<Device> testSubscriber = client.getDevices(params).test();
        testSubscriber.assertValueCount(2);
        testSubscriber.assertValueAt(0, new TestNamePredicate("name1"));
        testSubscriber.assertValueAt(1, new TestNamePredicate("name2"));
    }

    @Test
    void testSerialExcludeSerial1() {
        DevicesParamsImpl params = setupSerial(createTestParams(), InclusionType.EXCLUDE, "serial1");
        TestSubscriber<Device> testSubscriber = client.getDevices(params).test();
        testSubscriber.assertValueCount(2);
        testSubscriber.assertValueAt(0, new TestNamePredicate("name3"));
        testSubscriber.assertValueAt(1, new TestNamePredicate("name4"));
    }

    @Test
    void testSerialIncludeSerial2() {
        DevicesParamsImpl params = setupSerial(createTestParams(), InclusionType.INCLUDE, "serial2");
        TestSubscriber<Device> testSubscriber = client.getDevices(params).test();
        testSubscriber.assertValueCount(1);
        testSubscriber.assertValueAt(0, new TestNamePredicate("name3"));
    }

    @Test
    void testTwoNames() {
        DevicesParamsImpl testParams = createTestParams();
        testParams.setNameFilterDescription(new StringsFilterDescription(InclusionType.INCLUDE, Arrays.asList("1", "2")));
        TestSubscriber<Device> test = client.getDevices(testParams).test();
        test.assertValueCount(2);
        test.assertValueAt(0, new TestNamePredicate("name1"));
        test.assertValueAt(1, new TestNamePredicate("name2"));
    }

    private DevicesParamsImpl setupProvider(DevicesParamsImpl params, InclusionType type, String... s) {
        params.setProviderFilterDescription(new StringsFilterDescription(type, Arrays.asList(s)));
        return params;
    }

    private DevicesParamsImpl setupSerial(DevicesParamsImpl params, InclusionType type, String... s) {
        params.setSerialFilterDescription(new StringsFilterDescription(type, Arrays.asList(s)));
        return params;
    }

    private DevicesParamsImpl createTestParams() {
        DevicesParamsImpl params = new DevicesParamsImpl();
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

        when(devices.get(0).getSerial()).thenReturn("serial1");
        when(devices.get(1).getSerial()).thenReturn("serial1");
        when(devices.get(2).getSerial()).thenReturn("serial2");
        when(devices.get(3).getSerial()).thenReturn("serial3");

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