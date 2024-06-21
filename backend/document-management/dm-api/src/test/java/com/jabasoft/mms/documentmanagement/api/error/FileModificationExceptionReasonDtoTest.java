package com.jabasoft.mms.documentmanagement.api.error;

import com.jabasoft.mms.documentmanagement.error.FileModificationExceptionReasonDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileModificationExceptionReasonDtoTest {


    @Test
    void testEnumHasCorrectNumberOfValues(){

        assertEquals(5, FileModificationExceptionReasonDto.values().length);
    }
}