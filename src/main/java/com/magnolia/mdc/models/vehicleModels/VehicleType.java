package com.magnolia.mdc.models.vehicleModels;


public enum VehicleType {
    VAN("Van"),
    CAR("Car"),
    PERSONAL("Personal Vehicle");

    private final String vehicleType;


    VehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

   public String getVehicleType() {
        return vehicleType;
   }

}
