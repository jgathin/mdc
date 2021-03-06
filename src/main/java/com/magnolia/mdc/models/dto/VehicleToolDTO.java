package com.magnolia.mdc.models.dto;

import com.magnolia.mdc.models.toolModels.Tool;
import com.magnolia.mdc.models.vehicleModels.Vehicle;

import javax.validation.constraints.NotNull;

public class VehicleToolDTO {

    @NotNull
    private Vehicle vehicle;

    @NotNull
    private Tool tool;

    private int quantity;

    public VehicleToolDTO() {}

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Tool getTool() {
        return tool;
    }

    public void setTool(Tool tool) {
        this.tool = tool;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
