package kursanov.repo;


import kursanov.entities.Task;

import java.util.List;

public interface TaskRepo {


    void saveTask(Long lessonId, Task task);

    List<Task> getAllTasks(Long lessonId);

    Task findByTaskById(Long lessonId,Long taskId);

    Task updateTask(Long taskId,Task task);

    String delete(Long lessonId,Long taskId);
}
