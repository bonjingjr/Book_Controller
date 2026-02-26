package com.example.webbackend.controller;

import com.example.webbackend.entity.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class BookController {

    private List<Book> books = new ArrayList<>();
    private Long nextId = 1L;

    public BookController() {
        books.add(new Book(nextId++, "Spring Boot in Action", "Craig Walls", 39.99));
        books.add(new Book(nextId++, "Effective Java", "Joshua Bloch", 45.00));
        books.add(new Book(nextId++, "Clean Code", "Robert Martin", 42.50));
        books.add(new Book(nextId++, "Java Concurrency in Practice", "Brian Goetz", 49.99));
        books.add(new Book(nextId++, "Design Patterns", "Gang of Four", 54.99));
        books.add(new Book(nextId++, "Head First Java", "Kathy Sierra", 35.00));
        books.add(new Book(nextId++, "Spring in Action", "Craig Walls", 44.99));
        books.add(new Book(nextId++, "Clean Architecture", "Robert Martin", 39.99));
        books.add(new Book(nextId++, "Refactoring", "Martin Fowler", 47.50));
        books.add(new Book(nextId++, "The Pragmatic Programmer", "Andrew Hunt", 41.99));
        books.add(new Book(nextId++, "You Don't Know JS", "Kyle Simpson", 29.99));
        books.add(new Book(nextId++, "JavaScript: The Good Parts", "Douglas Crockford", 32.50));
        books.add(new Book(nextId++, "Eloquent JavaScript", "Marijn Haverbeke", 27.99));
        books.add(new Book(nextId++, "Python Crash Course", "Eric Matthes", 38.00));
        books.add(new Book(nextId++, "Automate the Boring Stuff", "Al Sweigart", 33.50));
    }

    @GetMapping("/books")
    public List<Book> getBooks() {
        return books;
    }

    @GetMapping("/books/search")
    public List<Book> searchByTitle(
            @RequestParam(required = false, defaultValue = "") String title
    ) {
        if (title.isEmpty()) return books;
        return books.stream()
                .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    @GetMapping("/books/price-range")
    public List<Book> getBooksByPrice(
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice
    ) {
        return books.stream()
                .filter(book -> {
                    boolean min = minPrice == null || book.getPrice() >= minPrice;
                    boolean max = maxPrice == null || book.getPrice() <= maxPrice;
                    return min && max;
                })
                .collect(Collectors.toList());
    }

    @GetMapping("/books/sorted")
    public List<Book> getSortedBooks(
            @RequestParam(required = false, defaultValue = "title") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order
    ) {
        Comparator<Book> comparator;
        switch (sortBy.toLowerCase()) {
            case "author":
                comparator = Comparator.comparing(Book::getAuthor);
                break;
            case "price":
                comparator = Comparator.comparingDouble(Book::getPrice);
                break;
            default:
                comparator = Comparator.comparing(Book::getTitle);
                break;
        }
        if ("desc".equalsIgnoreCase(order)) comparator = comparator.reversed();
        return books.stream().sorted(comparator).collect(Collectors.toList());
    }

    @GetMapping("/books/paginated")
    public List<Book> getPaginatedBooks(
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size
    ) {
        int fromIndex = page * size;
        if (fromIndex >= books.size()) return new ArrayList<>();
        int toIndex = Math.min(fromIndex + size, books.size());
        return books.subList(fromIndex, toIndex);
    }

    @GetMapping("/books/filter")
    public List<Book> filterSortPage(
            @RequestParam(required = false, defaultValue = "") String title,
            @RequestParam(required = false, defaultValue = "") String author,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false, defaultValue = "title") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String order,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "5") int size
    ) {
        List<Book> result = books.stream()
                .filter(book -> title.isEmpty() || book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .filter(book -> author.isEmpty() || book.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .filter(book -> minPrice == null || book.getPrice() >= minPrice)
                .filter(book -> maxPrice == null || book.getPrice() <= maxPrice)
                .collect(Collectors.toList());

        Comparator<Book> comparator;
        switch (sortBy.toLowerCase()) {
            case "author":
                comparator = Comparator.comparing(Book::getAuthor);
                break;
            case "price":
                comparator = Comparator.comparingDouble(Book::getPrice);
                break;
            default:
                comparator = Comparator.comparing(Book::getTitle);
                break;
        }
        if ("desc".equalsIgnoreCase(order)) comparator = comparator.reversed();
        result.sort(comparator);

        int fromIndex = page * size;
        if (fromIndex >= result.size()) return new ArrayList<>();
        int toIndex = Math.min(fromIndex + size, result.size());
        return result.subList(fromIndex, toIndex);
    }

    @GetMapping("/books/{id}")
    public Book getBook(@PathVariable Long id) {
        return books.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @PostMapping("/books")
    public List<Book> createBook(@RequestBody Book book) {
        book.setId(nextId++);
        books.add(book);
        return books;
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId().equals(id)) {
                updatedBook.setId(id);
                books.set(i, updatedBook);
                return updatedBook;
            }
        }
        return null;
    }

    @PatchMapping("/books/{id}")
    public Book partialUpdateBook(@PathVariable Long id, @RequestBody Book partialBook) {
        for (Book book : books) {
            if (book.getId().equals(id)) {
                if (partialBook.getTitle() != null)  book.setTitle(partialBook.getTitle());
                if (partialBook.getAuthor() != null) book.setAuthor(partialBook.getAuthor());
                if (partialBook.getPrice() != null)  book.setPrice(partialBook.getPrice());
                return book;
            }
        }
        return null;
    }

    @DeleteMapping("/books/{id}")
    public List<Book> deleteBook(@PathVariable Long id) {
        books.removeIf(book -> book.getId().equals(id));
        return books;
    }
}