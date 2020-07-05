package pl.obol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.obol.model.Author;

import java.util.List;
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findAuthorByName(String name);

}
