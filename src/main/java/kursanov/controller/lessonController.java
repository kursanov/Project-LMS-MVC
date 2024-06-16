package kursanov.controller;


import kursanov.entities.Lesson;
import kursanov.entities.Student;
import kursanov.service.CourseService;
import kursanov.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/lms/lesson")
public class lessonController {

    private final LessonService lessonService;
    private final CourseService courseService;


    @GetMapping("/{companyId}/{courseId}")
    public String getAllLessonsForCourse(@PathVariable Long companyId,
                                         @PathVariable Long courseId,
                                         Model model) {
        List<Lesson> lessons = lessonService.getAllLessons(courseId);
        model.addAttribute("lessons", lessons);
        model.addAttribute("companyId",companyId);
        model.addAttribute("courseId",courseId);
        return "all-lessons";
    }


    @GetMapping("/save-lesson/{companyId}/{courseId}")
    public String showAddLessonForm(@PathVariable Long companyId,
                                    @PathVariable Long courseId,
                                    Model model) {
        model.addAttribute("companyId", companyId);
        model.addAttribute("courseId", courseId);
        model.addAttribute("lesson", new Lesson());
        return "new-lesson";
    }


    @PostMapping("/save/{companyId}/{courseId}")
    public String saveLesson(@PathVariable Long companyId,
                             @PathVariable Long courseId,
                             @ModelAttribute("lesson") Lesson lesson) {
        lessonService.saveLesson(courseId, lesson);
        return "redirect:/lms/lesson/"+companyId+"/"+courseId;
    }

    @GetMapping("/update-lesson/{companyId}/{courseId}/{lessonId}")
    public String updateShow(@PathVariable("companyId") Long companyId,
                             @PathVariable("courseId") Long courseId,
                             @PathVariable("lessonId") Long lessonId,
                             Model model) {
        Lesson lesson = lessonService.findById(courseId, lessonId);
        model.addAttribute("lesson", lesson);
        model.addAttribute("companyId", companyId);
        model.addAttribute("courseId", courseId);
        return "update-lesson";
    }

    @PostMapping("/update-lesson/{companyId}/{courseId}/{lessonId}")
    public String update(@PathVariable("courseId") Long courseId,
                         @PathVariable("lessonId") Long lessonId,
                         @PathVariable("companyId") Long companyId,
                         @ModelAttribute("lesson") Lesson lesson) {
        lessonService.updateLesson(courseId, lessonId, lesson);
        return "redirect:/lms/lesson/" + companyId + "/" + courseId;
    }

    @PostMapping("/delete-lesson/{companyId}/{courseId}/{lessonId}")
    public String delete(@PathVariable("companyId") Long companyId,
                         @PathVariable("courseId") Long courseId,
                         @PathVariable("lessonId") Long lessonId) {
        lessonService.delete(courseId, lessonId);
        return "redirect:/lms/lesson/" + companyId + "/" + courseId;
    }








}
