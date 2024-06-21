package com.jabasoft.mms.documentmanagement.filepath.adapter;

import com.jabasoft.mms.documentmanagement.filepath.api.FilePathManagementPort;
import com.jabasoft.mms.documentmanagement.dto.FilePathDto;
import com.jabasoft.mms.documentmanagement.dto.FileStructureDto;
import com.jabasoft.mms.documentmanagement.dto.FileStructureWithDocumentsDto;
import com.jabasoft.mms.documentmanagement.error.ApiFileModificationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/mms/api/files/paths")
public class FilePathManagementRestAdapter {

    private FilePathManagementPort filePathManagementPort;

    @Autowired
    public FilePathManagementRestAdapter(FilePathManagementPort filePathManagementPort) {
        this.filePathManagementPort = filePathManagementPort;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/filestructure")
    public ResponseEntity<FileStructureDto> getFileStructure(){
        Optional<FileStructureDto> fileStructure = filePathManagementPort.getFileStructure();
        return fileStructure.map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("filestructurewithdocuments")
    public ResponseEntity<FileStructureWithDocumentsDto> getFileStructureWithDocuments(){
        Optional<FileStructureWithDocumentsDto> fileStructureWithDocuments = filePathManagementPort.getFileStructureWithDocuments();
        return fileStructureWithDocuments.map(ResponseEntity::ok).orElse(ResponseEntity.noContent().build());
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("filestructure")
    public ResponseEntity<FileStructureDto> createFileStructure(@RequestBody FilePathDto path){
        try {
            Optional<FileStructureDto> fileStructure = filePathManagementPort.createFileStructure(path);
            return fileStructure.map(ResponseEntity::ok).orElse(ResponseEntity.internalServerError().build());
        } catch (ApiFileModificationException e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("filestructure")
    public ResponseEntity<FileStructureDto> deleteFileStructure(@RequestBody FilePathDto path){
        try {
            Optional<FileStructureDto> fileStructure = filePathManagementPort.deleteFileStructure(path);
            return fileStructure.map(ResponseEntity::ok).orElse(ResponseEntity.internalServerError().build());
        } catch (ApiFileModificationException e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("filestructurewithchildren")
    public ResponseEntity<FileStructureDto> deleteFileStructureWithChildren(@RequestBody FilePathDto pathDto){
        try {
            Optional<FileStructureDto> fileStructure = filePathManagementPort.deleteFileStructureWithChildren(pathDto);
            return fileStructure.map(ResponseEntity::ok).orElse(ResponseEntity.internalServerError().build());
        } catch (ApiFileModificationException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
