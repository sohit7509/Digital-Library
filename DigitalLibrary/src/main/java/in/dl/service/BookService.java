package in.dl.service;

//import java.awt.print.Book;
import in.dl.entity.Book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.dl.repo.BookRepository;
@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public String addBook(Book book) {
        bookRepository.save(book);
        return "Book added successfully.";
    }

    public List<Book> viewAllBooks() {
        return bookRepository.findAll();
    }

    public Book searchBook(Integer id, String title) {
        if (id != null) {
            return bookRepository.findById(id).orElse(null);
        }
        if (title != null) {
            return bookRepository.findByTitle(title).orElse(null);
        }
        return null;
    }

    public String updateBook(int id, Book updatedBook) {
        bookRepository.save(updatedBook);
        return "Book updated successfully.";
    }

    public String deleteBook(int id) {
        bookRepository.deleteById(id);
        return "Book deleted successfully.";
    }
}