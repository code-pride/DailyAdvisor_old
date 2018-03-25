package com.advisor.service;

import com.advisor.model.entity.Diet;
import com.advisor.model.entity.User;
import com.advisor.model.entity.UserDiet;
import com.advisor.repository.UserDietRepository;
import com.advisor.service.Exceptions.DataRepositoryException;
import com.advisor.service.Exceptions.EntityExists;
import com.advisor.service.Exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service("userDietService")
public class UserDietServiceImpl implements UserDietService {

    private static final String USER_DIET_NOT_FOUND_MESSAGE_CODE = "exception.entityNotFoundException.userDiet";

    private static final String USER_DIET_EXISTS_MESSAGE_CODE = "exception.entityNotFoundException.userDiet";

    @Autowired
    @Qualifier("userDietRepository")
    private UserDietRepository repository;

    @Override
    public UserDiet create(UserDiet userDiet) throws EntityExists {
        if(userDiet.getId() == null || repository.existsById(userDiet.getId())) {
            return repository.save(userDiet);
        } else {
            throw new EntityExists(USER_DIET_EXISTS_MESSAGE_CODE);
        }
    }

    @Override
    public void delete(UUID id) throws DataRepositoryException {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException(USER_DIET_NOT_FOUND_MESSAGE_CODE);
        }
    }

    @Override
    public List<UserDiet> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<UserDiet> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public UserDiet update(UserDiet userDiet) throws DataRepositoryException {
        if(repository.existsById(userDiet.getId())){
            return repository.save(userDiet);
        } else {
            throw new EntityNotFoundException(USER_DIET_NOT_FOUND_MESSAGE_CODE);
        }
    }

    @Override
    public UserDiet findByDietIdAndUser(Diet diet, User user) throws EntityNotFoundException {
        UserDiet userDiets = repository.findOneByUserAndDiet(user, diet);
        if(Optional.of(diet).isPresent()){
            return userDiets;
        } else{
            throw new EntityNotFoundException(USER_DIET_NOT_FOUND_MESSAGE_CODE);
        }
    }

    @Override
    public List<UserDiet> findByUser(User user) {
        return repository.findByUser(user);
    }

    @Override
    public void useDietList(UserDiet userDiet) throws DataRepositoryException {
        userDiet.setStatus("used");
        update(userDiet);
    }

}
