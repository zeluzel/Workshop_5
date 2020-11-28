package pl.coderslab.workshophibernate.fixture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.workshophibernate.entity.Book;
import pl.coderslab.workshophibernate.service.BookService;

import java.util.Map;

@Component
public class BookFixture {

    private final BookService bookService;

    private final Map<Long, Book> books = Map.of(
            1L, new Book(
                    1L, "9788324631766", "Pan Tadeusz",
                    "Adam Mickiewicz", "Helion", "dasdas"
            ),
            2L, new Book(
                    2L, "1433252323", "Pan Wołodyjowski",
                    "Henryk Sienkiewicz", "asfdasd", "dadas"
            ),
            3L, new Book(
                    3L, "12343132", "Inny Pan",
                    "Inny autor", "dfsdfsd", "dfsfsd"
            )
    );

    @Autowired
    public BookFixture(BookService bookService) {
        this.bookService = bookService;
    }

    public void loadIntoDb() {
        for (Map.Entry<Long, Book> book : books.entrySet()) {
            bookService.addBook(book.getValue());
        }
    }
}
