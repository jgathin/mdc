package com.magnolia.mdc.models.dto;

import com.magnolia.mdc.models.partModels.ReferencedPart;
import com.magnolia.mdc.models.vehicleModels.Vehicle;

import javax.validation.constraints.NotNull;

public class VehicleReferencedPartDTO {

    @NotNull
    private Vehicle vehicle;

    @NotNull
    private ReferencedPart referencedPart;

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public ReferencedPart getReferencedPart() {
        return referencedPart;
    }

    public void setReferencedPart(ReferencedPart referencedPart) {
        this.referencedPart = referencedPart;
    }
}
