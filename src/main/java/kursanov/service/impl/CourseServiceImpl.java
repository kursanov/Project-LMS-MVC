package kursanov.service.impl;

import jakarta.transaction.Transactional;
import kursanov.entities.Company;
import kursanov.entities.Course;
import kursanov.repo.CourseRepo;
import kursanov.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private  final CourseRepo courseRepo;

    @Override
    public void saveCourse(Long companyId, Course course) {
        courseRepo.saveCourse(companyId,course);

    }

    @Override
    public List<Course> getAllCourseCompany(Long companyId) {
        return courseRepo.getAllCourseCompany(companyId);

    }

    @Override
    public Course findById(Long courseId) {
        return courseRepo.findById(courseId);
    }


    @Override
    public Course updateCourse(Long companyId, Long currentCourseId, Course course) {
        return courseRepo.updateCourse(companyId,currentCourseId,course);
    }

    @Override
    @Transactional
    public String delete(Long courseId) {
        return courseRepo.delete(courseId);
    }

    @Override
    @Transactional
    public void deleteCourseById(Long courseId) {
        courseRepo.deleteByID(courseId);
    }

    @Override
    public List<Course> getCoursesNotInGroup(Long groupId, Long companyId) {
        return courseRepo.getCoursesNotInGroup(groupId,companyId);
    }


    @Override
    public Company getCompanyByCourseId(Long courseId) {
        return courseRepo.getCompanyByCourseId(courseId);
    }


}
