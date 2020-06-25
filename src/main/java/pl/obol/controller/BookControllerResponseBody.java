package pl.obol.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.List;

@Controller
@RequestMapping("/book")
public class BookControllerResponseBody {


    private final Logger logger
            = LoggerFactory.getLogger(BookControllerResponseBody.class);

    private final BookService bookService;
    private final PublisherService publisherService;


    public BookControllerResponseBody(BookService bookService,
                                      PublisherService publisherService) {
        this.bookService = bookService;
        this.publisherService = publisherService;

    }

    @GetMapping
    @ResponseBody
    public String addBook() {
        Publisher publisher = new Publisher();
        publisher.setName("Znak");
        publisher.setCity("Opole");
        publisherService.savePublisher(publisher);
        Book book = new Book();
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

    @GetMapping("/all")
    @ResponseBody
    public String findAll(){
        List<Book> books = bookService.findAll();
        return String.valueOf(books);
    }
//    @GetMapping("/all/{rating:\\d\\.\\d}")
    @GetMapping("/all/{rating:.+}")
    @ResponseBody
    public String findAllByRating(@PathVariable BigDecimal rating){
        List<Book> books = bookService.findAllByRating(rating);
        return String.valueOf(books);
    }
}
