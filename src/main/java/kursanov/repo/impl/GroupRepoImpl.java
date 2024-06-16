package kursanov.repo.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import kursanov.entities.Company;
import kursanov.entities.Course;
import kursanov.entities.Group;
import kursanov.repo.GroupRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class GroupRepoImpl implements GroupRepo {

    @PersistenceContext
    private final EntityManager entityManager;

    @Override
    @Transactional
    public void saveGroup(Group group, List<Long> courseIds) {
        entityManager.persist(group);
        for (Long courseId : courseIds) {
            Course course = entityManager.find(Course.class, courseId);
            group.addCourse(course);
            course.addGroup(group);
        }

    }

    @Override
    public List<Group> getAllGroups(Long companyId) {
        return entityManager.createQuery(
                        "select distinct g from Group g join g.courses c where c.company.id = :companyId", Group.class)
                .setParameter("companyId", companyId)
                .getResultList();
    }

    @Override
    public Group findByGroupById(Long id) {
        return entityManager.createQuery("select g from  Group g where  g.id = :id", Group.class )
                .setParameter("id",id)
                .getSingleResult();

    }


    @Override
    @Transactional
    public Group updateGroup(Long id, Group group) {
        Group group1 = entityManager.find(Group.class, id);
        group1.setGroupName(group.getGroupName());
        group1.setDescription(group.getDescription());
        group1.setLinkImage(group.getLinkImage());
        entityManager.merge(group1);
        return group1;
    }

    @Override
    @Transactional
    public String delete(Long id) {
        Group group = entityManager.find(Group.class, id);
        entityManager.remove(group);
        return "Success!";
    }

    @Override
    @Transactional
    public void addCoursesToGroup(Long groupId, List<Long> courseIds) {
        Group group = entityManager.find(Group.class, groupId);
        if (group != null) {
            for (Long courseId : courseIds) {
                Course course = entityManager.find(Course.class, courseId);
                if (course != null) {
                    group.addCourse(course);
                    course.addGroup(group);
                }
            }
            entityManager.merge(group);
        }
    }

    @Override
    public Company getCompanyByGroupId(Long groupId) {
       return entityManager.createQuery("select c from Company c join c.courses cc join cc.groups g where g.id =:groupId", Company.class)
                .setParameter("groupId", groupId).getSingleResult();
    }

    @Override
    @Transactional
    public Group detach(Long groupId, Long courseId) {
        Group group = entityManager.find(Group.class, groupId);
        Course course = entityManager.find(Course.class, courseId);
        if (group != null && course != null) {
            group.getCourses().remove(course);
            course.getGroups().remove(group);
            entityManager.merge(group);
            entityManager.merge(course);

            if (group.getCourses().isEmpty()) {
                entityManager.remove(group);
                return  null;
            }
        }
        return group;

    }

    @Override
    @Transactional
    public List<Course> getAllCoursesForGroup(Long groupId) {
        Group group = entityManager.find(Group.class, groupId);
        return group.getCourses();
    }


}
