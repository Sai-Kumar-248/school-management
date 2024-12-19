package com.schoolmanagement.dto;

public class ValidUser {

    boolean isValidUser=false;
    private String message=null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isValidUser() {
        return isValidUser;
    }

    public void setValidUser(boolean validUser) {
        isValidUser = validUser;
    }
}
