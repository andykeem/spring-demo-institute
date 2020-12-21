package com.example.demo.controller;

import com.example.demo.model.Instructor;
import com.example.demo.model.InstructorDetail;
import com.example.demo.service.InstructorDetailService;
import com.example.demo.service.InstructorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(path = "/instructor-detail")
@Controller
public class InstructorDetailController {

    private final Logger log = LoggerFactory.getLogger(InstructorDetailController.class);
    private final InstructorService instService;
    private final InstructorDetailService detailService;

    @Autowired
    public InstructorDetailController(InstructorService instService,
                                      InstructorDetailService detailService) {
        this.instService = instService;
        this.detailService = detailService;
    }

    @GetMapping(path = "{id}/edit/{did}")
    public String edit(@PathVariable int id, @PathVariable int did, Model model) {
        log.info("id: {}, did: {}", id, did);

        Instructor inst = instService.findById(id);
        log.info("inst: {}", inst);

        InstructorDetail detail = detailService.findById(did);
        log.info("detail: {}", detail);

//        detail.setInstructor(inst);

        model.addAttribute("instructor", inst);
        model.addAttribute("detail", detail);
        return "instructor/detail/edit";
    }

    @PostMapping(path = "/save")
    public String save(@Valid @ModelAttribute("detail") InstructorDetail detail,
                       Errors errors, BindingResult result) {
        log.info("detail: {}", detail);
        log.info("errors: {}", errors);
        log.info("result: {}", result);
        if (errors.hasErrors()) {
            return "redirect: /instructor-detail/" + detail.getId();
        }
        return "redirect:/instruct/edit/"; // + detail.getInstructor().getId();
    }
}
