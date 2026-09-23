package com.enterprise.documentmetadata.service;

import com.enterprise.documentmetadata.entity.DocumentMetadata;
import com.enterprise.documentmetadata.repository.DocumentMetadataRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import com.enterprise.documentmetadata.exception.DuplicateDocumentException;
import com.enterprise.documentmetadata.exception.DocumentNotFoundException;
import com.enterprise.documentmetadata.dto.DocumentMetadataRequest;
import com.enterprise.documentmetadata.mapper.DocumentMetadataMapper;
import com.enterprise.documentmetadata.generator.DocumentCodeGenerator;
import com.enterprise.documentmetadata.dto.CloudCopyRequest;

@Service
public class DocumentMetadataService {

    private final DocumentMetadataRepository repository;
    private final DocumentMetadataMapper mapper;
    private final DocumentCodeGenerator documentCodeGenerator;

    public DocumentMetadataService(
            DocumentMetadataRepository repository,
            DocumentMetadataMapper mapper,
            DocumentCodeGenerator documentCodeGenerator) {

        this.repository = repository;
        this.mapper = mapper;
        this.documentCodeGenerator = documentCodeGenerator;
    }

    public DocumentMetadata save(DocumentMetadata documentMetadata) {


        boolean exists =
                repository.existsByProviderAndExternalFileId(
                        documentMetadata.getProvider(),
                        documentMetadata.getExternalFileId()
                );

        if (exists) {
            throw new DuplicateDocumentException(
                    "Document already exists for provider "
                            + documentMetadata.getProvider()
                            + " with external file ID "
                            + documentMetadata.getExternalFileId()
            );
        }

        documentMetadata.setDocumentCode(documentCodeGenerator.generate());
        return repository.save(documentMetadata);
    }

    public DocumentMetadata addCloudCopy( String documentCode,  CloudCopyRequest request) {

        DocumentMetadata existingDocument =
                repository.findFirstByDocumentCode(documentCode)
                        .orElseThrow(() ->
                                new DocumentNotFoundException(
                                        "Document not found with code: " + documentCode
                                )
                        );

        boolean providerAlreadyExists =
                repository.existsByDocumentCodeAndProvider(
                        documentCode,
                        request.getProvider()
                );

        if (providerAlreadyExists) {
            throw new DuplicateDocumentException(
                    "Document already has a copy on provider: "
                            + request.getProvider()
            );
        }

        boolean externalFileAlreadyExists =
                repository.existsByProviderAndExternalFileId(
                        request.getProvider(),
                        request.getExternalFileId()
                );

        if (externalFileAlreadyExists) {
            throw new DuplicateDocumentException(
                    "Cloud file already exists for provider "
                            + request.getProvider()
                            + " with external file ID "
                            + request.getExternalFileId()
            );
        }

        DocumentMetadata cloudCopy = new DocumentMetadata();

        cloudCopy.setDocumentCode(existingDocument.getDocumentCode());
        cloudCopy.setProvider(request.getProvider());
        cloudCopy.setExternalFileId(request.getExternalFileId());
        cloudCopy.setFileName(existingDocument.getFileName());
        cloudCopy.setContentType(request.getContentType());
        cloudCopy.setSize(request.getSize());
        cloudCopy.setOwnerId(request.getOwnerId());

        return repository.save(cloudCopy);


    }


    public List<DocumentMetadata> getAll() {
        return repository.findAll();
    }

    public DocumentMetadata getById(Long id) {

        return repository.findById(id)
             .orElseThrow(() ->  new DocumentNotFoundException( "Document not found with id: " + id  )  );
    }

    public DocumentMetadata update(Long id, DocumentMetadataRequest request) {

        DocumentMetadata existingDocument =
                repository.findById(id)
                        .orElseThrow(() ->
                                new DocumentNotFoundException(
                                        "Document not found with id: " + id
                                )
                        );


        mapper.updateEntity(request, existingDocument);

        return repository.save(existingDocument);
    }

    public void delete(Long id) {

        DocumentMetadata existingDocument =
                repository.findById(id)
                        .orElseThrow(() ->
                                new DocumentNotFoundException(
                                        "Document not found with id: " + id   )
                        );

        repository.delete(existingDocument);
    }

    public List<DocumentMetadata> getCopiesByDocumentCode(String documentCode) {

        List<DocumentMetadata> copies =
                repository.findByDocumentCode(documentCode);

        if (copies.isEmpty()) {
            throw new DocumentNotFoundException(
                    "Document not found with code: " + documentCode
            );
        }

        return copies;
    }


    }