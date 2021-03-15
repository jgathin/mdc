package com.magnolia.mdc.controllers;

import com.magnolia.mdc.data.VehicleRepository;
import com.magnolia.mdc.models.vehicleModels.Vehicle;
import com.magnolia.mdc.models.vehicleModels.VehicleCondition;
import com.magnolia.mdc.models.vehicleModels.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("vehicles")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @GetMapping
    public String displayVehicles(Model model) {
        model.addAttribute("title", "All Vehicles");
        model.addAttribute("vehicles", vehicleRepository.findAll());

        return "vehicles/index";
    }

    @GetMapping("add")
    public String displayAddVehicleForm(Model model) {
        model.addAttribute("title", "Add Vehicle");
        model.addAttribute(new Vehicle());
        model.addAttribute("types", VehicleType.values());
        model.addAttribute("conditions", VehicleCondition.values());

        return "vehicles/add";

    }

    @PostMapping("add")
    public String processAddVehicleForm(@ModelAttribute @Valid Vehicle newVehicle,
                                        Errors errors, Model model) {
        if(errors.hasErrors()) {
            model.addAttribute("title", "Add Vehicle");
            return "vehicles/add";
        }

        vehicleRepository.save(newVehicle);
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteEventForm(Model model) {
        model.addAttribute("title", "Delete Vehicles");
        model.addAttribute("vehicles", vehicleRepository.findAll());
        return "vehicles/delete";

    }

    @PostMapping("delete")
    public String processDeleteVehiclesForm(@RequestParam(required = false) int[] vehicleIds) {

        if (vehicleIds != null) {
            for (int id : vehicleIds) {
                vehicleRepository.deleteById(id);
            }
        }


        return "redirect:";

    }

    @GetMapping("detail")
    public String displayEventDetails(@RequestParam Integer vehicleId, Model model) {

        Optional<Vehicle> result = vehicleRepository.findById(vehicleId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Vehicle ID: " + vehicleId);
        } else {
            Vehicle vehicle = result.get();
            model.addAttribute("title", vehicle.getAlias() + " Details");
            model.addAttribute("vehicle", vehicle);
        }

        return "vehicles/detail";
    }

}
