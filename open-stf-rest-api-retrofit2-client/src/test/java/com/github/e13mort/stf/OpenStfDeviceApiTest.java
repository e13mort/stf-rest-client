package com.github.e13mort.stf;

import com.github.e13mort.stf.api.DevicesApi;
import com.github.e13mort.stf.model.DeviceListResponse;
import com.github.e13mort.stf.model.DeviceResponse;
import com.github.e13mort.stf.model.device.Device;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.assertNotNull;

/**
 * Created by pavel
 */
public class OpenStfDeviceApiTest extends BaseOpenStfApiTest {
    private DevicesApi deviceApi;

    @BeforeClass
    public static void init() throws IOException {
        initProperties();
    }

    @Before
    public void setUp() throws Exception {
        final ApiClient apiClient = new ApiClient(baseUrl, apiKey);
        deviceApi = apiClient.createService(DevicesApi.class);
    }

    @Test
    public void testDeviceLists() throws Exception {
        final Call<DeviceListResponse> devices = deviceApi.getDevices("");
        final Response<DeviceListResponse> response = devices.execute();
        final DeviceListResponse body = response.body();
        assertNotNull(body);
    }

    @Test
    public void testGetDevice() throws Exception {
        final Call<DeviceResponse> call = deviceApi.getDeviceBySerial(testDeviceSerial, "");
        final Response<DeviceResponse> response = call.execute();
        final DeviceResponse body = response.body();
        assertNotNull(body);
        final Device device = body.getDevice();
        assertNotNull(device);
    }
}