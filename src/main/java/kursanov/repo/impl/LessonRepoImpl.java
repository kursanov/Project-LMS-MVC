package kursanov.repo.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import kursanov.entities.Course;
import kursanov.entities.Lesson;
import kursanov.repo.LessonRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LessonRepoImpl implements LessonRepo {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public void saveLesson(Long courseId, Lesson lesson) {
        Course course = entityManager.find(Course.class, courseId);
        if (course == null) {
            System.out.println("Курс с id " + courseId + " не найден");
            return;
        }

        lesson.setCourse(course);
        course.addLesson(lesson);
        entityManager.persist(lesson);

    }




    @Override
    public List<Lesson> getAllLessons(Long courseId) {
        return entityManager.createQuery("select l from Lesson l where  l.course.id = :courseId", Lesson.class)
                .setParameter("courseId", courseId).getResultList();
    }

    @Override
    public Lesson findById(Long courseId, Long lessonId) {
        return entityManager.createQuery("select l from  Lesson  l where  l.course.id = :courseId and l.id = :lessonId", Lesson.class)
                .setParameter("courseId", courseId)
                .setParameter("lessonId", lessonId).getSingleResult();
    }


    @Override
    @Transactional
    public Lesson updateLesson(Long courseId, Long lessonId, Lesson lesson) {
        Lesson lesson1 = entityManager.find(Lesson.class, lessonId);
        lesson1.setLessonName(lesson.getLessonName());
        entityManager.merge(lesson1);
        return lesson1;
    }

    @Override
    @Transactional
    public String delete(Long courseId, Long lessonId) {
        Course course = entityManager.find(Course.class, courseId);
        Lesson lesson = entityManager.find(Lesson.class, lessonId);
        lesson.setCourse(null);
        course.getLessons().remove(lesson);
        entityManager.merge(course);
        entityManager.remove(lesson);
        return "success!";
    }

    @Override
    public Course getCourseByLessonId(Long lessonId) {
        Lesson lesson = entityManager.find(Lesson.class, lessonId);
        if (lesson != null){
            return lesson.getCourse();
        }else {
            return null;
        }
    }
}
