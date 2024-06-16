package kursanov.service.impl;

import jakarta.transaction.Transactional;
import kursanov.entities.Company;
import kursanov.entities.Course;
import kursanov.entities.Group;
import kursanov.repo.GroupRepo;
import kursanov.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class GroupServiceImpl implements GroupService {


    private final GroupRepo
            groupRepo;

    @Override
    public void saveGroup(Group group, List<Long> courseIds) {
         groupRepo.saveGroup(group, courseIds);
    }

    @Override
    public List<Group> getAllGroups(Long companyId) {
        return groupRepo.getAllGroups(companyId);
    }

    @Override
    public Group findByGroupById(Long id) {
        return groupRepo.findByGroupById(id);
    }


    @Override
    public Group updateGroup(Long id, Group group) {
        return groupRepo.updateGroup(id,group);
    }

    @Override @Transactional
    public String delete(Long id) {
        Group byGroupById = groupRepo.findByGroupById(id);
        if (byGroupById == null) throw  new NoSuchElementException("Group with id: %d not found!".formatted(id));
        byGroupById.getCourses().forEach(c -> c.getGroups().remove(byGroupById));
        byGroupById.getCourses().clear();
        return groupRepo.delete(id);
    }

    @Override
    public void addCoursesToGroup(Long groupId, List<Long> courseIds) {
        groupRepo.addCoursesToGroup(groupId, courseIds);
    }

    @Override
    public Company getCompanyByGroupId(Long groupId) {
        return groupRepo.getCompanyByGroupId(groupId);
    }

    @Override
    public Group detach(Long groupId, Long courseId) {
         return groupRepo.detach(groupId,courseId);
    }

    @Override
    public List<Course> getAllCoursesForGroup(Long groupId) {
        return groupRepo.getAllCoursesForGroup(groupId);
    }
}
