package pl.obol.repository;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pl.obol.model.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Qualifier("EntityManager")
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
}
