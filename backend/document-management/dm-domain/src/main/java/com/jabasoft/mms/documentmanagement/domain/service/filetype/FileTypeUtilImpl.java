package com.jabasoft.mms.documentmanagement.domain.service.filetype;


import com.jabasoft.mms.documentmanagement.domain.model.FileType;
import org.springframework.stereotype.Service;

@Service
public class FileTypeUtilImpl implements FileTypeUtil {

	@Override
	public FileType fromString(String fileType){

		return new FileType(fileType);
	}

}
