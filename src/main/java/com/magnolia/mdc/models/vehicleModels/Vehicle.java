package com.magnolia.mdc.models.vehicleModels;


import com.magnolia.mdc.models.AbstractEntity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Map;

@Entity
public class Vehicle extends AbstractEntity {

    @NotBlank(message = "Alias is required")
    @Size(min = 3, max = 50, message = "Alias must be between 3 and 50 characters")
    private String alias;

    @OneToOne(cascade = CascadeType.ALL)
    @Valid
    @NotNull
    private VehicleDetails vehicleDetails;

    @ElementCollection
    @CollectionTable(name = "vehicle_tool_mapping",
        joinColumns = {@JoinColumn(name = "vehicle_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "tool_name")
    @Column(name = "quantity")
    private Map<String, Integer> vehicleToolMap;

    @ElementCollection
    @CollectionTable(name = "vehicle_part_mapping",
            joinColumns = {@JoinColumn(name = "vehicle_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "part_name")
    @Column(name = "quantity")
    private Map<String, Integer> vehiclePartMap;


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

    public Map<String, Integer> getVehicleToolMap() {
        return vehicleToolMap;
    }

    public Map<String, Integer> getVehiclePartMap() {
        return vehiclePartMap;
    }

    public void setVehiclePartMap(Map<String, Integer> vehiclePartMap) {
        this.vehiclePartMap = vehiclePartMap;
    }

    public void setVehicleToolMap(Map<String, Integer> vehicleToolMap) {
        this.vehicleToolMap = vehicleToolMap;


}

    public void addVehicleTool(String toolName) {
        this.vehicleToolMap.put(toolName, 1);
    }

    public void setVehicleTool(String toolName, Integer x) {
        this.vehicleToolMap.put(toolName, vehicleToolMap.get(toolName) + x);
    }

    public void addVehiclePart(String partName) {
        this.vehiclePartMap.put(partName, 1);
    }

    public void setVehiclePart(String partName, Integer x) {
        this.vehiclePartMap.put(partName, vehiclePartMap.get(partName) + x);
    }

}

