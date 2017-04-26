package com.github.e13mort.stf.client;

public class DevicesParams {
    private boolean allDevices;
    private String deviceId;
    private String abi;
    private int osVersion;
    private int count;
    private String name;

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

    public void setCount(int count) {
        this.count = count;
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

    public int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
