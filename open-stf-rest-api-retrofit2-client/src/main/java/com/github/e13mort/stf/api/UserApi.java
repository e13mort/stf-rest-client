package com.github.e13mort.stf.api;


import com.github.e13mort.stf.model.AccessTokensResponse;
import com.github.e13mort.stf.model.AddUserDevicePayload;
import com.github.e13mort.stf.model.DeviceListResponse;
import com.github.e13mort.stf.model.DeviceResponse;
import com.github.e13mort.stf.model.RemoteConnectUserDeviceResponse;
import com.github.e13mort.stf.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface UserApi {

    /**
     * Add a device to a user
     * The User Devices endpoint will request stf server for a new device.
     *
     * @param device Device to add (required)
     * @return Call<Void>
     */

    @POST("user/devices")
    Call<Object> addUserDevice(
            @Body AddUserDevicePayload device
    );


    /**
     * Delete User Device
     * The User Devices endpoint will request for device release from stf server. It will return request accepted if device is being used by current user
     *
     * @param serial Device Serial (required)
     * @return Call<Void>
     */

    @DELETE("user/devices/{serial}")
    Call<Object> deleteUserDeviceBySerial(
            @Path("serial") String serial
    );


    /**
     * User Profile
     * The User Profile endpoint returns information about current authorized user
     *
     * @return Call<UserResponse>
     */

    @GET("user")
    Call<UserResponse> getUser();


    /**
     * Access Tokens
     * The Access Tokens endpoints returns titles of all the valid access tokens
     *
     * @return Call<AccessTokensResponse>
     */

    @GET("user/accessTokens")
    Call<AccessTokensResponse> getUserAccessTokens();


    /**
     * User Device
     * The devices enpoint return information about device owned by user
     *
     * @param serial Device Serial (required)
     * @param fields Fields query parameter takes a comma seperated list of fields. Only listed field will be return in response (optional)
     * @return Call<DeviceResponse>
     */

    @GET("user/devices/{serial}")
    Call<DeviceResponse> getUserDeviceBySerial(
            @Path("serial") String serial, @Query("fields") String fields
    );


    /**
     * User Devices
     * The User Devices endpoint returns device list owner by current authorized user
     *
     * @param fields Fields query parameter takes a comma seperated list of fields. Only listed field will be return in response (optional)
     * @return Call<DeviceListResponse>
     */

    @GET("user/devices")
    Call<DeviceListResponse> getUserDevices(
            @Query("fields") String fields
    );


    /**
     * Remote Connect
     * The device connect endpoint will request stf server to connect remotely
     *
     * @param serial Device Serial (required)
     * @return Call<RemoteConnectUserDeviceResponse>
     */

    @Headers("Content-Type: application/json")
    @POST("user/devices/{serial}/remoteConnect")
    Call<RemoteConnectUserDeviceResponse> remoteConnectUserDeviceBySerial(
            @Path("serial") String serial
    );


    /**
     * Remote Disconnect
     * The device connect endpoint will request stf server to disconnect remotely
     *
     * @param serial Device Serial (required)
     * @return Call<Void>
     */

    @DELETE("user/devices/{serial}/remoteConnect")
    Call<Object> remoteDisconnectUserDeviceBySerial(
            @Path("serial") String serial
    );


}
