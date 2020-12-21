package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Data
@Entity
public class InstructorDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String website;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "instructor_id") // , unique = true)
    private Instructor instructor;

    public InstructorDetail(String website) {
        this.website = website;
    }

    /*public void setInstructor(Instructor instructor) {
        instructor.setDetail(this);
        this.instructor = instructor;
    }*/
}
