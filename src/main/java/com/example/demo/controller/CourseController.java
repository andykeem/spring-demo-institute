package com.example.demo.controller;

import com.example.demo.model.Course;
import com.example.demo.model.Instructor;
import com.example.demo.service.CourseService;
import com.example.demo.service.InstructorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "course")
public class CourseController {

    private final Logger log = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    private CourseService courseService;

    @Autowired
    private InstructorService instructorService;

    @GetMapping(path = "/edit/{id}")
    public String edit(Model model, @PathVariable int id) {
        log.info("edit course..");
        Course course = courseService.findById(id);
        model.addAttribute(course);

        List<Instructor> instructorList = instructorService.findAll();
        model.addAttribute(instructorList);
        return "course/edit";
    }

    @PostMapping(path = "/save")
    public String add(@Valid @ModelAttribute("course") Course course,
                      BindingResult result) {
        log.info("course: {}", course);
        log.info("result: {}", result);
        if (result.hasErrors()) {
            return "course/new";
        }
        courseService.save(course);
        return "/home";
    }

    @GetMapping(path = "/list")
    public String list(Model model) {
        List<Course> courseList = courseService.findAll();
        log.info("course list: {}", courseList);
        model.addAttribute(courseList);
        return "course/list";
    }

    @GetMapping(path = "/delete/{id}")
    public String delete(@PathVariable int id) {
        courseService.deleteById(id);
        return "redirect:/course/list";
    }
}
