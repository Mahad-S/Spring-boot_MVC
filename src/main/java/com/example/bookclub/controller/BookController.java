package com.example.bookclub.controller;

import com.example.bookclub.domain.Book;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BookController {

    // Returns a list of books for testing
    private List<Book> getSampleBooks() {
        return List.of(
                new Book("Harry Potter och de vises sten", "J.K. Rowling", "Fantasy"),
                new Book("Och så var de bara en", "Agatha Christie", "Deckare"),
                new Book("Brott och straff", "Fjodor Dostojevskij", "Klassiker"),
                new Book("Da Vinci-koden", "Dan Brown", "Thriller")
        );
    }

    // Handle home page request and add greeting and date
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("greeting", "Välkommen till Bokklubben!");
        model.addAttribute("currentDate", LocalDate.now());
        return "home";
    }

    // Display the list of all books
    @GetMapping("/books")
    public String bookList(Model model) {
        model.addAttribute("books", getSampleBooks());
        return "booklist";
    }

    // Show books by genre
    @GetMapping("/genre")
    public String filterByGenre(@RequestParam(required = false) String type, Model model) {
        List<Book> books = getSampleBooks();


        // Filter books by genre if a type is provided
        if (type != null && !type.isEmpty()) {
            books = books.stream()
                    .filter(b -> b.getGenre().equalsIgnoreCase(type))
                    .collect(Collectors.toList());
        }

        model.addAttribute("books", books);
        model.addAttribute("selectedType", type);
        return "genre";
    }
}