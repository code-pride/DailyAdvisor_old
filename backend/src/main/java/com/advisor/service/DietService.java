package com.advisor.service;

import com.advisor.model.entity.Diet;
import com.advisor.model.entity.User;
import com.advisor.model.request.DietListRequest;
import com.advisor.service.Exceptions.DataRepositoryException;
import com.advisor.service.Exceptions.EntityNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DietService extends IService<Diet, UUID>{

    void addDietList(User user, DietListRequest dietListRequest) throws DataRepositoryException;

    Diet findByCreatorAndId(User user, UUID dietId) throws EntityNotFoundException;

    Diet update(Diet diet) throws DataRepositoryException;

    void addUserDiet(User user, Diet diet) throws DataRepositoryException;

    Optional<Diet> findById(UUID dietId);

    void setStatus(User user, UUID dietId, String disabled) throws DataRepositoryException;

    List<Diet> getAllDietLists(User user);

    Diet findByUserAndDietId(User user, UUID dietId) throws EntityNotFoundException;

    List<Diet> findAllUserDiets(User user);

    List<Diet> getAllActiveDiets(User user);

    Diet findOneByUserAndDiet(User user, Diet diet) throws EntityNotFoundException;
}
