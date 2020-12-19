package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.model.Instructor;
import com.example.demo.service.CourseService;
import com.example.demo.service.InstructorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(path = "/instructor")
public class InstructorController {

    private final Logger log = LoggerFactory.getLogger(InstructorController.class);
    private final InstructorService instService;
    private final CourseService courseService;

    public InstructorController(InstructorService instService, CourseService courseService) {
        this.instService = instService;
        this.courseService = courseService;
    }

    @GetMapping(path = "/list")
    public String list(Model model) {
        List<Instructor> instructorList = instService.findAll();
        log.info("inst. list: {}", instructorList);
        model.addAttribute(instructorList);
        return "instructor/list";
    }

    @GetMapping(path = "/edit/{id}")
    public String edit(Model model, @PathVariable int id) {
        log.info("edit..");
        Instructor instructor = instService.findById(id);
        model.addAttribute(instructor);

        List<Course> courseList = courseService.findAll();
        model.addAttribute(courseList);
        return "instructor/edit";
    }

    @PostMapping(path = "/update")
    public String add(@Valid @ModelAttribute("instructor") Instructor instructor,
                      BindingResult result, @RequestParam Map<String, String> params) {
        log.info("instructor: {}", instructor);
        log.info("result: {}", result);
//        log.info("params: {}", params);
        if (result.hasErrors()) {
            return "/new";
        }
        instService.save(instructor); // , params);
        return "/home";
    }

    @GetMapping(path = "/delete/{id}")
    public String delete(@PathVariable int id) {
        log.info("delete id: {}", id);
        instService.deleteById(id);
        return "/home";
    }
}
