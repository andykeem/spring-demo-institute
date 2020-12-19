package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
public class CourseService {

    private final CourseRepository courseRepo;

    public CourseService(CourseRepository courseRepo) {
        this.courseRepo = courseRepo;
    }

    public List<Course> findAll() {
        return courseRepo.findAll();
    }

    public Course findById(long id) {
        return courseRepo.findById(id).orElseGet(new Supplier<Course>() {
            @Override
            public Course get() {
                return new Course();
            }
        });
    }

    public void save(Course course) {
        courseRepo.save(course);
    }
}
