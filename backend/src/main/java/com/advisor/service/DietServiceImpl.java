package com.advisor.service;

import com.advisor.model.entity.*;
import com.advisor.model.request.DietListRequest;
import com.advisor.repository.*;
import com.advisor.service.Exceptions.DataRepositoryException;
import com.advisor.service.Exceptions.EntityExists;
import com.advisor.service.Exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("dietService")
public class DietServiceImpl implements DietService {

    private static final String DIET_NOT_FOUND_MESSAGE_CODE = "exception.entityNotFoundException.diet";

    private static final String DIET_EXISTS_MESSAGE_CODE = "exception.entityNotFoundException.diet";

    @Autowired
    @Qualifier("dietRepository")
    private DietRepository repository;

    @Autowired
    @Qualifier("userDietService")
    private UserDietService userDietService;

    @Autowired
    @Qualifier("mealService")
    private MealService mealService;

    @Autowired
    @Qualifier("eventService")
    private EventService eventService;

    @Autowired
    @Qualifier("recurringPatternService")
    private RecurringPatternService recurringPatternService;

    @Autowired
    @Qualifier("recurringTypeService")
    private RecurringTypeService recurringTypeService;

    @Override
    public Diet create(Diet diet) throws EntityExists {
        if(diet.getId() == null || !repository.existsById(diet.getId())) {
            return repository.save(diet);
        } else {
            throw new EntityExists(DIET_EXISTS_MESSAGE_CODE);
        }
    }

    @Override
    public void delete(UUID id) throws DataRepositoryException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException(DIET_NOT_FOUND_MESSAGE_CODE);
        }
    }

    @Override
    public List<Diet> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Diet> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Diet update(Diet diet) throws DataRepositoryException {
        if(repository.existsById(diet.getId())){
            return repository.save(diet);
        } else {
            throw new EntityNotFoundException(DIET_NOT_FOUND_MESSAGE_CODE);
        }
    }

    @Override
    public void addDietList(User user, DietListRequest dietListRequest) throws DataRepositoryException {
        Diet dietList = new Diet(user, dietListRequest);

        for (Meal meal : dietList.getMeals()) {
            if(meal.getEvent().getRecurring()){
                meal.getEvent().getRecurringPattern().setRecurringType(recurringTypeService.findByRecurringName(meal.getEvent().getRecurringPattern().getRecurringType().getRecurringName()));
                recurringPatternService.create(meal.getEvent().getRecurringPattern());
            }
            eventService.create(meal.getEvent());
            mealService.create(meal);
        }
        repository.save(dietList);
    }

    @Override
    public Diet findByCreatorAndId(User user, UUID dietId) throws EntityNotFoundException {
        Diet diet = repository.findOneByCreatedByAndId(user, dietId);
        if(Optional.of(diet).isPresent()){
            return diet;
        } else{
            throw new EntityNotFoundException(DIET_NOT_FOUND_MESSAGE_CODE);
        }
    }

    @Override
    public void addUserDiet(User user, Diet diet) throws DataRepositoryException {
        userDietService.create(new UserDiet(user, diet, "waiting"));
    }

    @Override
    public void setStatus(User user, UUID dietId, String status) throws DataRepositoryException {
        Diet diet = findByCreatorAndId(user, dietId);
        if(diet != null && diet.getStatus().equals("published")){
            diet.setStatus(status);
            update(diet);
        } else {
            throw new EntityNotFoundException(DIET_NOT_FOUND_MESSAGE_CODE);
        }
    }

    //TODO not optimal, should resolve it on db part
    @Override
    public List<Diet> getAllDietLists(User user) {
        List<Diet> dietList = repository.findByCreatedBy(user);
        List<UserDiet> userDietList = userDietService.findByUser(user);
        for (UserDiet userDiet : userDietList) {
            if(!dietList.contains(userDiet.getDiet())){
                dietList.add(userDiet.getDiet());
            }
        }
        return dietList;
    }

    @Override
    public Diet findByUserAndDietId(User user, UUID dietId) throws EntityNotFoundException {
        Diet diet = this.repository.findOneById(dietId);
        if(diet != null) {
            if (diet.getCreatedBy().equals(user)) {
                return diet;
            } else {
                UserDiet userDiet = userDietService.findByDietIdAndUser(diet, user);
                if(Optional.of(userDiet).isPresent()){
                    return userDiet.getDiet();
                }
            }
        }
        throw new EntityNotFoundException(DIET_NOT_FOUND_MESSAGE_CODE);
    }

    @Override
    public List<Diet> findAllUserDiets(User user) {
        List<UserDiet> userDiets = userDietService.findByUser(user);
        List<Diet> dietList = new ArrayList<>();
        for (UserDiet userDiet : userDiets) {
            dietList.add(userDiet.getDiet());
        }
        return dietList;
    }

    @Override
    public List<Diet> getAllActiveDiets(User user) {
        List<UserDiet> userDiets = userDietService.findByUser(user);
        List<Diet> dietList = new ArrayList<>();
        for (UserDiet userDiet : userDiets) {
            if(userDiet.getStatus().equals("used")) {
                dietList.add(userDiet.getDiet());
            }
        }
        return dietList;
    }

}