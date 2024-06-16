package kursanov.entities;


import jakarta.persistence.*;
import kursanov.enums.FormatStudy;
import lombok.*;

@Entity
@Table(name = "students")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@SequenceGenerator(name = "entity_gen", sequenceName = "student_seq", allocationSize = 1)
public class Student extends BaseEntity{

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String formatStudy;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Group group;




}
