package com.jabasoft.mms.documentmanagement.domain.model.error;

public enum FileModificationExceptionReason {
    FILE_DOES_NOT_EXIST,
    FILE_EXISTS,
    FILE_HAS_CHILDREN,
    FILE_HAS_NO_CHILDREN,
    OTHER_EXCEPTION
}
