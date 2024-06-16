package kursanov.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
@NoArgsConstructor
@Getter @Setter
@SequenceGenerator(name = "entity_gen", sequenceName = "company_seq", allocationSize = 1)
public class Company extends BaseEntity {


    @Column(nullable = false)
    private String name;

    private String country;

    private String address;

    private String phoneNumber;


//


    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    private List<Course> courses;

    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    private List<Instructor> instructors;

    public void addInstructor(Instructor instructor){
        if (this.instructors == null) this.instructors = new ArrayList<>();
        this.instructors.add(instructor);
    }


    public Company(String name, String country, String address, String phoneNumber) {
        this.name = name;
        this.country = country;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }


    public void addCourse(Course course) {
        if (this.courses == null) this.courses = new ArrayList<>();
        this.courses.add(course);
    }
}
