package com.github.e13mort.stf.client;

import com.github.e13mort.stf.adapter.filters.StringsFilterDescription;

import java.util.List;

public class DevicesParams {
    private boolean allDevices;
    private String deviceId;
    private String abi;
    private int apiVersion;
    private int minApiVersion;
    private int maxApiVersion;
    private int count;
    private List<String> name;
    private StringsFilterDescription stringsFilterDescription;

    public void setAllDevices(boolean onlyAvailable) {
        this.allDevices = onlyAvailable;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public void setAbi(String abi) {
        this.abi = abi;
    }

    public void setApiVersion(int apiVersion) {
        this.apiVersion = apiVersion;
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

    public int getApiVersion() {
        return apiVersion;
    }

    public int getCount() {
        return count;
    }

    public List<String> getNames() {
        return name;
    }

    public void setNames(List<String> name) {
        this.name = name;
    }

    public void setMinApiVersion(int minApiVersion) {
        this.minApiVersion = minApiVersion;
    }

    public void setMaxApiVersion(int maxApiVersion) {
        this.maxApiVersion = maxApiVersion;
    }

    public int getMinApiVersion() {
        return minApiVersion;
    }

    public int getMaxApiVersion() {
        return maxApiVersion;
    }

    public StringsFilterDescription getStringsFilterDescription() {
        return stringsFilterDescription;
    }

    public void setStringsFilterDescription(StringsFilterDescription stringsFilterDescription) {
        this.stringsFilterDescription = stringsFilterDescription;
    }
}
