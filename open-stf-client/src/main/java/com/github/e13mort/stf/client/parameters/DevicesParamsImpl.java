package com.github.e13mort.stf.client.parameters;

import com.github.e13mort.stf.adapter.filters.StringsFilterDescription;

public class DevicesParamsImpl implements DevicesParams {
    private boolean allDevices;
    private String abi;
    private int apiVersion;
    private int minApiVersion;
    private int maxApiVersion;
    private int count;
    private StringsFilterDescription nameFilterDescription;
    private StringsFilterDescription providerFilterDescription;
    private StringsFilterDescription serialFilterDescription;

    public void setAllDevices(boolean onlyAvailable) {
        this.allDevices = onlyAvailable;
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

    @Override
    public boolean isAllDevices() {
        return allDevices;
    }

    @Override
    public String getAbi() {
        return abi;
    }

    @Override
    public int getApiVersion() {
        return apiVersion;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public StringsFilterDescription getNameFilterDescription() {
        return nameFilterDescription;
    }

    public void setNameFilterDescription(StringsFilterDescription nameFilterDescription) {
        this.nameFilterDescription = nameFilterDescription;
    }

    public void setMinApiVersion(int minApiVersion) {
        this.minApiVersion = minApiVersion;
    }

    public void setMaxApiVersion(int maxApiVersion) {
        this.maxApiVersion = maxApiVersion;
    }

    @Override
    public int getMinApiVersion() {
        return minApiVersion;
    }

    @Override
    public int getMaxApiVersion() {
        return maxApiVersion;
    }

    @Override
    public StringsFilterDescription getProviderFilterDescription() {
        return providerFilterDescription;
    }

    public void setProviderFilterDescription(StringsFilterDescription providerFilterDescription) {
        this.providerFilterDescription = providerFilterDescription;
    }

    @Override
    public StringsFilterDescription getSerialFilterDescription() {
        return serialFilterDescription;
    }

    public void setSerialFilterDescription(StringsFilterDescription serialFilterDescription) {
        this.serialFilterDescription = serialFilterDescription;
    }
}
