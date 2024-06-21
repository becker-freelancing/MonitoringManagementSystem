package com.jabasoft.mms.documentmanagement.error;

public class ApiFileModificationException extends Exception{

    private FileModificationExceptionReasonDto reason;

    public ApiFileModificationException(FileModificationExceptionReasonDto reason) {
        this.reason = reason;
    }

    public FileModificationExceptionReasonDto getReason() {
        return reason;
    }
}
