package com.advisor.controllers;

import com.advisor.model.entity.Diet;
import com.advisor.model.entity.User;
import com.advisor.model.entity.UserDiet;
import com.advisor.model.request.DietListRequest;
import com.advisor.model.request.DietShareRequest;
import com.advisor.model.response.DietResponse;
import com.advisor.service.DietService;
import com.advisor.service.Exceptions.DietNotFoundException;
import com.advisor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
        if(diet != null && user2 !=null && diet.getStatus() != "disabled"){
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

    @RequestMapping(value = { "diet/use" }, method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity useDietPlan(@RequestBody long dietId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        Diet diet = dietService.findDietById(dietId);
        UserDiet userDiet = dietService.findUserDietByDietIdAndUser(diet, user);
        if(userDiet.getStatus().equals("used")){
            return new ResponseEntity(HttpStatus.IM_USED);
        }
        if (diet != null && user != null) {
            if (userDiet == null) {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
            else{
                dietService.useDietList(userDiet);
                return new ResponseEntity(HttpStatus.OK);
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = { "diet/disableDietList" }, method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity disableDietList(@RequestBody long dietId)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        try{
            dietService.setStatus(user, dietId, "disabled");
        } catch (DietNotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = { "diet/getAllDietLists" }, method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<DietResponse>> getAllDietLists()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        try{
            List<Diet> diets = dietService.getAllDietLists(user);
            List<DietResponse> dietResponses = new ArrayList<>();
            for (Diet diet : diets) {
                dietResponses.add(new DietResponse(diet));
            }
            return new ResponseEntity<>(dietResponses, HttpStatus.OK);
        } catch (DietNotFoundException e){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }

    }


}

//delete diet