package kursanov.service;

import kursanov.entities.Company;
import kursanov.entities.Course;

import java.util.List;

public interface CourseService {


    void saveCourse(Long companyId,Course course);

    List<Course> getAllCourseCompany(Long companyId);

    Course findById(Long courseId);

    Course updateCourse(Long companyId,Long currentCourseId,Course course);

    String delete(Long courseId);

    void deleteCourseById(Long courseId);

    List<Course> getCoursesNotInGroup(Long groupId, Long companyId);

    Company getCompanyByCourseId(Long courseId);
}
