package com.advisor.service;

import com.advisor.model.entity.Diet;
import com.advisor.model.entity.User;
import com.advisor.model.entity.UserDiet;
import com.advisor.service.Exceptions.DataRepositoryException;
import com.advisor.service.Exceptions.EntityNotFoundException;

import java.util.List;
import java.util.UUID;

public interface UserDietService extends IService<UserDiet, UUID> {

    UserDiet findByDietIdAndUser(Diet diet, User user) throws EntityNotFoundException;

    List<UserDiet> findByUser(User user);

    void useDietList(UserDiet userDiet) throws DataRepositoryException;

    void removeDiet(UserDiet userDiet) throws DataRepositoryException;
}

