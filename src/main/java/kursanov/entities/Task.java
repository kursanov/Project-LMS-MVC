package kursanov.entities;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@SequenceGenerator(name = "entity_gen", sequenceName = "task_seq", allocationSize = 1)
public class Task extends BaseEntity{

    @Column(nullable = false)
    private String taskName;

    @Column(nullable = false)
    private String taskText;

    @Column(nullable = false)
    private LocalDate deadline;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Lesson lesson;
}
