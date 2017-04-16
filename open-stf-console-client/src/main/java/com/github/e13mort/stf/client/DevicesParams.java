package com.github.e13mort.stf.client;

public class DevicesParams {
    private boolean allDevices;
    private String deviceId;
    private String abi;
    private int osVersion;

    public void setAllDevices(boolean onlyAvailable) {
        this.allDevices = onlyAvailable;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setAbi(String abi) {
        this.abi = abi;
    }

    public void setOsVersion(int osVersion) {
        this.osVersion = osVersion;
    }

    public boolean isAllDevices() {
        return allDevices;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public String getAbi() {
        return abi;
    }

    public int getOsVersion() {
        return osVersion;
    }
}
