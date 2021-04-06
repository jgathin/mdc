package com.magnolia.mdc.controllers;


import com.magnolia.mdc.data.FormRepository;
import com.magnolia.mdc.data.UserRepository;
import com.magnolia.mdc.models.Forms.PostMDCForm;
import com.magnolia.mdc.models.User;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("forms")
public class PostMDCFormController {

    private static final String userSessionKey = "user";

    @Autowired
    UserRepository userRepository;

    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            return null;
        }

        return  user.get();
    }



    @Autowired
    FormRepository formRepository;

    @GetMapping("index")
    public String displayPostMDCForms(Model model) {

        model.addAttribute("title", "Post MDC Forms");
        model.addAttribute("forms", formRepository.findAll());

        return "forms/index";
    }

    @GetMapping("add")
    public String displayPostMDCForm(Model model) {

        model.addAttribute("title", "Post MDC Form");
        model.addAttribute(new PostMDCForm());

        return "forms/add";
    }

    @PostMapping("add")
    public String processPostMDCForm(@ModelAttribute @Valid PostMDCForm newPostMDCForm, Errors errors,
                                     Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("title", "Post MDC Form");
            return "forms/add";
        }

        formRepository.save(newPostMDCForm);

        return "redirect:detail?postMDCFormId=" + newPostMDCForm.getId();
    }

    @GetMapping("edit/{postMDCFormId}")
    public String displayEditPostMDCForm(Model model, @PathVariable int postMDCFormId) {

        Optional<PostMDCForm> result = formRepository.findById(postMDCFormId);
        PostMDCForm editPostMDCForm = result.get();
        model.addAttribute("title", "Edit " + editPostMDCForm.getInvoiceNumber());
        model.addAttribute("postMDCForm", editPostMDCForm);

        return "forms/edit";
    }

    @PostMapping("edit")
    public String processEditPostMDCForm(@ModelAttribute @Valid PostMDCForm postMDCForm, int postMDCFormId,
     Errors errors, Model model) {

        Optional<PostMDCForm> result = formRepository.findById(postMDCFormId);
        PostMDCForm newPostMDCForm = result.get();

        if (errors.hasErrors()) {
            model.addAttribute("title", "Edit " + newPostMDCForm.getInvoiceNumber());
            return "forms/edit";
        }

        formRepository.deleteById(postMDCFormId);
        formRepository.save(postMDCForm);

        return "redirect:index";
    }

    @GetMapping("detail")
    public String displayPostMDCFormDetails(@RequestParam Integer postMDCFormId, Model model) {

        Optional<PostMDCForm> result = formRepository.findById(postMDCFormId);

        HttpSession session = null;
//        Integer userId = (Integer) session.getAttribute(userSessionKey);
//        Optional<User> user = userRepository.findById(userId);

        ;

        if (result.isEmpty()) {
            model.addAttribute("title", "Invalid Post Form ID: " + postMDCFormId);
        } else {
            PostMDCForm newPostMDCForm = result.get();
            model.addAttribute("title", newPostMDCForm.getInvoiceNumber() + " Details");
            model.addAttribute("postMDCForm", newPostMDCForm);
//            model.addAttribute("user", user);
        }

        return "forms/detail";
    }
}
