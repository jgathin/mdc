package com.magnolia.mdc.models.dto;

import com.magnolia.mdc.models.partModels.Part;
import com.magnolia.mdc.models.vehicleModels.Vehicle;

import javax.validation.constraints.NotNull;

public class VehiclePartDTO {

    @NotNull
    private Vehicle vehicle;

    @NotNull
    private Part part;

    private int quantity;


    public VehiclePartDTO() {}

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
