package com.enterprise.documentmetadata.repository;

import com.enterprise.documentmetadata.entity.DocumentMetadata;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface DocumentMetadataRepository
        extends JpaRepository<DocumentMetadata, Long> {

    boolean existsByProviderAndExternalFileId(
            String provider,
            String externalFileId
    );
    Optional<DocumentMetadata> findFirstByDocumentCode(String documentCode);

    boolean existsByDocumentCodeAndProvider(
            String documentCode,
            String provider
    );

    List<DocumentMetadata> findByDocumentCode(String documentCode);

}