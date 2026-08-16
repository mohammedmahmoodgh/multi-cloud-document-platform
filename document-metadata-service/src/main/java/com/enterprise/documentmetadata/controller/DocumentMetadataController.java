package com.enterprise.documentmetadata.controller;

import com.enterprise.documentmetadata.entity.DocumentMetadata;
import com.enterprise.documentmetadata.service.DocumentMetadataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/documents")
public class DocumentMetadataController {

    private final DocumentMetadataService service;

    public DocumentMetadataController(DocumentMetadataService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DocumentMetadata> create(
            @RequestBody DocumentMetadata documentMetadata) {

        DocumentMetadata savedDocument = service.save(documentMetadata);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedDocument);
    }
}
