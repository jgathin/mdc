package com.magnolia.mdc.models.vehicleModels;


public enum VehicleCondition {

    NEW("New Condition"),
    GOOD("Good Condition"),
    FAIR("Fair/Satisfactory Condition"),
    OLD("Poor Condition");



    private final String vehicleCondition;

    VehicleCondition(String vehicleCondition) {
        this.vehicleCondition = vehicleCondition;
    }



    public String getVehicleCondition() {
        return vehicleCondition;
    }
}
