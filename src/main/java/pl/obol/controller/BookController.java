package pl.obol.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.obol.model.Book;
import pl.obol.model.Publisher;
import pl.obol.service.BookService;
import pl.obol.service.PublisherService;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/book")
public class BookController {

    private final Validator validator;
    private final Logger logger = LoggerFactory.getLogger(BookController.class);
    private final PublisherService publisherService;
    private final BookService bookService;

    public BookController(Validator validator, PublisherService publisherService, BookService bookService) {
        this.validator = validator;
        this.publisherService = publisherService;
        this.bookService = bookService;
    }

    @ModelAttribute("publishers")
    public List<Publisher> publishers(){
        return publisherService.findAll();
    }


    @GetMapping("/add")
    public String addBook(Model model){
        model.addAttribute("book", new Book());
        return "addBook";
    }
    @PostMapping("/add")
    public String saveBook(@ModelAttribute Book book, Model model){
        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        if(!violations.isEmpty()) {
            for(ConstraintViolation<Book> violation: violations){
                System.out.println(violation.getPropertyPath() + " " + violation.getMessage());
            }
        }
        else {
            bookService.saveBook(book);
            model.addAttribute("book", book);
        }
        return "bookSaved";
    }




}
