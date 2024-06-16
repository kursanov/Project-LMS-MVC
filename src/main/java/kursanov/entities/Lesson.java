package kursanov.entities;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "lessons")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@SequenceGenerator(name = "entity_gen", sequenceName = "lesson_seq", allocationSize = 1)
public class Lesson extends BaseEntity {

    @Column(nullable = false)
    private String lessonName;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Course course;

    @OneToMany(mappedBy = "lesson",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Task> tasks;

    public void addTask(Task task) {
        if (this.tasks == null) this.tasks = new ArrayList<>();
        tasks.add(task);
    }
}
