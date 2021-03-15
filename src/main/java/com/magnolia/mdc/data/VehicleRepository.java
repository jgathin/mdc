package com.magnolia.mdc.data;

import com.magnolia.mdc.models.vehicleModels.Vehicle;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends CrudRepository<Vehicle, Integer> {
}
