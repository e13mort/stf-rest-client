package com.github.e13mort.stf.api;


import com.github.e13mort.stf.model.DeviceListResponse;
import com.github.e13mort.stf.model.DeviceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface DevicesApi {

    /**
     * Device Information
     * The device enpoint return information about a single device
     *
     * @param serial Device Serial (required)
     * @param fields Fields query parameter takes a comma seperated list of fields. Only listed field will be return in response (optional)
     * @return Call<DeviceResponse>
     */

    @GET("devices/{serial}")
    Call<DeviceResponse> getDeviceBySerial(
            @Path("serial") String serial, @Query("fields") String fields
    );


    /**
     * Device List
     * The devices endpoint return list of all the STF devices including Disconnected and Offline
     *
     * @param fields Fields query parameter takes a comma seperated list of fields. Only listed field will be return in response (optional)
     * @return Call<DeviceListResponse>
     */

    @GET("devices")
    Call<DeviceListResponse> getDevices(
            @Query("fields") String fields
    );


}
