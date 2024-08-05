package com.jabasoft.mms.documentmanagement.adapter;

import com.jabasoft.mms.documentmanagement.api.DocumentManagementPort;
import com.jabasoft.mms.documentmanagement.dto.DocumentDto;
import com.jabasoft.mms.documentmanagement.dto.DocumentWithoutContentDto;
import com.jabasoft.mms.documentmanagement.dto.TagDto;
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
    public ResponseEntity<DocumentWithoutContentDto> deleteDocument(@RequestBody DocumentWithoutContentDto document) {
        Optional<DocumentWithoutContentDto> deleted = documentManagementPort.deleteDocument(document.getPathToDocumentFromRoot(), document.getDocumentName());
        return deleted.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/exists")
    public ResponseEntity<Boolean> existsDocument(@RequestBody DocumentWithoutContentDto document) {
        boolean exists = documentManagementPort.existsDocument(document);
        return ResponseEntity.ok(exists);
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/customer")
    public ResponseEntity<DocumentDto> setCustomer(@RequestBody SetCustomerRequestBody body) {

        Optional<DocumentDto> documentDto = documentManagementPort.setCustomer(body.getDocumentId(), body.getCustomerId());

        return documentDto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/customer")
    public ResponseEntity<DocumentDto> resetCustomer(@RequestBody Long documentId) {
        Optional<DocumentDto> documentDto = documentManagementPort.resetCustomer(documentId);

        return documentDto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/tags")
    public ResponseEntity<DocumentDto> addTag(@RequestBody AddDeleteTagRequestBody body) {
        Optional<DocumentDto> documentDto = documentManagementPort.addTag(body.getDocumentId(), new TagDto(body.getTag()));

        return documentDto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/tags")
    public ResponseEntity<DocumentDto> removeTag(@RequestBody AddDeleteTagRequestBody body) {
        Optional<DocumentDto> documentDto = documentManagementPort.removeTag(body.getDocumentId(), new TagDto(body.getTag()));

        return documentDto.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
}
