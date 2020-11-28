package pl.coderslab.workshophibernate.service;

import pl.coderslab.workshophibernate.entity.Book;

import java.util.Map;
import java.util.Optional;

public interface BookService {
    Map<Long, Book> getAllBooks();
    Optional<Book> getBook(long id);
    boolean addBook(Book bookToAdd);
    boolean editBook(Book bookToEdit);
    boolean deleteBook(long id);
    long getLastId();
}
