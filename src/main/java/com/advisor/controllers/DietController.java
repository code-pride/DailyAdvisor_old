package com.advisor.controllers;

import com.advisor.model.entity.RecurringPattern;
import com.advisor.model.entity.RecurringType;
import com.advisor.model.entity.User;
import com.advisor.model.request.DietListRequest;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @Autowired
    private EventService eventService;

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
}


//edit diet plan
//delete diet plan
//share diet plan
//list available plan lists
//adjust plan list
//edit diet
//delete diet