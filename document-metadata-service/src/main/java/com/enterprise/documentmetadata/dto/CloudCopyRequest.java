package com.enterprise.documentmetadata.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public class CloudCopyRequest {

    @NotBlank(message = "Provider is required")
    @Pattern(
            regexp = "AWS|AZURE|GCP",
            message = "Provider must be AWS, AZURE, or GCP"
    )
    private String provider;

    @NotBlank(message = "External file ID is required")
    private String externalFileId;

    @NotBlank(message = "Content type is required")
    private String contentType;

    @NotNull(message = "File size is required")
    @Positive(message = "File size must be greater than zero")
    private Long size;

    @NotBlank(message = "Owner ID is required")
    private String ownerId;



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