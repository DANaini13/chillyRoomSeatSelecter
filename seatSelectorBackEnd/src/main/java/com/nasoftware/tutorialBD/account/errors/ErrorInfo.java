package com.nasoftware.tutorialBD.account.errors;

public class ErrorInfo {
    /*=================== error code below ================== */
    static public final long success = 0;
    static public final long unknown_error = 1;
    static public final long pass_not_match = 2;
    static public final long account_not_found = 3;
    static public final long account_already_exist = 4;
    static public final long seat_already_token = 5;

    /*=================== error msg below ================== */
    static public final String success_msg = "success";

    private long errorCode = success;

    public long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(long errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    private String errorMsg = "";

    public ErrorInfo(long errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
