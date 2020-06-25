package pl.obol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.obol.model.Publisher;
import pl.obol.service.PublisherService;

@Controller
@RequestMapping("/publisher")
public class PublisherController {

    PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping("/add")
    public String addPublisher(Model model){
        model.addAttribute("publisher",new Publisher());
        return "addPublisher";
    }
    @PostMapping("/add")
    public String savePublisher(@ModelAttribute Publisher publisher, Model model){
        publisherService.savePublisher(publisher);
        model.addAttribute("publisher",publisher);
        return "publisherSaved";
    }
}
