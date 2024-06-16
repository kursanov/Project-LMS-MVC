package kursanov.service.impl;

import kursanov.entities.Task;
import kursanov.repo.TaskRepo;
import kursanov.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TaskServiceImpl implements TaskService {


    @Autowired
    private final TaskRepo taskRepo;


    @Override
    public void saveTask(Long lessonId, Task task) {
        taskRepo.saveTask(lessonId,task);

    }

    @Override
    public List<Task> getAllTasks(Long lessonId) {
        return taskRepo.getAllTasks(lessonId);
    }

    @Override
    public Task findByTaskById(Long lessonId, Long taskId) {
        return taskRepo.findByTaskById(lessonId,taskId);
    }


    @Override
    public Task updateTask(Long taskId, Task task) {
        return taskRepo.updateTask(taskId,task);
    }

    @Override
    public String delete(Long lessonId, Long taskId) {
        return taskRepo.delete(lessonId,taskId);
    }

}
