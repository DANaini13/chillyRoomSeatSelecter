package com.nasoftware.tutorialBD.account.exceptions;

public class AccountAlreadyExistException extends Exception{
    public String getLocalizedErrorMessage() {
        return "账号已经存在了";
    }
}
