package com.advisor.controllers;

import com.advisor.model.entity.Diet;
import com.advisor.model.entity.User;
import com.advisor.model.entity.UserDiet;
import com.advisor.model.request.DietListRequest;
import com.advisor.model.request.DietShareRequest;
import com.advisor.service.DietService;
import com.advisor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

        Diet diet = dietService.findByCreatorAndId(user, dietShareRequest.getDietId());
        User user2 = userService.findUserById(dietShareRequest.getShareUser());
        if(diet != null && user2 !=null){
            if(dietService.findUserDietByDietIdAndUser(diet, user2) != null){
                return new ResponseEntity(HttpStatus.IM_USED);
            }
            if(user2 != null){
                dietService.addUserDiet(user2, diet);
                dietService.updateDiet(diet);
                return new ResponseEntity(HttpStatus.OK);
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

//    @RequestMapping(value = { "diet/use" }, method = RequestMethod.POST)
//    @ResponseBody
//    public ResponseEntity useDietPlan(@RequestBody DietListRequest dietListRequest)
//    {
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User user = userService.findUserByEmail(auth.getName());
//
//        Diet diet = new Diet(dietService.findByUserAndId(dietListRequest.getCreatorId(), dietListRequest.getDiet()));
//        dietService.addDietList(diet);
//        if(diet != null ){
//            User user2 = userService.findUserById(dietShareRequest.getShareUser());
//            if(user2.getId() == user.getId()){
//                return new ResponseEntity(HttpStatus.CONFLICT);
//            }
//            if(user2 != null){
//                diet.getUsers().add(user2);
//                dietService.updateDiet(diet);
//                return new ResponseEntity(HttpStatus.OK);
//            }
//        }
//        return new ResponseEntity(HttpStatus.NOT_FOUND);
//    }
}
//TODO przeslac caly dietlist wraz z id diety na ktorej sie bazuje

//edit diet plan
//delete diet plan
//list available plan lists
//adjust plan list
//edit diet
//delete diet