package com.enterprise.documentmetadata.mapper;
import com.enterprise.documentmetadata.dto.DocumentMetadataRequest;
import com.enterprise.documentmetadata.entity.DocumentMetadata;
import org.springframework.stereotype.Component;

@Component
public class DocumentMetadataMapper {

    public DocumentMetadata toEntity(DocumentMetadataRequest request) {

        DocumentMetadata documentMetadata = new DocumentMetadata();
        documentMetadata.setFileName(request.getFileName());
        documentMetadata.setProvider(request.getProvider());
        documentMetadata.setExternalFileId(request.getExternalFileId());
        documentMetadata.setContentType(request.getContentType());
        documentMetadata.setSize(request.getSize());
        documentMetadata.setOwnerId(request.getOwnerId());
        return documentMetadata;
    }

    public void updateEntity(
            DocumentMetadataRequest request,
            DocumentMetadata documentMetadata) {

        documentMetadata.setFileName(request.getFileName());
        documentMetadata.setProvider(request.getProvider());
        documentMetadata.setExternalFileId(request.getExternalFileId());
        documentMetadata.setContentType(request.getContentType());
        documentMetadata.setSize(request.getSize());
        documentMetadata.setOwnerId(request.getOwnerId());
    }




}