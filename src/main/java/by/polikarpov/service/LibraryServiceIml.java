package by.polikarpov.service;

import by.polikarpov.entity.Library;
import by.polikarpov.repository.LibraryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryServiceIml implements CommonService<Library, Long> {

    private final LibraryRepository repository;

    @Autowired
    public LibraryServiceIml(LibraryRepository repository) {
        this.repository = repository;
    }

    @Override
    public Library save(Library entity) {
        return repository.save(entity);
    }

    @Override
    public Library update(Long id, Library entity) {
        checkLibraryById(id);

        entity.setId(id);
        return repository.save(entity);
    }

    @Override
    public Optional<Library> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Library> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        checkLibraryById(id);

        repository.deleteById(id);
    }

    private void checkLibraryById(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Student not found with ID " + id);
        }
    }
}
