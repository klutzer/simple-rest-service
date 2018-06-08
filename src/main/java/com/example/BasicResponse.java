package com.example;

/**
 * @author erico.lutzer
 *
 */
public class BasicResponse {

    private boolean success;
    private String message;

    public BasicResponse() {
    }

    public BasicResponse(boolean success, String message) {
        super();
        this.success = success;
        this.message = message;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
