package by.polikarpov.controller;

import by.polikarpov.entity.Library;
import by.polikarpov.service.LibraryServiceIml;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryController {

    private final LibraryServiceIml service;

    @Autowired
    public LibraryController(LibraryServiceIml service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Library> createLibrary(@RequestBody Library library) {
        return ResponseEntity.ok(service.save(library));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Library> changeLibrary(@RequestBody Library library, @PathVariable Long id) {
        return ResponseEntity.ok(service.update(id, library));
    }

    @GetMapping
    public ResponseEntity<List<Library>> getAllLibrary() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Library> getLibraryById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibrary(@PathVariable Long id) {
        try {
            service.delete(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }

}
