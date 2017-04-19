package com.github.e13mort.stf.adapter;

import retrofit2.Response;

public class ResponseException extends Exception {

    private final int code;
    private final String message;

    public ResponseException(Response response) {
        this.code = response.code();
        this.message = response.message();
    }

    public ResponseException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
