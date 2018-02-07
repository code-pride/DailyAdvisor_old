package com.advisor.controllers;

import com.advisor.model.entity.Location;
import com.advisor.model.entity.RecurringType;
import com.advisor.model.entity.Role;
import com.advisor.model.entity.User;
import com.advisor.model.request.AdvertisementRequest;
import com.advisor.model.request.EventRequest;
import com.advisor.model.request.MeetingRequest;
import com.advisor.model.request.NewUserRequest;
import com.advisor.repository.RecurringTypeRepository;
import com.advisor.repository.RoleRepository;
import com.advisor.service.AdvertisementService;
import com.advisor.service.MeetingService;
import com.advisor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
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

    @RequestMapping(value = { "/populate" }, method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity getMeetingById()
    {
        //save roles
        List<Role> roles = new ArrayList();
        Role role = new Role("ADMIN");
        roles.add(role);
        role = new Role("COACH");
        roles.add(role);
        role = new Role("USER");
        roles.add(role);
        for (Role r : roles) {
            roleRepository.save(r);
        }

        //save recurrence types
        List<RecurringType> recurringTypes = new ArrayList();
        recurringTypes.add(new RecurringType("daily"));
        recurringTypes.add(new RecurringType("weekly"));
        recurringTypes.add(new RecurringType("monthly"));
        recurringTypes.add(new RecurringType("yearly"));
        for (RecurringType rt : recurringTypes) {
            recurringTypeRepository.save(rt);
        }



        //User1register1
        String email = "m@m.mm";
        NewUserRequest newUserRequest = new NewUserRequest(email, "Marek", "Makowski", "Katowice", "111111");
        userService.saveUser(newUserRequest);
        User user = userService.findUserByEmail(email);

        //adv1
        AdvertisementRequest advertisementRequest = new AdvertisementRequest("Zajebiste moje ogloszenie");
        advertisementService.setAdvertisement(user, advertisementRequest);

        //User2register2
        email = "r@r.rr";
        newUserRequest = new NewUserRequest(email, "Marcin", "Krawczyk", "Gliwice", "111111");
        userService.saveUser(newUserRequest);

        //Meeting1
        MeetingRequest meetingRequest = new MeetingRequest((long) 2, "Ziom dzis rano ustawka", new Location(50.243788, 50.243788), new EventRequest(new Date(235423342), new Date(12313231), new Time(1231413), new Time(12414111), false, false, null, null, null));
        meetingService.addMeeting(user, meetingRequest);

        //User2
        user = userService.findUserByEmail(email);

        //adv2
        advertisementRequest = new AdvertisementRequest("Zajebiste moje ogloszenie");
        advertisementService.setAdvertisement(user, advertisementRequest);

        //Meeting2
        meetingRequest = new MeetingRequest((long) 1, "Wieczorowe napierdalanie na silowni", new Location(50.243788, 50.243788), new EventRequest(new Date(235423342), new Date(12313231), new Time(1231413), new Time(12414111), false, false, null, null, null));
        meetingService.addMeeting(user, meetingRequest);


        return new ResponseEntity(HttpStatus.OK);
    }
}
