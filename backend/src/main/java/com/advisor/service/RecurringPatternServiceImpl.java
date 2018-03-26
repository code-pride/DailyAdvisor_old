package com.advisor.service;

import com.advisor.model.entity.RecurringPattern;
import com.advisor.model.entity.RecurringType;
import com.advisor.repository.RecurringPatternRepository;
import com.advisor.service.Exceptions.DataRepositoryException;
import com.advisor.service.Exceptions.EntityExists;
import com.advisor.service.Exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service("recurringPatternService")
public class RecurringPatternServiceImpl implements RecurringPatternService {

    private static final String RECURRING_PATTERN_NOT_FOUND_MESSAGE_CODE = "exception.entityNotFoundException.recurringPattern";

    private static final String RECURRING_PATTERN_EXISTS_MESSAGE_CODE = "exception.entityNotFoundException.recurringPattern";

    @Autowired
    @Qualifier("recurringPatternRepository")
    private RecurringPatternRepository repository;

    @Override
    public RecurringPattern create(RecurringPattern recurringPattern) throws EntityExists {
        if(recurringPattern.getId() == null || !repository.existsById(recurringPattern.getId())) {
            return repository.save(recurringPattern);
        } else {
            throw new EntityExists(RECURRING_PATTERN_EXISTS_MESSAGE_CODE);
        }
    }

    @Override
    public void delete(UUID id) throws DataRepositoryException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException(RECURRING_PATTERN_NOT_FOUND_MESSAGE_CODE);
        }
    }

    @Override
    public List<RecurringPattern> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<RecurringPattern> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public RecurringPattern update(RecurringPattern recurringPattern) throws DataRepositoryException, NoSuchElementException {
        if(repository.existsById(recurringPattern.getId())){
            return repository.save(recurringPattern);
        } else {
            throw new EntityNotFoundException(RECURRING_PATTERN_NOT_FOUND_MESSAGE_CODE);
        }
    }

}
