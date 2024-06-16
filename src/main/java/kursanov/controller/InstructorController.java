package kursanov.controller;

import kursanov.entities.Company;
import kursanov.entities.Instructor;
import kursanov.service.CompanyService;
import kursanov.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequiredArgsConstructor
@RequestMapping("/lms/instructor")
public class InstructorController {

    private final InstructorService instructorService;
    private final CompanyService companyService;


    @GetMapping("/getAllI/{companyId}")
    public String getAllInstructor(@PathVariable Long companyId, Model model) {
        List<Instructor> instructors = instructorService.getAllInstructors();
        model.addAttribute("instructors", instructors);
        model.addAttribute("companyId", companyId);
        return "all-instructors";
    }

    @GetMapping("/company-instructors/{companyId}")
    public String getInstructorsInCompany(@PathVariable Long companyId, Model model) {
        Company company = companyService.getCompanyById(companyId);
        List<Instructor> instructors = company.getInstructors();
        model.addAttribute("companyId", company.getId());
        model.addAttribute("instructors", instructors);
        return "instructors-main";
    }

    @GetMapping("/comp/{instructorId}/{companyId}")
    public String assignInstructor(@PathVariable Long companyId,
                                   @PathVariable Long instructorId,
                                   Model model) {
        instructorService.assign(companyId, instructorId);
        model.addAttribute("companyId", companyId);
        return "redirect:/lms/instructor/company-instructors/"+companyId;
    }

    @GetMapping("/newInstructor")
    public String create(Model model, @RequestParam("companyId") Long companyId) {
        model.addAttribute("newInstructor", new Instructor());
        model.addAttribute("companyId", companyId);
        return "new-instructor";
    }


    @PostMapping("/save")
    public String saveInstructor(@ModelAttribute("newInstructor") Instructor newInstructor, @RequestParam("companyId") Long companyId) {
        instructorService.saveInstructor(newInstructor);
        return "redirect:/lms/instructor/company-instructors/" + companyId;
    }


    @GetMapping("/update-instructor/{instructorId}")
    public String showUpdateForm(@PathVariable("instructorId") Long instructorId, Model model) {
        Instructor instructor = instructorService.findByInstructorById(instructorId);
        model.addAttribute("instructor", instructor);
        return "update-instructor";
    }

    @PostMapping("/save-updated/{instructorId}")
    public String updateInstructor(@PathVariable("instructorId") Long instructorId, @ModelAttribute("instructor") Instructor instructor){
        instructorService.updateInstructor(instructorId,instructor);
        Instructor instructorById = instructorService.findByInstructorById(instructorId);
        Long id = instructorById.getCompany().getId();
        return "redirect:/lms/instructor/company-instructors/"+id;
    }


    @GetMapping("/getAllS")
    public String getAllInstructors(Model model) {
        List<Instructor> instructors = instructorService.getAllInstructors();
        model.addAttribute("instructors", instructors);
        return "all-instructors";
    }

    @PostMapping("/delete-instructor/{instructorId}")
    public String delete(@PathVariable("instructorId") Long instructorId) {
        instructorService.deleteInstructor(instructorId);
        return "redirect:/lms/instructor/getAllS";
    }

    @GetMapping("/dismiss-instructor/{instructorId}/{companyId}")
    public String detach(@PathVariable("instructorId") Long instructorId,
                         @PathVariable Long companyId){
        instructorService.detach(instructorId, companyId);
        return "redirect:/lms/instructor/company-instructors/"+companyId;
    }


}
