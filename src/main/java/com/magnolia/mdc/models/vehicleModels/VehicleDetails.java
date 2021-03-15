package com.magnolia.mdc.models.vehicleModels;

import com.magnolia.mdc.models.AbstractEntity;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
public class VehicleDetails extends AbstractEntity {

    @NotBlank(message = "Color is required")
    private String color;


    @NotNull(message = "Vehicle type is required")
    private VehicleType vehicleType;


    @NotNull(message = "Vehicle condition is required")
    private  VehicleCondition vehicleCondition;

    public VehicleDetails(String color, VehicleType vehicleType, VehicleCondition vehicleCondition ) {
        this.color = color;
        this.vehicleType = vehicleType;
        this.vehicleCondition = vehicleCondition;
    }

    public VehicleDetails() {}

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VehicleType vehicleType) {
        this.vehicleType = vehicleType;
    }

    public VehicleCondition getVehicleCondition() {
        return vehicleCondition;
    }

    public void setVehicleCondition(VehicleCondition vehicleCondition) {
        this.vehicleCondition = vehicleCondition;
    }
}
