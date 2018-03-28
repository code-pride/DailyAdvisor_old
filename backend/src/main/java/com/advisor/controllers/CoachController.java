package com.advisor.controllers;

import com.advisor.model.entity.Coaching;
import com.advisor.model.entity.User;
import com.advisor.model.entity.UserProfile;
import com.advisor.model.request.CoachingRequest;
import com.advisor.model.response.CoachingResponse;
import com.advisor.service.CoachService;
import com.advisor.service.Exceptions.DataRepositoryException;
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
public class CoachController {

    @Autowired
    private CoachService coachService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"coaching/addClient"}, method = RequestMethod.POST)
    public ResponseEntity addClient(@Valid @RequestBody CoachingRequest coachingRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        Optional<User> client = userService.findById(UUID.fromString(coachingRequest.getClientId()));
        if (client.isPresent()) {
            Coaching coaching = coachService.findByCoachAndClient(user, client.get());
            try {
                if (coaching == null) {
                    Coaching newCoaching = new Coaching(user, client.get());
                    coachService.create(newCoaching);
                    return new ResponseEntity(HttpStatus.OK);
                } else if (coaching.getStatus().equals("sent")) {
                    coaching.setStatus("accepted");
                    coachService.update(coaching);
                    return new ResponseEntity(HttpStatus.OK);
                } else {
                    return new ResponseEntity(HttpStatus.IM_USED);
                }
            } catch (DataRepositoryException e) {
                return new ResponseEntity<>(e.getStandardResponseCode());
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = {"client/getClientCoaches"}, method = RequestMethod.GET)
    public ResponseEntity<List<CoachingResponse>> getClientCoaches() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        List<Coaching> coachingList = coachService.findByClient(user);
        if (coachingList != null) {
            return new ResponseEntity<>(getCoachingResponsesByUser(coachingList, "client"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = {"coaching/getCoachClients"}, method = RequestMethod.GET)
    public ResponseEntity<List<CoachingResponse>> getCoachClients() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        List<Coaching> coachingList = coachService.findByCoach(user);
        if (coachingList != null) {
            return new ResponseEntity<>(getCoachingResponsesByUser(coachingList, "coach"), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = {"client/acceptCoaching"}, method = RequestMethod.POST)
    public ResponseEntity acceptCoaching(@Valid @RequestBody CoachingRequest coachingRequest) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByEmail(auth.getName());
            Optional<User> coach = userService.findById(UUID.fromString(coachingRequest.getCoachId()));
            if (coach.isPresent()) {
                Coaching coaching = coachService.findByCoachAndClient(coach.get(), user);
                if (coaching.getStatus().equals("sent")) {
                    coaching.setStatus("accepted");
                    coachService.update(coaching);
                    return new ResponseEntity(HttpStatus.OK);
                } else {
                    return new ResponseEntity(HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (DataRepositoryException e) {
            return new ResponseEntity(e.getStandardResponseCode());
        }
    }

    @RequestMapping(value = {"client/cancelCoaching"}, method = RequestMethod.POST)
    public ResponseEntity leaveCoach(@Valid @RequestBody CoachingRequest coachingRequest) {
        try {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User user = userService.findUserByEmail(auth.getName());
            Optional<User> coach = userService.findById(UUID.fromString(coachingRequest.getCoachId()));
            if (coach.isPresent()) {
                Coaching coaching = coachService.findByCoachAndClient(coach.get(), user);
                if (coaching.getStatus().equals("sent") || coaching.getStatus().equals("accepted")) {
                    coaching.setStatus("canceled");
                    coachService.update(coaching);
                    return new ResponseEntity(HttpStatus.OK);
                } else {
                    return new ResponseEntity(HttpStatus.NOT_FOUND);
                }
            } else {
                return new ResponseEntity(HttpStatus.NOT_FOUND);
            }
        } catch (DataRepositoryException e) {
            return new ResponseEntity(e.getStandardResponseCode());
        }
    }

    private List<CoachingResponse> getCoachingResponsesByUser(List<Coaching> coachingList, String user) {
        if (!coachingList.isEmpty()) {
            List<User> users = new ArrayList<>();
            if (user.equals("coach")) {
                for (Coaching coaching : coachingList) {
                    users.add(userService.findById(coaching.getClient().getId()).get());
                }
            } else if (user.equals("client")) {
                for (Coaching coaching : coachingList) {
                    users.add(userService.findById(coaching.getCoach().getId()).get());
                }
            }
            List<UserProfile> userProfileList = userService.findByUsers(users);
            List<CoachingResponse> coachingResponsesList = new ArrayList<>();
            int i = 0;
            for (UserProfile userProfile : userProfileList) {
                coachingResponsesList.add(new CoachingResponse(userProfile, coachingList.get(i).getStatus()));
                i++;
            }
            return coachingResponsesList;
        }
        return new ArrayList<>();
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
