package com.jabasoft.mms.documentmanagement.domain.service.filepath;

import com.jabasoft.mms.documentmanagement.domain.model.FilePath;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FilePathServiceImpl implements FilePathService {

    @Override
    public List<FilePath> splitPath(FilePath path){
        String[] filePathParts = path.getFilePath().split("\\\\");

        List<FilePath> mappedFilePathParts = new ArrayList<>();
        for (String filePathPart : filePathParts) {
            mappedFilePathParts.add(new FilePath(filePathPart));
        }

        return mappedFilePathParts;
    }

}
