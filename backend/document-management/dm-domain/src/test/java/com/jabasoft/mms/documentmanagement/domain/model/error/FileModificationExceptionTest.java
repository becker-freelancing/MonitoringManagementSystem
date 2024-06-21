package com.jabasoft.mms.documentmanagement.domain.model.error;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileModificationExceptionTest {

    @Test
    void testFileModificationExceptionSavesModificationExceptionReason(){
        FileModificationException exception = new FileModificationException(FileModificationExceptionReason.FILE_EXISTS);

        assertEquals(FileModificationExceptionReason.FILE_EXISTS, exception.getReason());
    }

}