package pl.coderslab.workshophibernate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.coderslab.workshophibernate.entity.Book;
import pl.coderslab.workshophibernate.repository.BookRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Primary
public class JpaBookService implements BookService {

    private final BookRepository bookRepository;

    @Autowired
    public JpaBookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Map<Long, Book> getAllBooks() {
        List<Book> booksList = bookRepository.findAll();
        return booksList.stream()
                .collect(Collectors.toMap(Book::getId, book -> book));
    }

    @Override
    public Optional<Book> getBook(long id) {
        return bookRepository.findById(id);
    }

    @Override
    public boolean addBook(Book bookToAdd) {
        if (bookToAdd.getId() != null &&
                bookRepository.findById(bookToAdd.getId()).isPresent()) {
            return false;
        }
        bookRepository.save(bookToAdd);
        return true;
    }

    @Override
    public boolean editBook(Book bookToEdit) {
        if (bookToEdit.getId() == null ||
                !bookRepository.existsById(bookToEdit.getId())) {
            return false;
        }
        bookRepository.save(bookToEdit);
        return true;
    }

    @Override
    public boolean deleteBook(long id) {
        if (!bookRepository.existsById(id)) {
            return false;
        }
        bookRepository.deleteById(id);
        return true;
    }

    @Override
    public long getLastId() {
        return bookRepository.findFirstBookByOrderByIdDesc().getId();
    }

}