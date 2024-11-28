package by.polikarpov.service;

import by.polikarpov.entity.Readers;
import by.polikarpov.repository.ReadersRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReadersServiceIml implements CommonService<Readers, Long> {

    private final ReadersRepository repository;

    @Autowired
    public ReadersServiceIml(ReadersRepository repository) {
        this.repository = repository;
    }

    @Override
    public Readers save(Readers entity) {
        return repository.save(entity);
    }

    @Override
    public Readers update(Long id, Readers entity) {
        checkReaderById(id);

        entity.setId(id);
        return repository.save(entity);
    }

    @Override
    public Optional<Readers> getById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Readers> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        checkReaderById(id);

        repository.deleteById(id);
    }

    private void checkReaderById(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Reader not fount with ID: " + id);
        }
    }
}
