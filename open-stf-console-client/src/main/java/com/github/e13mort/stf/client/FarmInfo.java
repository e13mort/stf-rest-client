package com.github.e13mort.stf.client;

public class FarmInfo {
    private final String url;
    private final String authKey;
    private final String sdkPath;

    public FarmInfo(String url, String authKey, String sdkPath) {
        this.url = url;
        this.authKey = authKey;
        this.sdkPath = sdkPath;
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
}
