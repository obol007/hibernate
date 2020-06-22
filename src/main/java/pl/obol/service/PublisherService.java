package pl.obol.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.obol.model.Book;
import pl.obol.model.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
@Transactional
public class PublisherService {

    @PersistenceContext
    private EntityManager entityManager;

    public void savePublisher(Publisher publisher) {
        entityManager.persist(publisher);
    }

    public void addBookToPublisher(long bId, long pId) {
        Book book = entityManager.find(Book.class, bId);
        Publisher publisher = entityManager.find(Publisher.class, pId);
        book.setPublisher(publisher);
        entityManager.persist(book);
    }
}
