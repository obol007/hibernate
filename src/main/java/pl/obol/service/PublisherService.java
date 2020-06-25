package pl.obol.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.obol.model.Book;
import pl.obol.model.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional
public class PublisherService {

    @PersistenceContext
    private EntityManager entityManager;

    public void savePublisher(Publisher publisher) {
        System.out.println("Publisher: "+publisher);
        entityManager.persist(publisher);
    }

    public void addBookToPublisher(long bId, long pId) {
        Book book = entityManager.find(Book.class, bId);
        Publisher publisher = entityManager.find(Publisher.class, pId);
        book.setPublisher(publisher);
        entityManager.persist(book);
    }
    public Publisher findPublisher(long id){
        return entityManager.find(Publisher.class, id);
    }

    public List<Publisher> findAll() {
        return entityManager.createQuery("select p from Publisher p").getResultList();
    }
}
