package com.magnolia.mdc.models.toolModels;

import com.magnolia.mdc.models.AbstractEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Tool extends AbstractEntity {

    @NotBlank
    @Size(min = 2, max = 30, message = "Name must be between 2 & 50 characters")
    private String name;

    @NotNull
    private ToolType toolType;

    @NotBlank
    @Size(max = 1000, message = "Description too long!!")
    private String description;

    @Size(max = 500, message = "There are too many parts!!")
    private int quantity;

    public Tool(String name, ToolType toolType, String description, int quantity) {
        this.name = name;
        this.toolType = toolType;
        this.description = description;
        this.quantity = quantity;
    }

    public Tool () {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ToolType getToolType() {
        return toolType;
    }

    public void setToolType(ToolType toolType) {
        this.toolType = toolType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
