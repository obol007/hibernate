package pl.obol.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.obol.model.Student;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/form")
public class FormController {

    private final Logger logger = LoggerFactory.getLogger(FormController.class);

    @ModelAttribute("programs")
    public List<String> languages(){
        return Arrays.asList("word","excel","notepad","windows");
    }

    @GetMapping("/search")
    public String showForm() {
        return "form";
    }

    @GetMapping("/result")
    public String showResult(HttpServletRequest request) {
        logger.info("Searched product: {}", request.getParameter("product"));
        return "form";
    }

    @GetMapping("/reset")
    public String resetEmail() {
        return "email";
    }

    @PostMapping("/reset")
    public String emailReset(@RequestParam String email) {
        logger.info("Email to be reset: {}", email);
        return "email";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registered() {
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@RequestParam String firstName,
                           @RequestParam String lastName,
                           Model model) {
        Student student = new Student(firstName, lastName);
        model.addAttribute("student",student);
        return "registered";
    }

    @GetMapping("/register/form")
    public String registerForm(Model model){
        Student student = new Student();
        model.addAttribute("student",student);
        return "registerForm";
    }
    @PostMapping("/register/form")
    public String registeredForm(@ModelAttribute Student student, Model model){
        model.addAttribute("student",student);
        return "registered";
    }



}
