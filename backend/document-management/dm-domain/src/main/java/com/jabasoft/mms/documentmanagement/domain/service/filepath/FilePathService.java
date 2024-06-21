package com.jabasoft.mms.documentmanagement.domain.service.filepath;

import com.jabasoft.mms.documentmanagement.domain.model.FilePath;

import java.util.List;

public interface FilePathService {
    List<FilePath> splitPath(FilePath path);
}
