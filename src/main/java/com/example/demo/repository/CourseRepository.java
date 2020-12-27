package com.example.demo.repository;

import com.example.demo.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    //    @Query("SELECT id, title, instructor_id FROM Course WHERE instructor_id = :id")
    List<Course> findAllByInstructorId(@Param(value = "id") long id);
}
