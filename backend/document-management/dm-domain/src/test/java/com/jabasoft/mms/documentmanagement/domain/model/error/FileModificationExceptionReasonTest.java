package com.jabasoft.mms.documentmanagement.domain.model.error;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileModificationExceptionReasonTest {

    @Test
    void testEnumHasCorrectNumberOfValues(){

        assertEquals(5, FileModificationExceptionReason.values().length);
    }
}