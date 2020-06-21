package pl.obol.repository;


import pl.obol.model.Book;

import java.sql.SQLException;

public interface IntfBook {

    void saveBook(Book book);

    Book findById(long id);

    void update(Book book);

    void delete(Book book);
}
