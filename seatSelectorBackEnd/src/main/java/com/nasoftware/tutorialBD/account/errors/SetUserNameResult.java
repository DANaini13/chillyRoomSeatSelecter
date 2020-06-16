package com.nasoftware.tutorialBD.account.errors;

public class SetUserNameResult {
    public SetUserNameResult(ErrorInfo errorInfo, int userId) {
        this.errorInfo = errorInfo;
        this.userId = userId;
    }

    private final ErrorInfo errorInfo;

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }

    public int getUserId() {
        return userId;
    }

    private final int userId;
}