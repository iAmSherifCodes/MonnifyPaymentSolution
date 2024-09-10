package com.wallet.monnify.security.exceptions;

public enum ExceptionMessages {
    AUTHENTICATION_NOT_SUPPORTED("Authentication not supported on this system"),
    INVALID_CREDENTIALS_EXCEPTION("Invalid  login parameters"),
    USER_WITH_EMAIL_NOT_FOUND("No user with email %s"),
    COHORT_NOT_FOUND("No cohort named %s"),
    PROGRAM_NOT_FOUND("Program not found")

    ;

    private String message;

    ExceptionMessages(String message){
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

}
