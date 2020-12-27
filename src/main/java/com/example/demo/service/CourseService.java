package com.example.demo.service;

import com.example.demo.model.Course;
import com.example.demo.model.Instructor;
import com.example.demo.repository.CourseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

@Service
public class CourseService {

    private final Logger log = LoggerFactory.getLogger(CourseService.class);
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

    public void save(Instructor inst) {
//        courseRepo.
    }

    public void removeByInstructor(Instructor inst) {
        List<Course> courseList = courseRepo.findAllByInstructorId(inst.getId());
        log.info("removing courseList: {}", courseList);
        courseList.forEach(new Consumer<Course>() {
            @Override
            public void accept(Course course) {
                course.setInstructor(null);
                courseRepo.save(course);
            }
        });
    }

    public void deleteById(long id) {
        courseRepo.deleteById(id);
    }
}
