package kursanov.controller;

import kursanov.entities.Company;
import kursanov.entities.Course;
import kursanov.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/lms")
public class CompanyController {

    private final CompanyService companyService;


    @GetMapping("/all")
    public String getAllCompany(Model model){
        model.addAttribute("companies", companyService.getAllCompanies());
        return "main-page";
    }

    @GetMapping("/newCompany")
    public String create(Model model){
        model.addAttribute("newCompany", new Company());
        return "new-company";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("newCompany") Company company){
        companyService.saveCompany(company);
        return "redirect:/lms/all";
    }

    @GetMapping("/company/{id}")
    public String getCompany(@PathVariable("id") Long id, Model model) {
        Company company = companyService.getCompanyById(id);
        List<Course> courses = company.getCourses();
        for (Course cours : courses) {
            System.out.println("cours.getCourseName() = " + cours.getCourseName());
        }
        model.addAttribute("company", company);
        model.addAttribute("courses", courses);
        return "/company";
    }


    @GetMapping("/update-company/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Company company = companyService.getCompanyById(id);
        model.addAttribute("company", company);
        return "update-company";
    }

    @PostMapping("/update-company/{id}")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute Company company) {
        companyService.updateCompany(id, company);
        return "redirect:/lms/all";
    }



    @PostMapping("/delete-company/{id}")
    public String delete(@PathVariable("id") Long id ){
        companyService.delete(id);
        return "redirect:/lms/all";
    }


}
