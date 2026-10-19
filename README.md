# Healthcare Data & Reporting Management System

A backend-focused healthcare application built using Java and Spring Boot to manage patient records, healthcare encounters, and reporting operations with an Oracle database.

## Project Overview

The Healthcare Data & Reporting Management System provides REST APIs for managing patient information, recording healthcare encounters, and generating summary reports. The application follows a layered architecture to separate controllers, services, repositories, DTOs, and entities.

## Tech Stack

- **Language:** Java 22
- **Framework:** Spring Boot 4
- **Database:** Oracle AI Database 26ai Free
- **Data Access:** Spring Data JPA, Hibernate
- **API Development:** REST APIs, JSON
- **Build Tool:** Maven
- **IDE:** IntelliJ IDEA
- **Version Control:** Git and GitHub

## Features

### 1. Patient Management
- Create patient records.
- Retrieve a patient by ID.
- Retrieve patient records.
- Update existing patient details.
- Delete patient records.
- Validate required patient information.

### 2. Encounter Management
- Create healthcare encounters linked to patients.
- Retrieve encounters by ID.
- Retrieve all encounters.
- Retrieve encounters for a specific patient.
- Update encounter details and status.
- Delete encounters.
- Validate required encounter fields and patient references.

### 3. Reporting
- Generate encounter summaries grouped by status.
- Generate encounter summaries grouped by encounter type.
- Retrieve the encounter count for an individual patient.
- Use SQL aggregation queries for reporting.

### 4. Error Handling and Validation
- Centralized REST API exception handling.
- Structured error responses containing timestamps, status codes, and messages.
- Validation of required fields and invalid patient or encounter IDs.

### 5. Database Integration
- Oracle database connectivity using JDBC.
- Entity mapping and persistence using Spring Data JPA and Hibernate.
- SQL-based reporting queries.
- A PL/SQL function script for patient encounter-count reporting.

## Project Architecture

```text
Client / API Testing Tool
          |
          v
     REST Controller
          |
          v
       Service Layer
          |
          v
     Repository Layer
          |
          v
      Oracle Database
```

DTOs are used to transfer request
