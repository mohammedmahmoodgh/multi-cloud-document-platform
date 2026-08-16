# Multi-Cloud Document Platform

An enterprise-style multi-cloud document management platform built with Java and Spring Boot.

## Project Goal

The goal of this project is to build a production-oriented backend platform for managing document metadata and, later, document storage across multiple cloud providers.

The project is structured as multiple independent services so that each service can be developed, tested, deployed, and scaled separately.

## Current Services

### Document Metadata Service

Responsible for storing and managing document metadata such as:

- File name
- Cloud provider
- External file ID
- Content type
- File size
- Owner ID

## Technology Stack

- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Maven
- Git
- GitHub

## Current API Development

The Document Metadata Service currently includes:

- POST - Create document metadata

Additional CRUD operations will be added as development continues.

## Project Structure

multi-cloud-document-platform/
- document-metadata-service/
- README.md
- .gitignore

## Development Status

This project is under active development as a portfolio-quality enterprise Java application.