package kursanov.repo;

import kursanov.entities.Company;
import kursanov.entities.Course;

import java.util.List;

public interface CourseRepo {

    void saveCourse(Long companyId, Course course);

    List<Course> getAllCourseCompany(Long companyId);

    Course findById(Long courseId);

    Course updateCourse(Long companyId,Long currentCourseId,Course course);

    String delete(Long courseId);

    void deleteByID(Long courseId);

    List<Course> getCoursesNotInGroup(Long groupId, Long companyId);


    Company getCompanyByCourseId(Long courseId);
}
