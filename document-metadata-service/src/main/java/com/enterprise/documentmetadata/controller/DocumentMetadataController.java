package com.enterprise.documentmetadata.controller;

import com.enterprise.documentmetadata.entity.DocumentMetadata;
import com.enterprise.documentmetadata.service.DocumentMetadataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.enterprise.documentmetadata.dto.DocumentMetadataRequest;
import jakarta.validation.Valid;
import com.enterprise.documentmetadata.mapper.DocumentMetadataMapper;
import com.enterprise.documentmetadata.dto.CloudCopyRequest;

@RestController
@RequestMapping("/api/documents")
public class DocumentMetadataController {

    private final DocumentMetadataService service;
    private final DocumentMetadataMapper mapper;

    public DocumentMetadataController(
            DocumentMetadataService service,
            DocumentMetadataMapper mapper) {

        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<DocumentMetadata> create(
            @Valid @RequestBody DocumentMetadataRequest request) {
        DocumentMetadata documentMetadata = mapper.toEntity(request);
        DocumentMetadata savedDocument = service.save(documentMetadata);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedDocument);
    }

    @GetMapping
    public ResponseEntity<List<DocumentMetadata>> getAll() {

        List<DocumentMetadata> documents = service.getAll();

        return ResponseEntity.ok(documents);
    }


    @GetMapping("/{id}")
    public ResponseEntity<DocumentMetadata> getById(@PathVariable Long id) {

        DocumentMetadata document = service.getById(id);

        return ResponseEntity.ok(document);
    }




    @PutMapping("/{id}")
    public ResponseEntity<DocumentMetadata> update(
            @PathVariable Long id,
            @Valid @RequestBody DocumentMetadataRequest request) {

        DocumentMetadata document = service.update(id, request);

        return ResponseEntity.ok(document);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        service.delete(id);

        return ResponseEntity
                .ok("Document deleted successfully with id: " + id);
    }

    @PostMapping("/{documentCode}/copies")
    public ResponseEntity<DocumentMetadata> addCloudCopy(
            @PathVariable String documentCode,
            @Valid @RequestBody CloudCopyRequest request) {

        DocumentMetadata savedCopy =
                service.addCloudCopy(documentCode, request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedCopy);
    }

    @GetMapping("/{documentCode}/copies")
    public ResponseEntity<List<DocumentMetadata>> getCopiesByDocumentCode(
            @PathVariable String documentCode) {

        List<DocumentMetadata> copies =
                service.getCopiesByDocumentCode(documentCode);

        return ResponseEntity.ok(copies);
    }




}