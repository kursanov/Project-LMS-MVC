package kursanov.service.impl;

import kursanov.entities.Course;
import kursanov.entities.Lesson;
import kursanov.repo.LessonRepo;
import kursanov.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepo lessonRepo;


    @Override
    public void saveLesson(Long courseId, Lesson lesson) {
        lessonRepo.saveLesson(courseId, lesson);
    }

    @Override
    public List<Lesson> getAllLessons(Long courseId) {
        return lessonRepo.getAllLessons(courseId);
    }

    @Override
    public Lesson findById(Long courseId, Long lessonId) {
        return lessonRepo.findById(courseId,lessonId);
    }

    @Override
    public Lesson updateLesson(Long courseId, Long lessonId, Lesson lesson) {
        return lessonRepo.updateLesson(courseId,lessonId,lesson);
    }


    @Override
    public String delete(Long courseId,Long lessonId) {
        return lessonRepo.delete(courseId,lessonId);
    }

    @Override
    public Course getCourseByLessonId(Long lessonId) {
        return lessonRepo.getCourseByLessonId(lessonId);
    }
}
