package pl.obol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.obol.model.Book;
import pl.obol.service.BookService;
import pl.obol.service.PublisherService;

@Controller
@RequestMapping("/publisher")
public class PublisherController {

    PublisherService publisherService;
    BookService bookService;

    public PublisherController(PublisherService publisherService,
                               BookService bookService) {
        this.publisherService = publisherService;
        this.bookService = bookService;
    }

    @GetMapping("/{publisherId}/{bookId}")
    @ResponseBody
    public String addBook(@PathVariable("bookId") long bId,
                          @PathVariable("publisherId") long pId){
        publisherService.addBookToPublisher(bId,pId);
        Book book = bookService.findById(bId);
        return String.format("Book %s added to the Publisher.",book);

    }
}
