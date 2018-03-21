package com.advisor.service;

import com.advisor.model.entity.*;
import com.advisor.model.request.DietListRequest;
import com.advisor.repository.*;
import com.advisor.service.Exceptions.DietNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
    private RecurringTypeRepository recurringTypeRepository;

    @Autowired
    @Qualifier("userDietRepository")
    private UserDietRepository userDietRepository;

    @Override
    public void addDietList(User user, DietListRequest dietListRequest) {
        Diet dietList = new Diet(user, dietListRequest);

        for (Meal meal : dietList.getMeals()) {
            if(meal.getEvent().getRecurring()){
                meal.getEvent().getRecurringPattern().setRecurringType(recurringTypeRepository.findByRecurringName(meal.getEvent().getRecurringPattern().getRecurringType().getRecurringName()));
                recurringPatternRepository.save(meal.getEvent().getRecurringPattern());
            }
            else {
                meal.getEvent().setRecurringPattern(null);
                //recurringPatternRepository.save(meal.getEvent().getRecurringPattern());
            }

            eventRepository.save(meal.getEvent());
        }
        dietRepository.save(dietList);
    }

    @Override
    public Diet findByCreatorAndId(User user, UUID dietId) {
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
        List<UserDiet> userDiets = userDietRepository.findUserDietByUserAndDiet(user, diet);
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
    public Diet findDietById(UUID dietId) {
        return dietRepository.findOneById(dietId);
    }

    @Override
    public void setStatus(User user, UUID dietId, String status) throws DietNotFoundException{
        Diet diet = findByCreatorAndId(user, dietId);
        if(diet != null && diet.getStatus().equals("published")){
            diet.setStatus(status);
            dietRepository.save(diet);
        } else {
            throw new DietNotFoundException();
        }
    }

    @Override
    public List<Diet> getAllDietLists(User user) {
        List<Diet> dietList = dietRepository.findByCreatedBy(user);
        List<UserDiet> dietList2 = userDietRepository.findByUser(user);
        for (UserDiet userDiet : dietList2) {
            if(!dietList.contains(userDiet.getDiet())){
                dietList.add(userDiet.getDiet());
            }
        }
        return dietList;
    }

    @Override
    public void removeDiet(UserDiet userDiet) {
        userDiet.setStatus("waiting");
        userDietRepository.save(userDiet);
    }

    @Override
    public Diet findDietByUserAndDietId(User user, UUID dietId) throws DietNotFoundException{
        Diet diet = dietRepository.findOneById(dietId);
        if(diet != null) {
            if (diet.getCreatedBy().equals(user)) {
                return diet;
            } else {
                List<UserDiet> userDiets = userDietRepository.findUserDietByUserAndDiet(user, diet);
                if (userDiets.size() == 1) {
                    return userDiets.get(0).getDiet();
                }
            }
        }
        throw new DietNotFoundException();
    }

    @Override
    public List<Diet> getAllDiets(User user) {
        List<UserDiet> userDiets = userDietRepository.findByUser(user);
        List<Diet> dietList = new ArrayList<>();
        for (UserDiet userDiet : userDiets) {
            dietList.add(userDiet.getDiet());
        }
        return dietList;
    }

    @Override
    public List<Diet> getAllActiveDiets(User user) {
        List<UserDiet> userDiets = userDietRepository.findByUser(user);
        List<Diet> dietList = new ArrayList<>();
        for (UserDiet userDiet : userDiets) {
            if(userDiet.getStatus().equals("used")) {
                dietList.add(userDiet.getDiet());
            }
        }
        return dietList;
    }

}