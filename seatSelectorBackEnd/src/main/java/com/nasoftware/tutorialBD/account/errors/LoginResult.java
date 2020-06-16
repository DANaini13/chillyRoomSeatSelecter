package com.nasoftware.tutorialBD.account.errors;

public class LoginResult {
    private final String token;

    public String getToken() {
        return token;
    }

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }

    private final ErrorInfo errorInfo;

    public LoginResult(String token, ErrorInfo errorInfo) {
        this.token = token;
        this.errorInfo = errorInfo;
    }
}
