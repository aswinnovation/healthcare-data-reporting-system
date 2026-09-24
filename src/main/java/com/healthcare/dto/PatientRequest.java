package com.healthcare.dto;

import java.time.LocalDate;

/**
 * Inbound shape for creating/updating a patient.
 *
 * No Bean Validation annotations here: the project does not currently have
 * spring-boot-starter-validation as a dependency, and Phase 2's instructions
 * are to add validation only if that dependency already exists, and not to
 * add unnecessary dependencies to get it. Basic required-field checks are
 * done in the service layer instead.
 */
public class PatientRequest {

    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String phone;
    private String email;

    public PatientRequest() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}