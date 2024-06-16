package kursanov.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "courses")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@SequenceGenerator(name = "entity_gen", sequenceName = "course_seq", allocationSize = 1)
public class Course extends BaseEntity{

    @Column(nullable = false)
    private String courseName;

    @Column(nullable = false)
    private LocalDate dateOfStart;

    @Column(nullable = false)
    private String description;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Company company;


    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Lesson> lessons;

    @ManyToOne(cascade = CascadeType.DETACH)
    private Instructor instructor;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
    private List<Group> groups;

    public void addGroup(Group group){
        if (this.groups == null) this.groups = new ArrayList<>();
        this.groups.add(group);
    }


    public void addLesson(Lesson lesson) {
        if (this.lessons == null) this.lessons = new ArrayList<>();
        this.lessons.add(lesson);
    }
}
