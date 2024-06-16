package kursanov.controller;


import kursanov.entities.Course;
import kursanov.entities.Group;
import kursanov.entities.Student;
import kursanov.service.GroupService;
import kursanov.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/lms/student")
public class StudentController {


    private final StudentService studentService;
    private final GroupService groupService;


    @GetMapping("/{groupId}")
    public String getAll(@PathVariable("groupId") Long groupId, Model model) {
        List<Student> allStudents = studentService.getAllStudents(groupId);
        Group group = groupService.findByGroupById(groupId);
        model.addAttribute("allStudents", allStudents);
        model.addAttribute("group", group);
        return "all-students";
    }

    @GetMapping("/add-student/{groupId}")
    public String showAddStudentForm(@PathVariable("groupId") Long groupId, Model model) {
        Group group = groupService.findByGroupById(groupId);
        Student student = new Student();
        model.addAttribute("group", group);
        model.addAttribute("student", student);
        return "new-student";
    }

    @PostMapping("/add-student/{groupId}")
    public String addStudentToGroup(@PathVariable("groupId") Long groupId, @ModelAttribute Student student) {
        studentService.saveStudent(groupId, student);
        return "redirect:/lms/student/" + groupId;
    }


    @GetMapping("/update-student/{groupId}/{studentId}")
    public String updateShow(@PathVariable("groupId")Long groupId,
                             @PathVariable("studentId")Long studentId, Model model){
        Student student = studentService.findByStudentId(groupId, studentId);
        model.addAttribute("student",student);
        return "update-student";
    }

    @PostMapping("/update-student/{groupId}/{studentId}")
    public String update(@PathVariable("groupId") Long groupId,
                         @PathVariable("studentId") Long studentId,
                         @ModelAttribute("student") Student student){
        studentService.updateStudent(groupId,studentId,student);
        return "redirect:/lms/student/" + groupId;

    }


    @PostMapping("/delete-student/{groupId}/{studentId}")
    public String delete(@PathVariable("groupId") Long groupId,
                         @PathVariable("studentId") Long studentId){
        studentService.delete(groupId, studentId);
        return "redirect:/lms/student/" + groupId;

    }


}
