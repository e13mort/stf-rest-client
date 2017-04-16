package com.github.e13mort.stf.client;

public class FarmInfo {
    private final String url;
    private final String authKey;

    public FarmInfo(String url, String authKey) {
        this.url = url;
        this.authKey = authKey;
    }

    String getUrl() {
        return url;
    }

    String getAuthKey() {
        return authKey;
    }


}
