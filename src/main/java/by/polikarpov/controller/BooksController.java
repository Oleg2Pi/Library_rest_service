package by.polikarpov.controller;

import by.polikarpov.entity.Books;
import by.polikarpov.service.BooksServiceIml;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {

    private final BooksServiceIml service;

    @Autowired
    public BooksController(BooksServiceIml service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Books> createBook(@RequestBody Books book) {
        return ResponseEntity.ok(service.save(book));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Books> updateBook(@PathVariable Long id, @RequestBody Books book) {
        return ResponseEntity.ok(service.update(id, book));
    }

    @GetMapping
    public ResponseEntity<List<Books>> getAllBooks() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Books> getBookById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        try {
            service.delete(id);
        } catch(EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

}
