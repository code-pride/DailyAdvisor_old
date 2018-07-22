package com.advisor.controllers;

import com.advisor.model.entity.*;
import com.advisor.model.request.AdvertisementRequest;
import com.advisor.model.request.EventRequest;
import com.advisor.model.request.MeetingRequest;
import com.advisor.model.request.NewUserRequest;
import com.advisor.repository.CoachTypeRepository;
import com.advisor.repository.RecurringTypeRepository;
import com.advisor.repository.RoleRepository;
import com.advisor.repository.UserRepository;
import com.advisor.service.AdvertisementService;
import com.advisor.service.Exceptions.DataRepositoryException;
import com.advisor.service.Exceptions.EntityNotFoundException;
import com.advisor.service.MeetingService;
import com.advisor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class PopulateController {

    @Autowired
    private UserService userService;

    @Autowired
    private MeetingService meetingService;

    @Autowired
    private AdvertisementService advertisementService;

    @Autowired
    @Qualifier("roleRepository")
    private RoleRepository roleRepository;

    @Autowired
    @Qualifier("recurringTypeRepository")
    private RecurringTypeRepository recurringTypeRepository;

    @Autowired
    @Qualifier("coachTypeRepository")
    private CoachTypeRepository coachTypeRepository;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = { "/populate" }, method = RequestMethod.GET)
    public ResponseEntity populate() throws Exception
    {
        //save roles
        List<Role> roles = new ArrayList<>();
        roles.add(new Role("ADMIN"));
        roles.add(new Role("COACH"));
        roles.add(new Role("USER"));
        for (Role r : roles) {
            roleRepository.save(r);
        }

        //save CoachTypes
        List<CoachType> coachTypes = new ArrayList<>();
        coachTypes.add(new CoachType("bodybuilding"));
        coachTypes.add(new CoachType("fitness"));
        for (CoachType c : coachTypes) {
            coachTypeRepository.save(c);
        }

        //save recurrence types
        List<RecurringType> recurringTypes = new ArrayList<>();
        recurringTypes.add(new RecurringType("daily"));
        recurringTypes.add(new RecurringType("weekly"));
        recurringTypes.add(new RecurringType("monthly"));
        recurringTypes.add(new RecurringType("yearly"));
        for (RecurringType rt : recurringTypes) {
            recurringTypeRepository.save(rt);
        }


        //User1register1
        String email = "m@m.mm";
        NewUserRequest newUserRequest = new NewUserRequest(email, "Marek", "Makowski", "Katowice", "111111", "client");
        userService.registerClient(newUserRequest);
        User user = userService.findUserByEmail(email);
        user.setActive(true);
        user = userRepository.save(user);

        //adv1
        AdvertisementRequest advertisementRequest = new AdvertisementRequest("Genialne moje ogloszenie", "bodybuilding");
        advertisementService.setAdvertisement(user, advertisementRequest);

        //User2register2
        email = "r@r.rr";
        newUserRequest = new NewUserRequest(email, "Marcin", "Krawczyk", "Gliwice", "111111", "client");
        userService.registerClient(newUserRequest);

        //User2
        User user2 = userService.findUserByEmail(email);
        user2.setActive(true);
        user2 = userRepository.save(user2);

        //Meeting1
        try {
            MeetingRequest meetingRequest = new MeetingRequest(user2.getId().toString(), "Ziom dzis rano ustawka", new Location(50.243788, 50.243788), new EventRequest(new Date(235423342), new Date(12313231), new Time(1231413), new Time(12414111), false, false, null, null, null));
            meetingService.addMeeting(user, meetingRequest);


            //adv2
            advertisementRequest = new AdvertisementRequest("WYśmienite moje ogloszenie", "fitness");
            advertisementService.setAdvertisement(user2, advertisementRequest);

            //Meeting2
            meetingRequest = new MeetingRequest(user.getId().toString(), "Wieczorowe ciśnięcie na silowni", new Location(50.243788, 50.243788), new EventRequest(new Date(235423342), new Date(12313231), new Time(1231413), new Time(12414111), false, false, null, null, null));
            meetingService.addMeeting(user2, meetingRequest);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (DataRepositoryException e) {
            return new ResponseEntity(e.getStandardResponseCode());
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
