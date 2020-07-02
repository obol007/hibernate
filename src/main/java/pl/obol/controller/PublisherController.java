package pl.obol.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.obol.converter.PublisherConverter;
import pl.obol.model.Publisher;
import pl.obol.service.PublisherService;

import javax.validation.Valid;

@Controller
@RequestMapping("/publisher")
public class PublisherController {

    PublisherService publisherService;
    Logger logger = LoggerFactory.getLogger(PublisherConverter.class);

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping("/add")
    public String addPublisher(Model model) {
        model.addAttribute("newPublisher", new Publisher());
        return "addPublisher";
    }

    @PostMapping("/add")
    public String savePublisher(@ModelAttribute("newPublisher") @Valid Publisher publisher, BindingResult result, Model model) {
        model.asMap().forEach((k, v) -> logger.debug("ModelInfo: "+ k + ": " + v));
        if (!result.hasErrors()) {
            publisherService.savePublisher(publisher);
            model.addAttribute("publisher", publisher);
            return "publisherSaved";
        }
        return "addPublisher";


    }
}
