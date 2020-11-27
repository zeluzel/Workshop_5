package pl.coderslab.workshophibernate.service;

import org.springframework.stereotype.Component;
import pl.coderslab.workshophibernate.entity.Book;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

@Component
public class MemoryBookService implements BookService {

    Map<Long, Book> allBooksMap = new LinkedHashMap<>();
    private long lastId = 3L;

    public MemoryBookService() {
        allBooksMap.put(1L, new Book(
                1L, "9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming"
        ));
        allBooksMap.put(2L, new Book(
                2L, "9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming"
        ));
        allBooksMap.put(3L, new Book(
                3L, "9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming"
        ));
    }

    @Override
    public Map<Long, Book> getAllBooks() {
        return allBooksMap;
    }

    @Override
    public Optional<Book> getBook(long id) {
        return Optional.ofNullable(allBooksMap.get(id));
    }

    @Override
    public boolean addBook(Book bookToAdd) {
        if (allBooksMap.containsKey(bookToAdd.getId())) {
            return false;
        } else {
            long id = ++lastId;
            bookToAdd.setId(id);
            allBooksMap.put(id, bookToAdd);
            return true;
        }
    }

    @Override
    public boolean editBook(Book bookToEdit) {
        long id = bookToEdit.getId();
        if (allBooksMap.containsKey(id)) {
            allBooksMap.put(id, bookToEdit);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteBook(long id) {
        if (allBooksMap.containsKey(id)) {
            allBooksMap.remove(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public long getLastId() {
        return lastId;
    }
}