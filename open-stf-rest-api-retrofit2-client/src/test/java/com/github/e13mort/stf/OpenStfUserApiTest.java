package com.github.e13mort.stf;

import com.github.e13mort.stf.api.UserApi;
import com.github.e13mort.stf.model.AccessTokensResponse;
import com.github.e13mort.stf.model.AddUserDevicePayload;
import com.github.e13mort.stf.model.DeviceListResponse;
import com.github.e13mort.stf.model.UserResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Created by pavel
 */
@Tag("integration")
public class OpenStfUserApiTest extends BaseOpenStfApiTest {
    private static final int TEST_TIMEOUT = 60000;
    private UserApi userApi;

    @BeforeAll
    public static void init() throws IOException {
        initProperties();
    }

    @BeforeEach
    public void setUp() throws Exception {
        final ApiClient apiClient = new ApiClient(baseUrl, apiKey);
        userApi = apiClient.createService(UserApi.class);
    }

    @Test
    public void testUser() throws Exception {
        final Call<UserResponse> devices = userApi.getUser();
        final Response<UserResponse> response = devices.execute();
        final UserResponse body = response.body();
        assertNotNull(body);
        assertTrue(response.isSuccessful());
    }

    @Test
    public void testGetUserAccessToken() throws Exception {
        final Response<AccessTokensResponse> response = userApi.getUserAccessTokens().execute();
        final AccessTokensResponse body = response.body();
        assertNotNull(body);
        assertTrue(response.isSuccessful());
    }

    @Test
    public void testGetUserDevices() throws Exception {
        final Response<DeviceListResponse> response = userApi.getUserDevices("").execute();
        final DeviceListResponse body = response.body();
        assertNotNull(body);
        assertTrue(response.isSuccessful());
    }

    @Test
    public void testConnectionFlow() throws Exception {
        testResponse(userApi.addUserDevice(getAddUserDevicePayload()).execute());
        testResponse(userApi.remoteConnectUserDeviceBySerial(testDeviceSerial).execute());
        testResponse(userApi.remoteDisconnectUserDeviceBySerial(testDeviceSerial).execute());
        testResponse(userApi.deleteUserDeviceBySerial(testDeviceSerial).execute());
    }

    private void testResponse(Response<? extends Object> response) {
        assertTrue(response.isSuccessful());
    }

    private AddUserDevicePayload getAddUserDevicePayload() {
        final AddUserDevicePayload device = new AddUserDevicePayload();
        device.setSerial(testDeviceSerial);
        device.setTimeout(TEST_TIMEOUT);
        return device;
    }
}