package com.github.e13mort.stf.client;

public class FarmInfo {
    private final String url;
    private final String authKey;
    private final String sdkPath;
    private final int timeoutSec;

    public FarmInfo(String url, String authKey, String sdkPath, int timeoutSec) {
        this.url = url;
        this.authKey = authKey;
        this.sdkPath = sdkPath;
        this.timeoutSec = timeoutSec;
    }

    String getUrl() {
        return url;
    }

    String getAuthKey() {
        return authKey;
    }

    public String getSdkPath() {
        return sdkPath;
    }

    public int getTimeoutSec() {
        return timeoutSec;
    }
}
