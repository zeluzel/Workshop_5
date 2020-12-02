package pl.coderslab.workshophibernate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.workshophibernate.entity.Book;
import pl.coderslab.workshophibernate.service.BookService;

import java.util.Map;

@Controller
@RequestMapping("/admin/books")
public class ManageBookController {

    private final BookService bookService;

    @Autowired
    public ManageBookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public String showPosts(Model model) {
        Map<Long, Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "/books/all";
    }


}
