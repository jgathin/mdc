package com.magnolia.mdc.controllers;

import com.magnolia.mdc.data.PartRepository;
import com.magnolia.mdc.data.ReferencedPartRepository;
import com.magnolia.mdc.data.ToolRepository;
import com.magnolia.mdc.data.VehicleRepository;
import com.magnolia.mdc.models.dto.VehiclePartDTO;
import com.magnolia.mdc.models.dto.VehicleReferencedPartDTO;
import com.magnolia.mdc.models.dto.VehicleToolDTO;
import com.magnolia.mdc.models.partModels.Part;
import com.magnolia.mdc.models.partModels.ReferencedPart;
import com.magnolia.mdc.models.toolModels.Tool;
import com.magnolia.mdc.models.vehicleModels.Vehicle;
import com.magnolia.mdc.models.vehicleModels.VehicleCondition;
import com.magnolia.mdc.models.vehicleModels.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.*;

@Controller
@RequestMapping("vehicles")
public class VehicleController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ToolRepository toolRepository;

    @Autowired
    private PartRepository partRepository;

    @Autowired
    private ReferencedPartRepository referencedPartRepository;

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
            model.addAttribute("rparts", vehicle.getVehicleReferencedPartMap());
        }

        return "vehicles/detail";
    }

    @GetMapping("add-tool")
    public String displayAddToolForm(@RequestParam Integer vehicleId, Model model) {

        Integer quantity = null;

        Optional<Vehicle> result = vehicleRepository.findById(vehicleId);
        Vehicle vehicle = result.get();
        model.addAttribute("title", "Add Tool to: " + vehicle.getAlias());
        model.addAttribute("tools", toolRepository.findAll());
        model.addAttribute("quantity", quantity);
        VehicleToolDTO vehicleTool = new VehicleToolDTO();
        vehicleTool.setVehicle(vehicle);
        model.addAttribute("vehicleTool", vehicleTool);

        return "vehicles/add-tool";
    }

    @GetMapping("add-part")
    public String displayAddPartForm(@RequestParam Integer vehicleId, Model model) {

        Integer quantity = null;
        Optional<Vehicle> result = vehicleRepository.findById(vehicleId);
        Vehicle vehicle = result.get();
        model.addAttribute("title", "Add Part to: " + vehicle.getAlias());
        model.addAttribute("parts", partRepository.findAll());
        model.addAttribute("quantity", quantity);
        VehiclePartDTO vehiclePart = new VehiclePartDTO();
        vehiclePart.setVehicle(vehicle);

        model.addAttribute("vehiclePart", vehiclePart);

        return "vehicles/add-part";
    }

    @PostMapping("add-tool")
    public String processAddToolForm(@ModelAttribute @Valid VehicleToolDTO vehicleTool, Errors errors,
                                     Model model, Integer quantity) {

        if (!errors.hasErrors()) {
            Vehicle vehicle = vehicleTool.getVehicle();
            Tool tool = vehicleTool.getTool();
            if (!vehicle.getVehicleToolMap().containsKey(tool.getName())) {
                vehicle.addVehicleTool(tool.getName(), quantity);
                vehicleRepository.save(vehicle);
            } else if (vehicle.getVehicleToolMap().containsKey(tool.getName())) {
                vehicle.setVehicleTool(tool.getName(), quantity);
                vehicleRepository.save(vehicle);
            }

            return "redirect:detail?vehicleId=" + vehicle.getId();

        }

        return "redirect:add-tool";

    }

    @GetMapping("add-rpart")
    public String displayAddReferencedPartForm(@RequestParam Integer vehicleId, Model model) {

        Integer quantity = null;
        Optional<Vehicle> result = vehicleRepository.findById(vehicleId);
        Vehicle vehicle = result.get();

        Collection<ReferencedPart> referencedParts = (Collection<ReferencedPart>) referencedPartRepository.findAll();


        Collection<ReferencedPart> newReferencedParts;
        newReferencedParts = null;

        referencedParts.removeIf(part -> vehicle.getVehicleReferencedPartMap().containsKey(part.getReferenceNumber()));

        model.addAttribute("title", "Add Reference Part to: " + vehicle.getAlias());
        model.addAttribute("rparts", referencedParts);
        model.addAttribute("quantity", quantity);
        VehicleReferencedPartDTO vehicleReferencedPart = new VehicleReferencedPartDTO();
        vehicleReferencedPart.setVehicle(vehicle);

        model.addAttribute("vehicleReferencedPart", vehicleReferencedPart);

        return "vehicles/add-rpart";
    }

    @PostMapping("add-rpart")
    public String processAddReferencedPartForm(@ModelAttribute @Valid VehicleReferencedPartDTO vehicleReferencedPart, Errors errors,
                                     Model model, RedirectAttributes redirAttrs) {

        if (!errors.hasErrors()) {
            Vehicle vehicle = vehicleReferencedPart.getVehicle();
            ReferencedPart referencedPart = vehicleReferencedPart.getReferencedPart();

            if (!vehicle.getVehicleReferencedPartMap().containsKey(referencedPart.getReferenceNumber())) {
                vehicle.addVehicleReferencedPart(referencedPart.getReferenceNumber(),referencedPart.getName());
                vehicleRepository.save(vehicle);

                return "redirect:detail?vehicleId=" + vehicle.getId();
            } else  {
                redirAttrs.addFlashAttribute("error", "Part with reference no: " + referencedPart.getReferenceNumber() +
                        " is already on the vehicle");
                return "redirect:add-rpart?vehicleId=" + vehicle.getId();

            }


        }
        return "redirect:add-rpart";
    }

    @PostMapping("add-part")
    public String processAddPartForm(@ModelAttribute @Valid VehiclePartDTO vehiclePart, Errors errors,
                                     Model model, Integer quantity) {

        if (!errors.hasErrors()) {
            Vehicle vehicle = vehiclePart.getVehicle();
            Part part = vehiclePart.getPart();

            if (!vehicle.getVehiclePartMap().containsKey(part.getName())) {
                vehicle.addVehiclePart(part.getName(), quantity);
                vehicleRepository.save(vehicle);
            } else if (vehicle.getVehiclePartMap().containsKey(part.getName())) {
                vehicle.setVehiclePart(part.getName(), quantity);
                vehicleRepository.save(vehicle);
            }

            return "redirect:detail?vehicleId=" + vehicle.getId();
        }
        return "redirect:add-part";
    }

    @GetMapping("edit-tool-list")
    public String displayEditToolListForm(@RequestParam Integer vehicleId, Model model) {

        Integer quantity = null;

        Optional<Vehicle> result = vehicleRepository.findById(vehicleId);
        Vehicle vehicle = result.get();
        model.addAttribute("title", "Edit Tools On " + vehicle.getAlias());
        model.addAttribute("tools", vehicle.getVehicleToolMap());
        model.addAttribute("quantity", quantity);
        VehicleToolDTO vehicleTool = new VehicleToolDTO();
        vehicleTool.setVehicle(vehicle);
        model.addAttribute("vehicleTool", vehicleTool);

        return "vehicles/edit-tool-list";
    }

    @PostMapping("edit-tool-list")
    public String renderEditToolListForm(@ModelAttribute VehicleToolDTO vehicleTool, Model model) {

        Vehicle vehicle = vehicleTool.getVehicle();
        HashMap <String, Integer> toolList = (HashMap<String, Integer>) vehicle.getVehicleToolMap();

        for (Map.Entry<String,Integer> entry : toolList.entrySet()) {
//
            vehicle.editVehicleTool(entry.getKey());
        }

        vehicleRepository.save(vehicle);

        return "redirect:detail?vehicleId=" + vehicle.getId();
    }



}
