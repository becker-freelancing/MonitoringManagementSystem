package com.jabasoft.mms.documentmanagement.api.error;

import com.jabasoft.mms.documentmanagement.error.ApiFileModificationException;
import com.jabasoft.mms.documentmanagement.error.FileModificationExceptionReasonDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiFileModificationExceptionTest {


    @Test
    void testFileModificationExceptionSavesModificationExceptionReason(){
        ApiFileModificationException exception = new ApiFileModificationException(FileModificationExceptionReasonDto.FILE_EXISTS);

        assertEquals(FileModificationExceptionReasonDto.FILE_EXISTS, exception.getReason());
    }
}