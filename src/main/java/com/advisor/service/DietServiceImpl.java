package com.advisor.service;

import com.advisor.model.entity.Diet;
import com.advisor.model.entity.Meal;
import com.advisor.model.entity.User;
import com.advisor.model.request.DietListRequest;
import com.advisor.repository.DietRepository;
import com.advisor.repository.EventRepository;
import com.advisor.repository.RecurringPatternRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("dietService")
public class DietServiceImpl implements DietService {

    @Autowired
    @Qualifier("dietRepository")
    private DietRepository dietRepository;

    @Autowired
    @Qualifier("eventRepository")
    private EventRepository eventRepository;

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
        dietRepository.save(dietList);
    }

    @Override
    public Diet findByUserAndId(User user, long dietId) {
        List<Diet> dietList = dietRepository.findByUserAndId(user, dietId);
        if(dietList.size()>0){
            return dietList.get(0);
        }
        else{
            return null;
        }
    }

    @Override
    public void updateDiet(Diet diet) {
        dietRepository.save(diet);
    }
}