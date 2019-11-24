package com.kalher.movietime.datasource;

public class ResponseMessage {

    public enum Status {
        STATE_LOADING,

        STATE_SUCCESS,

        STATE_FAILED

        ;
    }

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
