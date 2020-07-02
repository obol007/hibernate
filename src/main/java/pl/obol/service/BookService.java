package pl.obol.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.obol.model.Book;
import pl.obol.repository.IntfBook;
import pl.obol.annotation.EntManager;

import java.math.BigDecimal;
import java.util.List;


@Service
@Transactional
public class BookService {


    private final IntfBook intfBook;

    public BookService(@EntManager IntfBook intfBook) {
        this.intfBook = intfBook;
    }

    public void saveBook(Book book) {
        intfBook.saveBook(book);
    }

    public Book findById(long id) {
        return intfBook.findById(id);
    }

    public void update(Book book){
        intfBook.update(book);
    }

    public void delete(Book book){
        intfBook.delete(book);
    }

    public List<Book> findAll() {
        return intfBook.findAll();
    }

    public List<Book> findAllByRating(BigDecimal rating) {
        return intfBook.findAllByRating(rating);
    }
}
