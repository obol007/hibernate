package pl.obol.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.obol.annotation.BookRepo;
import pl.obol.annotation.EntManager;
import pl.obol.model.Author;
import pl.obol.model.Book;
import pl.obol.model.Proposition;
import pl.obol.repository.AuthorRepository;
import pl.obol.repository.IntfBook;
import pl.obol.repository.PropositionRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class BookService {


    private final IntfBook intfBook;
    private final AuthorRepository authorRepository;
    private final PropositionRepository propositionRepository;

    public BookService(@EntManager IntfBook intfBook,
                       AuthorRepository authorRepository,
                       PropositionRepository propositionRepository) {
        this.intfBook = intfBook;
        this.authorRepository = authorRepository;
        this.propositionRepository = propositionRepository;
    }

    public void saveBook(Book book) {

        List<Author> authors = book.getAuthors();
        List<Author> finalAuthorsList = new ArrayList<>();
        for (int i = 0; i < authors.size(); i++) {
            List<Author> resultList = authorRepository.findAuthorByName(authors.get(i).getName());
            if (!resultList.isEmpty()) {
                finalAuthorsList.add(resultList.get(0));
            } else {
                Author a = authors.get(i);
                authorRepository.save(a);
                finalAuthorsList.add(a);
            }
        }
        book.setAuthors(finalAuthorsList);

        intfBook.saveBook(book);

    }

    public Book findById(long id) {
        return intfBook.findById(id);
    }

    public void update(Book book) {
        intfBook.update(book);
    }

    public void delete(Book book) {
        intfBook.delete(book);
    }

    public List<Book> findAll() {
        return intfBook.findAll();
    }

    public List<Book> findAllByRating(BigDecimal rating) {
        return intfBook.findAllByRating(rating);
    }

    public void saveProposition(Proposition proposition) {
        propositionRepository.save(proposition);
    }
}
