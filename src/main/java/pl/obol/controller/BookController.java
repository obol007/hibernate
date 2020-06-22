package pl.obol.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.obol.model.Book;
import pl.obol.model.Publisher;
import pl.obol.service.BookService;
import pl.obol.service.PublisherService;


import java.math.BigDecimal;

@Controller
@RequestMapping("/book")
public class BookController {

    BookService bookService;
    PublisherService publisherService;

    public BookController(BookService bookService, PublisherService publisherService) {
        this.bookService = bookService;
        this.publisherService = publisherService;
    }

    @GetMapping
    @ResponseBody
    public String addBook() {
        Publisher publisher = new Publisher();
        publisher.setName("Znak");
        publisherService.savePublisher(publisher);
        Book book = new Book();
        book.setAuthor("Sienkiewicz");
        book.setRating(new BigDecimal("9.7"));
        book.setTitle("Krzyżacy");
        book.setPublisher(publisher);
        bookService.saveBook(book);
        return String.format("Book %s has been saved!", book);
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String findOne(@PathVariable long id) {
        return bookService.findById(id).toString();
    }

    @GetMapping("/store")
    public String store(){
        return "bookStore";
    }
    @GetMapping("/update/{id}/{title}")
    @ResponseBody
    public String updateBook(@PathVariable long id, @PathVariable String title) {
        Book book = bookService.findById(id);
        book.setTitle(title);
        book.setDateUpdated(); //optional - to print an updated date
        bookService.update(book);
        return book.toString();
    }
    @GetMapping("/delete/{id}")
    @ResponseBody
    public String deleteBook(@PathVariable long id) {
        bookService.delete(bookService.findById(id));
        return "Book deleted!";
    }
}
