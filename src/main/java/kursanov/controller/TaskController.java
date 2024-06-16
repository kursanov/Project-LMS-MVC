package kursanov.controller;

import kursanov.entities.Course;
import kursanov.entities.Lesson;
import kursanov.entities.Student;
import kursanov.entities.Task;
import kursanov.service.LessonService;
import kursanov.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/lms/task")
public class TaskController {

    private final TaskService taskService;
    private final LessonService lessonService;

    @GetMapping("/all/{lessonId}/{courseId}")
    public String getAll(@PathVariable("lessonId") Long lessonId,
                         @PathVariable("courseId") Long courseId,
                         Model model) {
        List<Task> tasks = taskService.getAllTasks(lessonId);
        model.addAttribute("tasks", tasks);
        model.addAttribute("lessonId", lessonId);
        model.addAttribute("courseId", courseId);
        return "all-tasks";
    }




    @GetMapping("/new/{lessonId}/{courseId}")
    public String create(@PathVariable("lessonId") Long lessonId,
                         @PathVariable("courseId") Long courseId, Model model) {
        model.addAttribute("lessonId", lessonId);
        model.addAttribute("courseId", courseId);
        model.addAttribute("task", new Task());
        return "new-task";
    }


    @PostMapping("/save/{lessonId}/{courseId}")
    public String save(@PathVariable("lessonId") Long lessonId,
                       @PathVariable("courseId") Long courseId,
                       @ModelAttribute Task task) {
        lessonService.findById(courseId, lessonId);
            taskService.saveTask(lessonId, task);
        return "redirect:/lms/task/all/" + lessonId + "/" + courseId;
    }


    @GetMapping("/update-task/{lessonId}/{taskId}")
    public String updateShow(@PathVariable("lessonId")Long lessonId,
                             @PathVariable("taskId")Long taskId, Model model){
        Task task = taskService.findByTaskById(lessonId, taskId);
        model.addAttribute("task",task);
        return "update-task";
    }


    @PostMapping("/update-task/{lessonId}/{taskId}")
    public String update(@PathVariable("lessonId") Long lessonId,
                         @PathVariable("taskId") Long taskId,
                         @ModelAttribute("task") Task task){
        taskService.updateTask(taskId,task);
        Course course = lessonService.getCourseByLessonId(lessonId);
        return "redirect:/lms/task/all/" + lessonId + "/" + course.getId();
    }






}
