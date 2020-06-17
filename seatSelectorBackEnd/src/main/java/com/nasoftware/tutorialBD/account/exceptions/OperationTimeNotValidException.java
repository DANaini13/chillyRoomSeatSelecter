package com.nasoftware.tutorialBD.account.exceptions;

public class OperationTimeNotValidException extends Exception{
    public String getLocalizedErrorMessage() {
        return "当前不是该用户的可操作时间范围";
    }
}
