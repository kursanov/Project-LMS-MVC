package kursanov.entities;


import jakarta.persistence.*;
import kursanov.enums.Specialization;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "instructors")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@SequenceGenerator(name = "entity_gen", sequenceName = "instructor_seq", allocationSize = 1)
public class Instructor extends BaseEntity{


    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    private String phoneNumber;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Specialization  specialization;


    @ManyToOne(cascade = CascadeType.DETACH)
    private Company company;

    @OneToMany(mappedBy = "instructor")
    private List<Course> courses;
}
