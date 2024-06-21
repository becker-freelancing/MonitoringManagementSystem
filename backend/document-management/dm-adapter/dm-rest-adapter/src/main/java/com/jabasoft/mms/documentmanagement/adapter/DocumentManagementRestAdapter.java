package com.jabasoft.mms.documentmanagement.adapter;

import com.jabasoft.mms.documentmanagement.api.DocumentManagementPort;
import com.jabasoft.mms.documentmanagement.dto.DocumentDto;
import com.jabasoft.mms.documentmanagement.dto.DocumentWithoutContentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mms/api/documents")
public class DocumentManagementRestAdapter {

    private DocumentManagementPort documentManagementPort;

    @Autowired
    public DocumentManagementRestAdapter(DocumentManagementPort documentManagementPort) {
        this.documentManagementPort = documentManagementPort;
    }



    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/save")
    public ResponseEntity<DocumentDto> saveDocument(@RequestBody DocumentDto document){
        Optional<DocumentDto> documentDto = documentManagementPort.saveDocument(document);
        return documentDto.map(ResponseEntity::ok).orElse(ResponseEntity.internalServerError().build());
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/all")
    public ResponseEntity<List<DocumentDto>> getAllDocuments(){
        List<DocumentDto> allDocuments = documentManagementPort.getAllDocuments();
        return ResponseEntity.ok(allDocuments);
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/{id}")
    public ResponseEntity<DocumentDto> getDocument(@PathVariable("id") Long documentId){
        Optional<DocumentDto> document = documentManagementPort.getDocument(documentId);
        return document.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping
    public ResponseEntity<DocumentDto> deleteDocument(@RequestBody DocumentWithoutContentDto document){
        Optional<DocumentDto> deleted = documentManagementPort.deleteDocument(document.getPathToDocumentFromRoot(), document.getDocumentName());
        return deleted.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
