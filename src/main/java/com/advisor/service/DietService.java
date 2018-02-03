package com.advisor.service;

import com.advisor.model.entity.Diet;
import com.advisor.model.entity.User;
import com.advisor.model.request.DietListRequest;

public interface DietService {
    void addDietList(User user, DietListRequest dietListRequest);

    Diet findByUserAndId(User user, long dietId);

    void updateDiet(Diet diet);
}
