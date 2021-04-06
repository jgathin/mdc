package com.magnolia.mdc.models;

import com.magnolia.mdc.models.vehicleModels.Vehicle;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
public class User extends AbstractEntity{

    @NotNull
    private String username;


    @NotNull
    private String pwHash;

    @OneToOne(cascade = CascadeType.ALL)
    private Vehicle vehicle;

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public User() {}

    public User (String username, String password, Vehicle vehicle) {
        this.username = username;
        this.pwHash = encoder.encode(password);
        this.vehicle = vehicle;

    }

    public String getUsername() {
        return username;
    }

    public boolean isMatchingPassword(String password) {
        return encoder.matches(password, pwHash);
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

}
