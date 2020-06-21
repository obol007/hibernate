package pl.obol.service;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.obol.model.Book;
import pl.obol.repository.IntfBook;

import java.sql.SQLException;

@Service
@Transactional
public class BookService {

    private final IntfBook intfBook;

    public BookService(@Qualifier("JDBC") IntfBook intfBook) {
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

}
