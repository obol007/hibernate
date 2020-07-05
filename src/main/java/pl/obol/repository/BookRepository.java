package pl.obol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.obol.annotation.BookRepo;
import pl.obol.model.Book;



@Repository
@BookRepo
public interface BookRepository extends IntfBook , JpaRepository<Book, Long>{

    @Override
    default void saveBook(Book book) {
    save(book);
    }

    @Override
    default void update(Book book) {
    save(book);
    }
}
