package com.github.e13mort.stf.client.parameters;

import com.github.e13mort.stf.adapter.filters.StringsFilterDescription;

public interface DevicesParams {
    boolean isAllDevices();

    String getAbi();

    int getApiVersion();

    int getCount();

    StringsFilterDescription getNameFilterDescription();

    int getMinApiVersion();

    int getMaxApiVersion();

    StringsFilterDescription getProviderFilterDescription();

    StringsFilterDescription getSerialFilterDescription();
}
