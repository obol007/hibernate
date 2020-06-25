package pl.obol.controller;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.obol.model.Book;
import pl.obol.model.Publisher;
import pl.obol.service.BookService;
import pl.obol.service.PublisherService;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    private final Logger logger = LoggerFactory.getLogger(BookController.class);
    private final PublisherService publisherService;
    private final BookService bookService;

    public BookController(PublisherService publisherService, BookService bookService) {
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
        bookService.saveBook(book);
        model.addAttribute("book",book);
        return "bookSaved";
    }




}
