package com.magnolia.mdc.models.partModels;

import com.magnolia.mdc.models.AbstractEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class ReferencedPart extends AbstractEntity {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank
    @Size(max = 1000, message = "Description too long!!")
    private String description;

    @NotBlank
    @Size(min = 2, max = 30, message = "Name must be between 2 and 50 characters")
    private String referenceNumber;

    public ReferencedPart() {}

    public ReferencedPart(String name, String description, String referenceNumber) {
        this.name = name;
        this.description = description;
        this.referenceNumber = referenceNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReferenceNumber() {
        return referenceNumber;
    }

    public void setReferenceNumber(String referenceNumber) {
        this.referenceNumber = referenceNumber;
    }
}
