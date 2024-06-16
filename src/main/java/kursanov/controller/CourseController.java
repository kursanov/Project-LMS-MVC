package kursanov.controller;


import kursanov.entities.Course;
import kursanov.service.CompanyService;
import kursanov.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/lms/course")
public class CourseController {

    private final CourseService courseService;
    private final CompanyService companyService;

    @GetMapping("/getAllC/{companyId}")
    public String getAllCourse(@PathVariable Long companyId, Model model){
        model.addAttribute("courses", courseService.getAllCourseCompany(companyId));
        return "all-courses";
    }

    @GetMapping("/newCourse")
    public String create(Model model, @RequestParam("companyId") Long companyId){
        model.addAttribute("newCourse", new Course());
        model.addAttribute("companyId", companyId);
        return "new-course";
    }

    @PostMapping("/save/{companyId}")
    public String save(@ModelAttribute("newCourse") Course course,
                       @PathVariable Long companyId){
        courseService.saveCourse(companyId, course);
        return "redirect:/lms/company/" + companyId;
    }

    @GetMapping("/update-course/{companyId}/{courseId}")
    public String showUpdateForm(@PathVariable("companyId") Long companyId,
                                 @PathVariable("courseId") Long courseId,
                                 Model model) {
        Course course = courseService.findById(courseId);
        model.addAttribute("course", course);
        return "update-course";
    }

    @PostMapping("/update-course/{companyId}/{courseId}")
    public String updateCourse(@PathVariable("companyId") Long companyId,
                               @PathVariable("courseId") Long courseId,
                               @ModelAttribute("course") Course course) {
        courseService.updateCourse(companyId, courseId, course);
        return "redirect:/lms/company/" + companyId;
    }


    @PostMapping("/delete-course/{companyId}/{courseId}")
    public String deleteCourse(@PathVariable Long companyId,
                               @PathVariable Long courseId) {
        System.out.println("companyId = " + companyId);
        System.out.println("courseId = " + courseId);
        courseService.deleteCourseById(courseId);
        return "redirect:/lms/company/" + companyId;
    }






}
