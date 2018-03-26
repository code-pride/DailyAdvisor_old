package com.advisor.service;

import com.advisor.model.entity.RecurringType;
import com.advisor.repository.RecurringTypeRepository;
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

@Service("recurringTypeService")
public class RecurringTypeServiceImpl implements RecurringTypeService {

    private static final String RECURRING_TYPE_FOUND_MESSAGE_CODE = "exception.entityNotFoundException.recurringType";

    private static final String RECURRING_TYPE_EXISTS_MESSAGE_CODE = "exception.entityNotFoundException.recurringType";

    @Autowired
    @Qualifier("recurringTypeRepository")
    private RecurringTypeRepository repository;

    @Override
    public RecurringType create(RecurringType recurringType) throws EntityExists {
        if(recurringType.getId() == null || repository.existsById(recurringType.getId())) {
            return repository.save(recurringType);
        } else {
            throw new EntityExists(RECURRING_TYPE_EXISTS_MESSAGE_CODE);
        }
    }

    @Override
    public void delete(UUID id) throws DataRepositoryException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException(RECURRING_TYPE_FOUND_MESSAGE_CODE);
        }
    }

    @Override
    public List<RecurringType> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<RecurringType> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public RecurringType update(RecurringType recurringType) throws DataRepositoryException, NoSuchElementException {
        if(repository.existsById(recurringType.getId())){
            return repository.save(recurringType);
        } else {
            throw new EntityNotFoundException(RECURRING_TYPE_FOUND_MESSAGE_CODE);
        }
    }

    @Override
    public RecurringType findByRecurringName(String recurringName) {
        return repository.findByRecurringName(recurringName);
    }
}
