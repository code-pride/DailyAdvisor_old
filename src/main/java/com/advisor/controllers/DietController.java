package com.advisor.controllers;

import com.advisor.model.entity.Diet;
import com.advisor.model.entity.RecurringPattern;
import com.advisor.model.entity.RecurringType;
import com.advisor.model.entity.User;
import com.advisor.model.request.DietListRequest;
import com.advisor.model.request.DietShareRequest;
import com.advisor.model.request.EventRequest;
import com.advisor.model.request.MealRequest;
import com.advisor.service.DietService;
import com.advisor.service.EventService;
import com.advisor.service.UserService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Controller
public class DietController {

    @Autowired
    private UserService userService;

    @Autowired
    private DietService dietService;

    @RequestMapping(value = { "diet/addDietList" }, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addDietList(@RequestBody DietListRequest dietListRequest)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
//        DietListRequest dietListRequest1 = new DietListRequest();
//
//        dietListRequest1.setId(1);
//
//        Set<MealRequest> meals = new HashSet<>();
//
//        RecurringType recurringType = eventService.findByRecurringName("weekly");
//        RecurringPattern recurringPattern = new RecurringPattern(1, null, 2, null, null, null, recurringType);
//        EventRequest event = new EventRequest(new Date(235423342), new Date(12313231), new Time(1231413), new Time(12414111), false, true, null, null, recurringPattern);
//        MealRequest meal = new MealRequest(event, "Stejk wielki jak tur", 999, (float) 0.123, 1213);
//        meals.add(meal);
//        dietListRequest1.setMeals(meals);
//        Gson gson = new Gson();
//        String json = gson.toJson(dietListRequest1);
        dietService.addDietList(user, dietListRequest);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = { "diet/share" }, method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity shareDietPlan(@RequestBody DietShareRequest dietShareRequest)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        Diet diet = dietService.findByUserAndId(user, dietShareRequest.getDietId());
        if(diet != null){
            User user2 = userService.findUserById(dietShareRequest.getShareUser());
            if(user2.getId() == user.getId()){
                return new ResponseEntity(HttpStatus.CONFLICT);
            }
            if(user2 != null){
                diet.getUsers().add(user2);
                dietService.updateDiet(diet);
                return new ResponseEntity(HttpStatus.OK);
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}


//edit diet plan
//delete diet plan
//share diet plan
//list available plan lists
//adjust plan list
//edit diet
//delete diet