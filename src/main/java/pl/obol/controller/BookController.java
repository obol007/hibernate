package pl.obol.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.obol.model.Book;
import pl.obol.model.Proposition;
import pl.obol.model.Publisher;
import pl.obol.service.BookService;
import pl.obol.service.PublisherService;
import pl.obol.validator.BookValidation;
import pl.obol.validator.PropositionValidation;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.validation.groups.Default;
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
    public List<Publisher> publishers() {
        return publisherService.findAll();
    }


    @GetMapping("/add")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "addBook";
    }

    @PostMapping("/add")
    public String saveBook(@ModelAttribute
                               @Validated({PropositionValidation.class, BookValidation.class})
                                       Book book, BindingResult result, Model model) {
        if (book.getProposition()) {
            Set<ConstraintViolation<Book>> titleViolation = validator.validate(book, PropositionValidation.class);
            if (!titleViolation.isEmpty()) {
                for (ConstraintViolation<Book> violation : titleViolation) {
                    System.out.println("Proposition: " + violation.getPropertyPath() + " " + violation.getMessage());

                }
            }
            if (titleViolation.isEmpty()) {
                Proposition p = new Proposition();
                p.setTitle(book.getTitle());
                bookService.saveProposition(p);
                model.addAttribute("book", p);
                return "bookSaved";
            } else {
                return "addBook";
            }
        } else {
            Set<ConstraintViolation<Book>> bookViolation = validator.validate(book, BookValidation.class);

            if (bookViolation.isEmpty()) {
                bookService.saveBook(book);
                model.addAttribute("book", book);
                return "bookSaved";
            } else {
                return "addBook";
            }
        }

    }


}
