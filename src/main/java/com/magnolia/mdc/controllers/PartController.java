package com.magnolia.mdc.controllers;

import com.magnolia.mdc.data.PartRepository;
import com.magnolia.mdc.data.ReferencedPartRepository;
import com.magnolia.mdc.data.VehicleRepository;
import com.magnolia.mdc.models.partModels.Part;
import com.magnolia.mdc.models.partModels.PartType;
import com.magnolia.mdc.models.partModels.ReferencedPart;
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

    @Autowired
    private ReferencedPartRepository referencedPartRepository;

    @GetMapping
    public String displayAllParts(Model model) {

//        Vehicle vehicles = (Vehicle) vehicleRepository.findAll();


        model.addAttribute("title", "All Parts");
        model.addAttribute("parts", partRepository.findAll());
        model.addAttribute("rparts", referencedPartRepository.findAll());

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

    @GetMapping("radd")
    public String displayAddReferencedPartForm(Model model) {
        model.addAttribute("title", "Add Referenced Part");
        model.addAttribute("rpart", new ReferencedPart());

        return "parts/radd";
    }

    @PostMapping("radd")
    public String processAddReferencedPartForm(@ModelAttribute @Valid ReferencedPart newReferencedPart, Errors errors, Model model) {

        if(errors.hasErrors()) {
            model.addAttribute("title", "Add Referenced Part");
            return "parts/radd";
        }

        referencedPartRepository.save(newReferencedPart);
        return "redirect:";
    }

    @GetMapping("rdetail")
    public String displayReferencedPartDetails(@RequestParam Integer referencedPartId, Model model) {

        Optional<ReferencedPart> result = referencedPartRepository.findById(referencedPartId);

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Referenced Part ID: " + referencedPartId);
        } else {
            ReferencedPart referencedPart = result.get();
            model.addAttribute("title", referencedPart.getName() + " Details");
            model.addAttribute("rpart", referencedPart);
        }

        return "parts/rdetail";
    }

    @PostMapping("rdelete")
    public String processDeleteReferencedPartsForm(@RequestParam(required = false) int[] referencedPartIds) {

        if (referencedPartIds != null) {
            for (int id : referencedPartIds) {
                referencedPartRepository.deleteById(id);
            }
        }

        return "redirect:";
    }

}
