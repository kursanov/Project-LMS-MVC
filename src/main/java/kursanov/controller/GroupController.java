package kursanov.controller;


import jakarta.transaction.Transactional;
import kursanov.entities.Company;
import kursanov.entities.Course;
import kursanov.entities.Group;
import kursanov.service.CourseService;
import kursanov.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/lms/group")
public class GroupController {

    private final GroupService groupService;
    private  final CourseService courseService;




    @GetMapping("/all-groups/{id}")
    public String getAllGroups(@PathVariable("id") Long companyId, Model model){
        List<Group> allGroups = groupService.getAllGroups(companyId);
        List<Course> allCourseCompany = courseService.getAllCourseCompany(companyId);
        model.addAttribute("allGroups",allGroups);
        model.addAttribute("companyId",companyId);
        model.addAttribute("courses",allCourseCompany);
        return "all-groups";
    }




    @GetMapping("/save/{companyId}")
    public String showCreateGroupForm(@PathVariable("companyId") Long companyId, Model model) {
        List<Course> courses = courseService.getAllCourseCompany(companyId);
        model.addAttribute("group", new Group());
        model.addAttribute("courses", courses);
        model.addAttribute("courseIds", new ArrayList<Long>());
        model.addAttribute("companyId", companyId);
        return "new-group";
    }


    @PostMapping("/create/{companyId}")
    public String createGroup(@ModelAttribute("group") Group group, @RequestParam("courseIds") List<Long> courseIds, @PathVariable("companyId") Long companyId) {
        groupService.saveGroup(group, courseIds);
        return "redirect:/lms/group/all-groups/" + companyId;
    }


    @GetMapping("/get/{id}")
    public String getGroup(@PathVariable("id") Long groupId, Model model) {
        Group group = groupService.findByGroupById(groupId);
        Company company = null;
        for (Course course : group.getCourses()) {
            if(course != null) {
                company = course.getCompany();
            }
        }
        model.addAttribute("group", group);
        model.addAttribute("company", company);
        return "group";
    }

    @GetMapping("/delete-group/{companyId}/{groupId}")
    public String delete(@PathVariable("groupId") Long id, @PathVariable ("companyId") Long companyId){
        groupService.delete(id);
        return "redirect:/lms/group/all-groups/" + companyId;
    }



    @GetMapping("/manage-courses/{groupId}/{companyId}")
    public String manageCourses(@PathVariable Long groupId, @PathVariable Long companyId, Model model) {
        Group group = groupService.findByGroupById(groupId);
        List<Course> availableCourses = courseService.getCoursesNotInGroup(groupId, companyId);
        model.addAttribute("group", group);
        model.addAttribute("availableCourses", availableCourses);
        model.addAttribute("companyId", companyId);
        return "manage-courses";
    }



    @GetMapping("/update-group/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Group group = groupService.findByGroupById(id);
        model.addAttribute("group", group);
        return "update-group";
    }

    @PostMapping("/update-group/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute Group group) {
        groupService.updateGroup(id, group);
        return "redirect:/lms/group/get/" + id;
    }


    @Transactional
    @PostMapping("/add-courses/{groupId}")
    public String addCoursesToGroup(@PathVariable Long groupId, @RequestParam("selectedCourses") String selectedCourses) {
        String[] courseIdsArray = selectedCourses.split(",");
        List<Long> courseIds = Arrays.stream(courseIdsArray).map(Long::parseLong).collect(Collectors.toList());
        groupService.addCoursesToGroup(groupId, courseIds);
       Company company = groupService.getCompanyByGroupId(groupId);
        return "redirect:/lms/group/all-groups/" + company.getId();
    }

    @GetMapping("/group/{groupId}/courses")
    public String showGroupCourses(@PathVariable("groupId") Long groupId, Model model) {
        Group group = groupService.findByGroupById(groupId);
        List<Course> courses = groupService.getAllCoursesForGroup(groupId);
        model.addAttribute("group", group);
        model.addAttribute("courses", courses);
        return "group-courses";
    }


    @PostMapping("/{groupId}/detach-course")
    public String detachCourseFromGroup(@PathVariable("groupId") Long groupId, @RequestParam("courseId") Long courseId) {
        Group g = groupService.detach(groupId, courseId);
        System.out.println("courseId = " + courseId);
        if (g == null){
            Company company = courseService.getCompanyByCourseId(courseId);
            return "redirect:/lms/group/all-groups/" + company.getId();
        }else {
        return "redirect:/lms/group/group/" + groupId + "/courses";
        }
    }




}
