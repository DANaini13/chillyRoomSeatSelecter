package com.nasoftware.tutorialBD.account.exceptions;

public class SeatAlreadyTokenException extends Exception{
    public String getLocalizedErrorMessage() {
        return "座位已经被占用了";
    }
}
