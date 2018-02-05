package com.advisor.service;

import com.advisor.model.entity.Diet;
import com.advisor.model.entity.User;
import com.advisor.model.entity.UserDiet;
import com.advisor.model.request.DietListRequest;

public interface DietService {
    void addDietList(User user, DietListRequest dietListRequest);

    Diet findByCreatorAndId(User user, long dietId);

    void updateDiet(Diet diet);

    UserDiet findUserDietByDietIdAndUser(Diet diet, User user);

    void addUserDiet(User user, Diet diet);

    void useDietList(UserDiet userDiet);

    Diet findDietById(long dietId);

//    Diet findByUserAndId(User user, long dietId);
}
