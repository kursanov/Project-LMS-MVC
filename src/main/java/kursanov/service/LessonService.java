package kursanov.service;

import kursanov.entities.Course;
import kursanov.entities.Lesson;

import java.util.List;

public interface LessonService {


    void saveLesson(Long courseId, Lesson lesson);

    List<Lesson> getAllLessons(Long courseId);

    Lesson findById (Long courseId, Long lessonId);
    Lesson updateLesson(Long courseId,Long lessonId,Lesson lesson);

    String delete(Long courseId,Long lessonId);

    Course getCourseByLessonId(Long lessonId);
}
