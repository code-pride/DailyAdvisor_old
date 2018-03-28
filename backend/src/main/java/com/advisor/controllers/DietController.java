package com.advisor.controllers;

import com.advisor.model.entity.Diet;
import com.advisor.model.entity.User;
import com.advisor.model.entity.UserDiet;
import com.advisor.model.request.DietListRequest;
import com.advisor.model.request.DietShareRequest;
import com.advisor.model.response.DietResponse;
import com.advisor.service.DietService;
import com.advisor.service.Exceptions.DataRepositoryException;
import com.advisor.service.Exceptions.EntityNotFoundException;
import com.advisor.service.UserDietService;
import com.advisor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import com.advisor.validator.ValidationError;
import com.advisor.validator.ValidationErrorBuilder;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class DietController {

    @Autowired
    private UserService userService;

    @Autowired
    private DietService dietService;

    @Autowired
    private UserDietService userDietService;

    @RequestMapping(value = {"diet/addDietList"}, method = RequestMethod.POST)
    public ResponseEntity addDietList(@Valid @RequestBody DietListRequest dietListRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        try {
            dietService.addDietList(user, dietListRequest);
        } catch (DataRepositoryException e) {
            return new ResponseEntity(e.getStandardResponseCode());
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = {"diet/share"}, method = RequestMethod.PUT)
    public ResponseEntity shareDietPlan(@Valid @RequestBody DietShareRequest dietShareRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        try {

            Diet diet = dietService.findByCreatorAndId(user, UUID.fromString((dietShareRequest.getDietId())));

            Optional<User> user2 = userService.findById(UUID.fromString(dietShareRequest.getShareUser()));
            if (user2.isPresent() && "published".equals(diet.getStatus())) {
                Diet diet1 = dietService.findOneByUserAndDiet(user2.get(), diet);
                if(diet1 == null) {
                    dietService.addUserDiet(user2.get(), diet);
                    dietService.update(diet);
                    return new ResponseEntity(HttpStatus.OK);
                } else {
                    return new ResponseEntity(HttpStatus.IM_USED);
                }
            }
        } catch (DataRepositoryException e) {
            return new ResponseEntity(e.getStandardResponseCode());
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = {"diet/use"}, method = RequestMethod.POST)
    public ResponseEntity useDietPlan(@Valid @RequestBody UUID dietId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        Optional<Diet> diet = dietService.findById(dietId);
        try {
            if (diet.isPresent()) {
                UserDiet userDiet = userDietService.findByDietAndUser(diet.get(), user);
                if (userDiet.getStatus().equals("used")) {
                    return new ResponseEntity(HttpStatus.IM_USED);
                }
                userDietService.useDietList(userDiet);
                return new ResponseEntity(HttpStatus.OK);
            }
        } catch (DataRepositoryException e) {
            return new ResponseEntity(e.getStandardResponseCode());
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = {"diet/disableDietList"}, method = RequestMethod.PUT)
    public ResponseEntity disableDietList(@Valid @RequestBody UUID dietId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        try {
            dietService.setStatus(user, dietId, "disabled");
        } catch (DataRepositoryException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = {"diet/getAllDietLists"}, method = RequestMethod.GET)
    public ResponseEntity<List<DietResponse>> getAllDietLists() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        List<Diet> diets = dietService.getAllDietLists(user);
        List<DietResponse> dietResponseList = new ArrayList<>();
        for (Diet diet : diets) {
            dietResponseList.add(new DietResponse(diet));
        }
        return new ResponseEntity<>(dietResponseList, HttpStatus.OK);
    }

    @RequestMapping(value = {"diet/getDietList/{dietId}"}, method = RequestMethod.GET)
    public ResponseEntity<DietResponse> getDietList(@PathVariable UUID dietId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        try {
            Diet diet = dietService.findByUserAndDietId(user, dietId);
            return new ResponseEntity<>(new DietResponse(diet), HttpStatus.OK);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>(e.getStandardResponseCode());
        }
    }

    @RequestMapping(value = {"diet/getAllDiets"}, method = RequestMethod.GET)
    public ResponseEntity<List<DietResponse>> getAllDiets() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        List<Diet> dietList = dietService.findAllUserDiets(user);
        List<DietResponse> dietResponses = new ArrayList<>();
        for (Diet diet : dietList) {
            dietResponses.add(new DietResponse(diet));
        }
        return new ResponseEntity<>(dietResponses, HttpStatus.OK);
    }

    @RequestMapping(value = {"diet/remove"}, method = RequestMethod.POST)
    public ResponseEntity removeDiet(@Valid @RequestBody UUID dietId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        try {
            Optional<Diet> diet = dietService.findById(dietId);
            if (diet.isPresent()) {
                UserDiet userDiet = userDietService.findByDietAndUser(diet.get(), user);
                if (userDiet != null && userDiet.getStatus().equals("used")) {
                    userDietService.removeDiet(userDiet);
                    return new ResponseEntity(HttpStatus.OK);
                }
            }
        } catch (DataRepositoryException e) {
            return new ResponseEntity(e.getStandardResponseCode());
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationError handleException(MethodArgumentNotValidException exception) {
        return createValidationError(exception);
    }

    private ValidationError createValidationError(MethodArgumentNotValidException e) {
        return ValidationErrorBuilder.fromBindingErrors(e.getBindingResult());
    }
}
