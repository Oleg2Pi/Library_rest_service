package by.polikarpov.controller;

import by.polikarpov.entity.Readers;
import by.polikarpov.service.ReadersServiceIml;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("readers")
public class ReadersController {

    private final ReadersServiceIml service;

    @Autowired
    public ReadersController(ReadersServiceIml service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Readers> createReader(@RequestBody Readers reader) {
        return ResponseEntity.ok(service.save(reader));
    }

    @PostMapping("/{id}")
    public ResponseEntity<Readers> updateReader(@PathVariable Long id, @RequestBody Readers reader) {
        return ResponseEntity.ok(service.update(id, reader));
    }

    @GetMapping
    public ResponseEntity<List<Readers>> getReaders() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Readers> getReader(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReader(@PathVariable Long id) {
        try {
            service.delete(id);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

}
