package com.nasoftware.tutorialBD.account.exceptions;

public class AccountNotFoundException extends Exception {
    public String getLocalizedErrorMessage() {
        return "找不到用户名";
    }
}
