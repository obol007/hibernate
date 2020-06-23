package pl.obol.repository;

import org.springframework.stereotype.Repository;
import pl.obol.model.Book;
import pl.obol.repository.annotation.EntManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

@Repository
@EntManager
public class BookEntityManagerImpl implements IntfBook{

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public void saveBook(Book book){
        entityManager.persist(book);
    }
    @Override
    public Book findById(long id){
        return entityManager.find(Book.class, id);
    }
    @Override
    public void update(Book book){
        entityManager.merge(book);
    }
    @Override
    public void delete(Book book){
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
        query.setParameter("rating",rating);
        return query.getResultList();
    }
}
