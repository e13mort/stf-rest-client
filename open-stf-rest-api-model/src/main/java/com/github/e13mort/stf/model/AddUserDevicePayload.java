package com.github.e13mort.stf.model;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;


/**
 * payload object for adding device to user
 **/
public class AddUserDevicePayload {

    @SerializedName("serial")
    private String serial = null;

    @SerializedName("timeout")
    private Integer timeout = null;


    /**
     * Device Serial
     **/
    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    /**
     * Device timeout in ms. If device is kept idle for this period, it will be automatically disconnected. Default is provider group timeout
     **/
    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AddUserDevicePayload {\n");

        sb.append("    serial: ").append(toIndentedString(serial)).append("\n");
        sb.append("    timeout: ").append(toIndentedString(timeout)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}
