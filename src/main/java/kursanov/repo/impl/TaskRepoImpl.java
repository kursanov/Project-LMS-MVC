package kursanov.repo.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import kursanov.entities.Lesson;
import kursanov.entities.Task;
import kursanov.repo.TaskRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@RequiredArgsConstructor
@Repository
public class TaskRepoImpl implements TaskRepo {

    @PersistenceContext
    private final EntityManager entityManager;
    @Override
    @Transactional
    public void saveTask(Long lessonId, Task task) {
        Lesson lesson = entityManager.find(Lesson.class, lessonId);
        if (lesson != null) {
            lesson.addTask(task);
            task.setLesson(lesson);
            entityManager.persist(task);
        }


    }

    @Override
    public List<Task> getAllTasks(Long lessonId) {
       return entityManager.createQuery("select t from Task t where t.lesson.id = :lessonId", Task.class)
                .setParameter("lessonId",lessonId)
                .getResultList();

    }

    @Override
    public Task findByTaskById(Long lessonId, Long taskId) {
        return entityManager.createQuery("select t from Task t where t.lesson.id = :lessonId and t.id = :taskId", Task.class)
                .setParameter("lessonId",lessonId)
                .setParameter("taskId",taskId).getSingleResult();

    }

    @Override
    @Transactional
    public Task updateTask(Long taskId, Task task) {
        Task task1 = entityManager.find(Task.class, taskId);
        task1.setTaskName(task.getTaskName());
        task1.setTaskText(task.getTaskText());
        task1.setDeadline(task.getDeadline());
        entityManager.merge(task1);
        return task1;
    }

    @Override
    @Transactional
    public String delete(Long lessonId, Long taskId) {
        Lesson lesson = entityManager.find(Lesson.class, lessonId);
        Task task = entityManager.find(Task.class, taskId);
        task.setLesson(null);
        lesson.getTasks().remove(task);
        entityManager.merge(lesson);
        entityManager.remove(task);
        return "Success!";
    }
}
