package com.jabasoft.mms.documentmanagement.domain.model.error;

import com.jabasoft.mms.documentmanagement.domain.model.FilePath;

public class FilePathDoesNotExistException extends RuntimeException {

    private FilePath filePath;

    public FilePathDoesNotExistException(FilePath filePath) {
        this.filePath = filePath;
    }

    public FilePath getFilePath() {
        return filePath;
    }
}
