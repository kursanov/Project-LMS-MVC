package kursanov.repo.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import kursanov.entities.Company;
import kursanov.entities.Course;
import kursanov.repo.CourseRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Transactional
@Repository
@RequiredArgsConstructor
public class CourseRepoImpl implements CourseRepo {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    public void saveCourse(Long companyId, Course course) {
        Company company = entityManager.find(Company.class, companyId);
        if (company != null) {
            company.addCourse(course);
            course.setCompany(company);
            entityManager.persist(course);
        }
    }

    @Override
    public List<Course> getAllCourseCompany(Long companyId) {
        entityManager.find(Company.class, companyId);
        return entityManager.createQuery(
                        "select c from Course c where c.company.id = :companyId", Course.class)
                .setParameter("companyId", companyId)
                .getResultList();
    }

    @Override
    public Course findById(Long courseId) {
        return entityManager.createQuery(
                "SELECT c FROM Course c WHERE c.id = :courseId",
                Course.class
        ).getSingleResult();
    }


    @Override
    public Course updateCourse(Long companyId, Long currentCourseId, Course course) {
        Course currentCourse = entityManager.find(Course.class, currentCourseId);
        if (currentCourse == null || !currentCourse.getCompany().getId().equals(companyId)) {
            throw new IllegalArgumentException("Course not found or does not belong to the specified company");
        }
        currentCourse.setCourseName(course.getCourseName());
        currentCourse.setDescription(course.getDescription());
        currentCourse.setDateOfStart(course.getDateOfStart());
        entityManager.merge(currentCourse);
        return currentCourse;
    }

    @Override
    @Transactional
    public String delete(Long courseId) {
        Course course = entityManager.find(Course.class, courseId);
        entityManager.remove(course);
        return "Success";
    }

    @Override
    @Transactional
    public void deleteByID(Long courseId) {
        Course course = entityManager.find(Course.class, courseId);
        System.out.println("course = " + course);
        course.getGroups().forEach(c -> c.getCourses().remove(course));
        course.getGroups().clear();
        entityManager.remove(course);
    }



    @Override
    public List<Course> getCoursesNotInGroup(Long groupId, Long companyId) {
        return entityManager.createQuery(
                        "SELECT c FROM Course c WHERE c.id NOT IN " +
                                "(SELECT c2.id FROM Course c2 JOIN c2.groups g WHERE g.id = :groupId) " +
                                "AND c.company.id = :companyId", Course.class)
                .setParameter("groupId", groupId)
                .setParameter("companyId", companyId)
                .getResultList();
    }

    @Override
    public Company getCompanyByCourseId(Long courseId) {

        return entityManager.createQuery("select c from  Company  c join fetch c.courses cc where  cc.id = :courseId", Company.class).
                setParameter("courseId", courseId).
                getSingleResult();
    }
}
