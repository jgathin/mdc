package com.magnolia.mdc.controllers;

import com.magnolia.mdc.data.ToolRepository;
import com.magnolia.mdc.models.toolModels.Tool;
import com.magnolia.mdc.models.toolModels.ToolType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("tools")
public class ToolController {

    @Autowired
    private ToolRepository toolRepository;

    @GetMapping
    public String displayTools(Model model) {
        model.addAttribute("title", "All Tools");
        model.addAttribute("tools", toolRepository.findAll());

        return "tools/index";
    }

    @GetMapping("add")
    public String displayAddToolForm(Model model) {
        model.addAttribute("title", "Add Tool");
        model.addAttribute(new Tool());
        model.addAttribute("types", ToolType.values());

        return "tools/add";
    }

    @PostMapping("add")
    public String processAddToolForm(@ModelAttribute @Valid Tool newTool, Errors errors, Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Add Tool");
            return "tools/add";
        }

        toolRepository.save(newTool);
        return "redirect:";

    }


}
