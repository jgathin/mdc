package com.magnolia.mdc.controllers;

import com.magnolia.mdc.data.PartRepository;
import com.magnolia.mdc.data.VehicleRepository;
import com.magnolia.mdc.models.partModels.Part;
import com.magnolia.mdc.models.partModels.PartType;
import com.magnolia.mdc.models.vehicleModels.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("parts")
public class PartController {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private PartRepository partRepository;

    @GetMapping
    public String displayParts(Model model) {

//        Vehicle vehicles = (Vehicle) vehicleRepository.findAll();


        model.addAttribute("title", "All Parts");
        model.addAttribute("parts", partRepository.findAll());

        return "parts/index";
    }

    @GetMapping("add")
    public String displayAddPartForm(Model model) {
        model.addAttribute("title", "Add Part");
        model.addAttribute(new Part());
        model.addAttribute("types", PartType.values());

        return "parts/add";
    }

    @PostMapping("add")
    public String processAddPartForm(@ModelAttribute @Valid Part newPart, Errors errors, Model model) {

        if(errors.hasErrors()) {
            model.addAttribute("title", "Add Part");
            return "parts/add";
        }

        partRepository.save(newPart);
        return "redirect:";
    }

    @GetMapping("delete")
    public String displayDeletePartForm(Model model) {
        model.addAttribute("title", "Delete Parts");
        model.addAttribute("parts", partRepository.findAll());
        return "parts/delete";
    }

    @PostMapping("delete")
    public String processDeletePartsForm(@RequestParam(required = false) int[] partIds) {

        if (partIds != null) {
            for (int id : partIds) {
                partRepository.deleteById(id);
            }
        }

        return "redirect:";
    }

    @GetMapping("detail")
    public String displayPartDetails(@RequestParam Integer partId, Model model) {

        Optional<Part> result = partRepository.findById(partId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Part ID: " + partId);
        } else {
            Part part = result.get();
            model.addAttribute("title", part.getName() + " Details");
            model.addAttribute("part", part);
        }

        return "parts/detail";
    }

}
