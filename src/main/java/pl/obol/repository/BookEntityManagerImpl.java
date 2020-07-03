package pl.obol.repository;

import org.springframework.stereotype.Repository;
import pl.obol.model.Author;
import pl.obol.model.Book;
import pl.obol.annotation.EntManager;
import pl.obol.model.Proposition;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Repository
@EntManager
public class BookEntityManagerImpl implements IntfBook {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void saveBook(Book book) {
        if (!book.getProposition()) {
            List<Author> authors = book.getAuthors();
            List<Author> finalAuthorsList = new ArrayList<>();
            for (int i = 0; i < authors.size(); i++) {
                Query query = entityManager.createQuery("select a from Author a where a.name = :aName");
                query.setParameter("aName", authors.get(i).getName());
                List<Author> resultList = query.getResultList();
                if (!resultList.isEmpty()) {
                    finalAuthorsList.add(resultList.get(0));
                } else {
                    Author a = authors.get(i);
                    entityManager.persist(a);
                    finalAuthorsList.add(a);
                }
            }
            book.setAuthors(finalAuthorsList);
        }
        entityManager.persist(book);
    }



    @Override
    public Book findById(long id) {
        return entityManager.find(Book.class, id);
    }

    @Override
    public void update(Book book) {
        entityManager.merge(book);
    }

    @Override
    public void delete(Book book) {
        entityManager.remove(entityManager.contains(book) ? book : entityManager.merge(book));
    }

    @Override
    public List<Book> findAll() {
        Query query = entityManager.createQuery("select b from Book b");
        return query.getResultList();
    }

    @Override
    public List<Book> findAllByRating(BigDecimal rating) {
        Query query = entityManager.createQuery("select b from Book b where b.rating > :rating");
        query.setParameter("rating", rating);
        return query.getResultList();
    }

    @Override
    public void saveProposition(Proposition proposition) {
        entityManager.persist(proposition);
    }
}
