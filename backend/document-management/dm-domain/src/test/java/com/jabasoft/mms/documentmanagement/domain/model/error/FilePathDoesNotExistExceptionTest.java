package com.jabasoft.mms.documentmanagement.domain.model.error;

import com.jabasoft.mms.documentmanagement.domain.model.FilePath;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FilePathDoesNotExistExceptionTest {

    @Test
    void testExceptionSavesFilePath(){
        FilePath filePath = new FilePath("a/b/c");

        FilePathDoesNotExistException exception = new FilePathDoesNotExistException(filePath);

        assertEquals(filePath, exception.getFilePath());
    }
}