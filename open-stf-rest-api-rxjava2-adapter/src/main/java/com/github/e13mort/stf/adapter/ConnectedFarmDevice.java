package com.github.e13mort.stf.adapter;

import io.reactivex.annotations.NonNull;

public class ConnectedFarmDevice {

    private final @NonNull
    String serial;
    private final @NonNull
    String remoteConnectUrl;

    public ConnectedFarmDevice(@NonNull String serial, @NonNull String remoteConnectUrl) {
        this.serial = serial;
        this.remoteConnectUrl = remoteConnectUrl;
    }

    @NonNull
    public String getSerial() {
        return serial;
    }

    @NonNull
    public String getRemoteConnectUrl() {
        return remoteConnectUrl;
    }
}
