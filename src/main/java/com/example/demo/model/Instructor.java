package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true)
    @NotBlank(message = "required field")
    @Pattern(regexp = "(.+)@(.+)\\.(.+)", message = "invalid email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "instructor")
    private InstructorDetail detail;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
            CascadeType.REFRESH}, fetch = FetchType.LAZY, mappedBy = "instructor")
    private List<Course> courseList;

    public String getName() {
        return String.format("%s %s", firstName, lastName);
    }

    public void setDetail(InstructorDetail detail) {
        detail.setInstructor(this);
        this.detail = detail;
    }

    public void addCourse(Course course) {
        if (courseList.isEmpty()) {
            courseList = new ArrayList<>();
        }
        course.setInstructor(this);
        courseList.add(course);
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
