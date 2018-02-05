package com.advisor.service;

import com.advisor.model.entity.*;
import com.advisor.model.request.DietListRequest;
import com.advisor.repository.*;
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

    @Autowired
    @Qualifier("recurringTypeRepository")
    private RecurringTypeRepository recurringType;

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Autowired
    @Qualifier("userDietRepository")
    private UserDietRepository userDietRepository;

    @Override
    public void addDietList(User user, DietListRequest dietListRequest) {
        Diet dietList = new Diet(user, dietListRequest);

        for (Meal meal : dietList.getMeals()) {
            if(meal.getEvent().getRecurring()){
                meal.getEvent().getRecurringPattern().setRecurringType(recurringType.findByRecurringName(meal.getEvent().getRecurringPattern().getRecurringType().getRecurringName()));
            }
            else {
                meal.getEvent().setRecurringPattern(null);
            }
            recurringPatternRepository.save(meal.getEvent().getRecurringPattern());
            eventRepository.save(meal.getEvent());
        }
        dietRepository.save(dietList);
    }

    @Override
    public Diet findByCreatorAndId(User user, long dietId) {
        List<Diet> dietList = dietRepository.findByCreatorAndId(user, dietId);
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

    @Override
    public UserDiet findUserDietByDietIdAndUser(Diet diet, User user) {
        List<UserDiet> userDiets = userDietRepository.findUserDietByUserAndId(user, diet);
        if(userDiets.size()!=0){
            return userDiets.get(0);
        }
        return null;
    }

    @Override
    public void addUserDiet(User user, Diet diet) {
        userDietRepository.save(new UserDiet(user, diet, "waiting"));
    }

    @Override
    public void useDietList(UserDiet userDiet) {
        userDiet.setStatus("used");
        userDietRepository.save(userDiet);
    }

    @Override
    public Diet findDietById(long dietId) {
        return dietRepository.findOneById(dietId);
    }

//    @Override
//    public Diet findByUserAndId(User user, long dietId) {
//        List<Diet> dietList = dietRepository.findByUserAndId(user, dietId);
//        if(dietList.size()>0){
//            return dietList.get(0);
//        }
//        else{
//            return null;
//        }
//    }

}