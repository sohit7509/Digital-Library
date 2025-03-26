package in.dl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;

import in.dl.entity.Book;
import in.dl.service.BookService;

@Controller
@RequestMapping("/books")
class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public String viewAllBooks(Model model) {
        model.addAttribute("books", bookService.viewAllBooks());
        return "books";
    }

    @GetMapping("/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "add-book";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute Book book) {
        bookService.addBook(book);
        return "redirect:/books/all";
    }

    @GetMapping("/search")
    public String searchBook(@RequestParam(required = false) Integer id, @RequestParam(required = false) String title, Model model) {
        model.addAttribute("book", bookService.searchBook(id, title));
        return "book-details";
    }

    @GetMapping("/update/{id}")
    public String showUpdateBookForm(@PathVariable int id, Model model) {
        model.addAttribute("book", bookService.searchBook(id, null));
        return "update-book";
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable int id, @ModelAttribute Book updatedBook) {
        bookService.updateBook(id, updatedBook);
        return "redirect:/books/all";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return "redirect:/books/all";
    }
}