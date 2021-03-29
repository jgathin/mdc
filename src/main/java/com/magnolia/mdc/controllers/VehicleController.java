package com.magnolia.mdc.controllers;

import com.magnolia.mdc.data.PartRepository;
import com.magnolia.mdc.data.ToolRepository;
import com.magnolia.mdc.data.VehicleRepository;
import com.magnolia.mdc.models.dto.VehiclePartDTO;
import com.magnolia.mdc.models.dto.VehicleToolDTO;
import com.magnolia.mdc.models.partModels.Part;
import com.magnolia.mdc.models.toolModels.Tool;
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

    @Autowired
    private ToolRepository toolRepository;

    @Autowired
    private PartRepository partRepository;

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
        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Vehicle");
            return "vehicles/add";
        }

        vehicleRepository.save(newVehicle);
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeleteVehicleForm(Model model) {
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
    public String displayVehicleDetails(@RequestParam Integer vehicleId, Model model) {

        Optional<Vehicle> result = vehicleRepository.findById(vehicleId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Vehicle ID: " + vehicleId);
        } else {
            Vehicle vehicle = result.get();
            model.addAttribute("title", vehicle.getAlias() + " Details");
            model.addAttribute("vehicle", vehicle);
            model.addAttribute("tools", vehicle.getVehicleToolMap());
            model.addAttribute("parts", vehicle.getVehiclePartMap());
        }

        return "vehicles/detail";
    }

    @GetMapping("add-tool")
    public String displayAddToolForm(@RequestParam Integer vehicleId, Model model) {

        Optional<Vehicle> result = vehicleRepository.findById(vehicleId);
        Vehicle vehicle = result.get();
        model.addAttribute("title", "Add Tool to: " + vehicle.getAlias());
        model.addAttribute("tools", toolRepository.findAll());
        VehicleToolDTO vehicleTool = new VehicleToolDTO();
        vehicleTool.setVehicle(vehicle);
        model.addAttribute("vehicleTool", vehicleTool);

        return "vehicles/add-tool";
    }

    @GetMapping("add-part")
    public String displayAddPartForm(@RequestParam Integer vehicleId, Model model) {

        Optional<Vehicle> result = vehicleRepository.findById(vehicleId);
        Vehicle vehicle = result.get();
        model.addAttribute("title", "Add Part to: " + vehicle.getAlias());
        model.addAttribute("parts", partRepository.findAll());
        VehiclePartDTO vehiclePart = new VehiclePartDTO();
        vehiclePart.setVehicle(vehicle);
        model.addAttribute("vehiclePart", vehiclePart);

        return "vehicles/add-part";
    }

    @PostMapping("add-tool")
    public String processAddToolForm(@ModelAttribute @Valid VehicleToolDTO vehicleTool, Errors errors, Model model) {

        if (!errors.hasErrors()) {
            Vehicle vehicle = vehicleTool.getVehicle();
            Tool tool = vehicleTool.getTool();
            if (!vehicle.getVehicleToolMap().containsKey(tool.getName())) {
                vehicle.addVehicleTool(tool.getName());
                vehicleRepository.save(vehicle);
            } else if (vehicle.getVehicleToolMap().containsKey(tool.getName())) {
                vehicle.setVehicleTool(tool.getName(), 1);
                vehicleRepository.save(vehicle);
            }

            return "redirect:detail?vehicleId=" + vehicle.getId();

        }

        return "redirect:add-tool";

    }


    @PostMapping("add-part")
    public String processAddPartForm(@ModelAttribute @Valid VehiclePartDTO vehiclePart, Errors errors, Model model) {

        if (!errors.hasErrors()) {
            Vehicle vehicle = vehiclePart.getVehicle();
            Part part = vehiclePart.getPart();

            if (!vehicle.getVehiclePartMap().containsKey(part.getName())) {
                vehicle.addVehiclePart(part.getName());
                vehicleRepository.save(vehicle);
            } else if (vehicle.getVehiclePartMap().containsKey(part.getName())) {
                vehicle.setVehiclePart(part.getName(), 1);
                vehicleRepository.save(vehicle);
            }

            return "redirect:detail?vehicleId=" + vehicle.getId();
        }
        return "redirect:add-part";
    }

}
