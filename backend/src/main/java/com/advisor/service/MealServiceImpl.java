package com.advisor.service;

import com.advisor.model.entity.Meal;
import com.advisor.repository.MealRepository;
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

@Service("mealService")
public class MealServiceImpl implements MealService {

    private static final String MEAL_NOT_FOUND_MESSAGE_CODE = "exception.entityNotFoundException.meal";

    private static final String MEAL_EXISTS_MESSAGE_CODE = "exception.entityNotFoundException.meal";

    @Autowired
    @Qualifier("mealRepository")
    private MealRepository repository;

    @Override
    public Meal create(Meal meal) throws EntityExists {
        if(meal.getId() == null || repository.existsById(meal.getId())) {
            return repository.save(meal);
        } else {
            throw new EntityExists(MEAL_EXISTS_MESSAGE_CODE);
        }
    }

    @Override
    public void delete(UUID id) throws DataRepositoryException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException(MEAL_NOT_FOUND_MESSAGE_CODE);
        }
    }

    @Override
    public List<Meal> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Meal> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Meal update(Meal meal) throws DataRepositoryException, NoSuchElementException {
        if(repository.existsById(meal.getId())){
            return repository.save(meal);
        } else {
            throw new EntityNotFoundException(MEAL_NOT_FOUND_MESSAGE_CODE);
        }
    }
}
