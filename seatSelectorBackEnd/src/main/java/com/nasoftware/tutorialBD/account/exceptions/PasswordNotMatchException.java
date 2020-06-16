package com.nasoftware.tutorialBD.account.exceptions;

public class PasswordNotMatchException extends Exception {
    public String getLocalizedErrorMessage() {
        return "传入的用户名正确，密码错误";
    }
}
