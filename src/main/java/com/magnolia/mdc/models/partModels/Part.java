package com.magnolia.mdc.models.partModels;

import com.magnolia.mdc.models.AbstractEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Part extends AbstractEntity {

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 30, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotNull
    private PartType partType;

    @NotBlank
    @Size(max = 1000, message = "Description too long!!")
    private String description;

//    @Size(max = 500, message = "There are too many parts!!")
//    private int quantity;

    public Part(String name, PartType partType, String description) {
        this.name = name;
        this.partType = partType;
        this.description = description;
//        this.quantity = quantity;
    }

    public Part() {}

    public String getName() {
        return name;
    }

    public PartType getPartType() {
        return partType;
    }

    public String getDescription() {
        return description;
    }

//    public int getQuantity() {
//        return quantity;
//    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPartType(PartType partType) {
        this.partType = partType;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
}
