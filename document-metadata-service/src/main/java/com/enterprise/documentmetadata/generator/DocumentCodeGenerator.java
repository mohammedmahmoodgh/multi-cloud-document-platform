package com.enterprise.documentmetadata.generator;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DocumentCodeGenerator {

    private final JdbcTemplate jdbcTemplate;

    public DocumentCodeGenerator(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public String generate() {

        Long nextNumber = jdbcTemplate.queryForObject(
                "SELECT nextval('document_code_seq')",  Long.class  );

        return String.format("DOC-%06d", nextNumber);
    }


}