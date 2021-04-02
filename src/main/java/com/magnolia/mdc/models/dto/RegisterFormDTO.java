package com.magnolia.mdc.models.dto;

import com.magnolia.mdc.models.vehicleModels.Vehicle;

public class RegisterFormDTO extends LoginFormDTO {

    private String verifyPassword;

    private Vehicle vehicle;

    public String getVerifyPassword() {
        return verifyPassword;
    }

    public void setVerifyPassword(String verifyPassword) {
        this.verifyPassword = verifyPassword;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }
}
