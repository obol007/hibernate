package pl.obol.repository;


import pl.obol.model.Book;
import pl.obol.model.Proposition;

import java.math.BigDecimal;
import java.util.List;

public interface IntfBook {

    void saveBook(Book book);

    Book findById(long id);

    void update(Book book);

    void delete(Book book);

    List<Book> findAll();

    List<Book> findAllByRating(BigDecimal rating);

}
