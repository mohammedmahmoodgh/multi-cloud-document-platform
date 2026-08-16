package com.enterprise.documentmetadata.repository;

import com.enterprise.documentmetadata.entity.DocumentMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentMetadataRepository
        extends JpaRepository<DocumentMetadata, Long> {

}
