package com.advisor.service;

import com.advisor.model.entity.Diet;
import com.advisor.model.entity.User;
import com.advisor.model.entity.UserDiet;
import com.advisor.model.request.DietListRequest;
import com.advisor.service.Exceptions.DietNotFoundException;

import java.util.List;
import java.util.UUID;

public interface DietService {

    void addDietList(User user, DietListRequest dietListRequest);

    Diet findByCreatorAndId(User user, UUID dietId);

    void updateDiet(Diet diet);

    UserDiet findUserDietByDietIdAndUser(Diet diet, User user);

    void addUserDiet(User user, Diet diet);

    void useDietList(UserDiet userDiet);

    Diet findDietById(UUID dietId);

    void setStatus(User user, UUID dietId, String disabled) throws DietNotFoundException;

    List<Diet> getAllDietLists(User user);

    void removeDiet(UserDiet userDiet);

    Diet findDietByUserAndDietId(User user, UUID dietId) throws DietNotFoundException;

    List<Diet> getAllDiets(User user);

    List<Diet> getAllActiveDiets(User user);

}
