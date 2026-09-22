package com.enterprise.documentmetadata.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Positive;

public class DocumentMetadataRequest {

    @NotBlank(message = "File name is required")
    @Size(
            min = 1,
            max = 255,
            message = "File name must be between 1 and 255 characters"
    )
    private String fileName;

    @NotBlank(message = "Provider is required")
    @Pattern(
            regexp = "AWS|AZURE|GCP",
            message = "Provider must be AWS, AZURE, or GCP"
    )
    private String provider;

    @NotBlank(message = "External file ID is required")
    @Size(
            max = 255,
            message = "External file ID must not exceed 255 characters"
    )
    private String externalFileId;

    @NotBlank(message = "Content type is required")
    @Pattern(
            regexp = "^[a-zA-Z0-9!#$&^_.+-]+/[a-zA-Z0-9!#$&^_.+-]+$",
            message = "Content type must be a valid MIME type"
    )
    private String contentType;


    @NotNull(message = "File size is required")
    @Positive(message = "File size must be greater than zero")
    private Long size;

    @NotBlank(message = "Owner ID is required")
    @Size(
            max = 100,
            message = "Owner ID must not exceed 100 characters"
    )
    private String ownerId;


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getExternalFileId() {
        return externalFileId;
    }

    public void setExternalFileId(String externalFileId) {
        this.externalFileId = externalFileId;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }
}
