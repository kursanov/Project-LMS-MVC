package kursanov.service;

import kursanov.entities.Task;

import java.util.List;

public interface TaskService {


    void saveTask(Long lessonId, Task task);

    List<Task> getAllTasks(Long lessonId);

    Task findByTaskById(Long lessonId,Long taskId);


    Task updateTask(Long taskId, Task task);

    String delete(Long lessonId,Long taskId);
}
