package kursanov.service;

import kursanov.entities.Company;
import kursanov.entities.Course;
import kursanov.entities.Group;

import java.util.List;

public interface GroupService {


    void saveGroup(Group group,List<Long> courseIds);


    List<Group> getAllGroups(Long companyId);

    Group findByGroupById(Long id);

    Group updateGroup(Long id,Group group);

    String delete(Long id);

    void addCoursesToGroup(Long groupId, List<Long> courseIds);

    Company getCompanyByGroupId(Long groupId);

    Group detach(Long groupId,Long courseId);

    List<Course> getAllCoursesForGroup(Long groupId);



}
