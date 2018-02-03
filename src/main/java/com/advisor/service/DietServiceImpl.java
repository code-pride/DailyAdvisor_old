package com.advisor.service;

import com.advisor.model.entity.Diet;
import com.advisor.model.entity.Meal;
import com.advisor.model.entity.User;
import com.advisor.model.request.DietListRequest;
import com.advisor.repository.DietRepository;
import com.advisor.repository.EventRepository;
import com.advisor.repository.RecurringPatternRepository;
import com.advisor.repository.RecurringTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("dietService")
public class DietServiceImpl implements DietService {

    @Autowired
    @Qualifier("dietRepository")
    private DietRepository DietRepository;

    @Autowired
    @Qualifier("eventRepository")
    private EventRepository eventRepository;

    @Autowired
    @Qualifier("recurringTypeRepository")
    private RecurringTypeRepository recurringTypeRepository;

    @Autowired
    @Qualifier("recurringPatternRepository")
    private RecurringPatternRepository recurringPatternRepository;

    @Override
    public void addDietList(User user, DietListRequest dietListRequest) {
        Diet dietList = new Diet(user, dietListRequest);

        for (Meal meal : dietList.getMeals()) {
            recurringPatternRepository.save(meal.getEvent().getRecurringPattern());
            eventRepository.save(meal.getEvent());

        }
        DietRepository.save(dietList);
    }
}