package com.studentreportingsystem.salesken;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
public class UIcontroller {
    @Autowired
    private Search elasticSearchQuery;

    @GetMapping("/")
    public String viewHomePage(Model model) throws IOException {
        model.addAttribute("listProductDocuments",elasticSearchQuery.searchAllDocuments());
        return "index";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute("student") Students student) throws IOException {
        elasticSearchQuery.createOrUpdateDocument(student);
        return "redirect:/";
    }

    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") String id, Model model) throws IOException {

        Students student = elasticSearchQuery.getDocumentById(id);
        model.addAttribute("student", student);
        return "updateStudent";
    }

    @GetMapping("/showNewProductForm")
    public String showNewEmployeeForm(Model model) {
        // create model attribute to bind form data
        Students student = new Students();
        model.addAttribute("student", student);
        return "NewStudent";
    }

    @GetMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable(value = "id") String id) throws IOException {

        this.elasticSearchQuery.deleteDocumentById(id);
        return "redirect:/";
    }
}
