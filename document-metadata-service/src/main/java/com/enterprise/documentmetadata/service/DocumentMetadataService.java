package com.enterprise.documentmetadata.service;

import com.enterprise.documentmetadata.entity.DocumentMetadata;
import com.enterprise.documentmetadata.repository.DocumentMetadataRepository;
import org.springframework.stereotype.Service;

@Service
public class DocumentMetadataService {

    private final DocumentMetadataRepository repository;

    public DocumentMetadataService(DocumentMetadataRepository repository) {
        this.repository = repository;
    }

    public DocumentMetadata save(DocumentMetadata documentMetadata) {
        return repository.save(documentMetadata);
    }
}