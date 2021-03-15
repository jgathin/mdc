package com.magnolia.mdc.models.vehicleModels;


import com.magnolia.mdc.models.AbstractEntity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Vehicle extends AbstractEntity {

    @NotBlank(message = "Alias is required")
    @Size(min = 3, max = 50, message = "Alias must be between 3 and 50 characters")
    private String alias;

    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    @NotNull
    private VehicleDetails vehicleDetails;

    public Vehicle(String alias, VehicleDetails vehicleDetails) {
        this.alias = alias;
        this.vehicleDetails = vehicleDetails;
    }

    public Vehicle(){}

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public VehicleDetails getVehicleDetails() {
        return vehicleDetails;
    }

    public void setVehicleDetails(VehicleDetails vehicleDetails) {
        this.vehicleDetails = vehicleDetails;
    }
}

