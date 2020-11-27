package pl.coderslab.workshophibernate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.coderslab.workshophibernate.entity.Book;
import pl.coderslab.workshophibernate.service.BookService;

import java.util.Map;


@RestController
@RequestMapping("/books")
public class BookController {

    private BookService bookService;
    private final Logger logger = LoggerFactory.getLogger(BookController.class);

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
        logger.info("Created new BookController");
    }

    @RequestMapping("/helloBook")
    public Book helloBook() {
        return new Book(1L, "9788324631766", "Thinking in Java",
                "Bruce Eckel", "Helion", "programming");
    }

    @GetMapping("")
    public Map<Long, Book> getAllBooks() {
        logger.info("Fetched all books");
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBook(@PathVariable long id) {
        logger.info("Fetching book of id: {}", id);
        return bookService.getBook(id).orElseThrow(() -> {
                    logger.error("Failed: no book with such id");
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There's no book under given id");
                }
        );
    }

    @PostMapping("")
    public String addBook(@RequestBody Book bookToAdd) {
        logger.info("Adding book: {}", bookToAdd);
        if (!bookService.addBook(bookToAdd)) {
            logger.error("Failed: ID already taken");
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE,
                    "This id is already taken. Last applicable id is: " + bookService.getLastId() + 1);
        } else {
            logger.info("Success");
            return "Book successfully added";
        }
    }

    @PutMapping("")
    public String editBook(@RequestBody Book bookToEdit) {
        logger.info("Editing book: {}", bookToEdit);
        if (!bookService.editBook(bookToEdit)) {
            logger.error("Failed: ID doesn't exist");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "There's no book with id: " + bookToEdit.getId());
        } else {
            logger.info("Success", bookToEdit);
            return "Success";
        }
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable long id) {
        logger.info("Deleting book of id: {}", id);
        if (!bookService.deleteBook(id)) {
            logger.error("Failed: ID doesn't exist");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "There's no book with id: " + id);
        } else {
            logger.info("Success", bookService.getBook(id));
            return "Book successfully deleted";
        }
    }

}