package com.jabasoft.mms.documentmanagement.domain.model.error;

public class FileModificationException extends Exception{

    private FileModificationExceptionReason reason;

    public FileModificationException(FileModificationExceptionReason reason) {
        this.reason = reason;
    }

    public FileModificationException(Exception reason) {
        super(reason);
        this.reason = FileModificationExceptionReason.OTHER_EXCEPTION;
    }

    public FileModificationExceptionReason getReason() {
        return reason;
    }
}
