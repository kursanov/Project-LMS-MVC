package kursanov.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "groups")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@SequenceGenerator(name = "entity_gen", sequenceName = "group_seq", allocationSize = 1)
public class Group extends BaseEntity{

    @Column(nullable = false)
    private String groupName;

    @Column(length = 500)
    private String linkImage;

    private String description;


    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Student> students;

    @ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.DETACH)
    private List<Course> courses;

    public void addCourse(Course course){
        if (this.courses == null) this.courses = new ArrayList<>();
        this.courses.add(course);
    }

    public void addStudent(Student student){
        if (this.students == null) this.students = new ArrayList<>();
        this.students.add(student);
    }


}
