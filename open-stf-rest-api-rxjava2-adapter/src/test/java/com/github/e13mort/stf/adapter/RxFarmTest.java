package com.github.e13mort.stf.adapter;

import com.github.e13mort.stf.ApiClient;
import com.github.e13mort.stf.api.DevicesApi;
import com.github.e13mort.stf.api.UserApi;
import com.github.e13mort.stf.model.device.Device;
import io.reactivex.observers.TestObserver;
import io.reactivex.subscribers.TestSubscriber;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static com.github.e13mort.stf.adapter.filters.Filter.abi;
import static com.github.e13mort.stf.adapter.filters.Filter.api;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Tag("integration")
public class RxFarmTest {

    static String baseUrl;
    static String apiKey;
    static String testDeviceSerial;

    @BeforeAll
    public static void initProperties() throws IOException {
        //mock this

        final Properties properties = new Properties();
        properties.load(new FileReader("../stf.properties"));
        baseUrl = properties.getProperty("stf.url");
        apiKey = properties.getProperty("stf.key");
        testDeviceSerial = properties.getProperty("stf.device_serial");
        assertNotNull(baseUrl);
        assertNotNull(apiKey);
        assertNotNull(testDeviceSerial);
    }

    private RxFarm rxFarm;

    @BeforeEach
    public void setUp() throws Exception {
        ApiClient apiClient = new ApiClient(baseUrl, apiKey);
        rxFarm = new RxFarm(apiClient.createService(DevicesApi.class), apiClient.createService(UserApi.class));
    }

    @Test
    public void tesGetDevice() throws Exception {
        TestObserver<Device> device = rxFarm.getDevice(testDeviceSerial).test();
        device.assertNoErrors();
        device.assertValueCount(1);
    }

    @Test
    public void testAllDevices() throws Exception {
        TestSubscriber<Device> subscriber = rxFarm.getAllDevices().test();
        subscriber.assertComplete();
        assertFalse(subscriber.values().isEmpty());
    }

    @Test
    public void testFilterByAbiDoesNotEmmitError() throws Exception {
        TestSubscriber<Device> subscriber = rxFarm.getAllDevices()
                .filter(abi("armeabi-v7a"))
                .test();
        subscriber.assertNoErrors();
        print(subscriber);
    }

    @Test
    public void testFilterByApiLevelDoesNotEmmitError() throws Exception {
        TestSubscriber<Device> subscriber = rxFarm.getAllDevices()
                .filter(api(21)).test();
        subscriber.assertNoErrors();
        print(subscriber);
    }

    private void print(TestSubscriber<Device> subscriber) {
        for (Device device : subscriber.values()) {
            System.out.println(device);
        }
    }

}