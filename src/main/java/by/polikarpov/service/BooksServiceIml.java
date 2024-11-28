package by.polikarpov.service;

import by.polikarpov.entity.Books;
import by.polikarpov.repository.BooksRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksServiceIml implements CommonService<Books, Long> {

    private final BooksRepository repository;

    @Autowired
    public BooksServiceIml(BooksRepository repository) {
        this.repository = repository;
    }

    @Override
    public Books save(Books entity) {
        return repository.save(entity);
    }

    @Override
    public Books update(Long id, Books entity) {
        checkBookById(id);

        entity.setId(id);
        return repository.save(entity);
    }

    @Override
    public Optional<Books> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Books> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        checkBookById(id);

        repository.deleteById(id);
    }

    private void checkBookById(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Book not found with ID: " + id);
        }
    }
}
