package com.advisor.controllers;

import com.advisor.model.entity.Location;
import com.advisor.model.entity.Role;
import com.advisor.model.entity.User;
import com.advisor.model.request.AdvertisementRequest;
import com.advisor.model.request.EventRequest;
import com.advisor.model.request.MeetingRequest;
import com.advisor.model.request.NewUserRequest;
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
import java.util.Date;

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

    @RequestMapping(value = { "/populate" }, method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity getMeetingById()
    {
        Role role = new Role("ADMIN");
        roleRepository.save(role);
        //User1register
        NewUserRequest newUserRequest = new NewUserRequest("m@m.mm", "Marek", "Makowski", "Katowice", "111111");
        userService.saveUser(newUserRequest);
        String email = "m@m.mm";
        User user = userService.findUserByEmail(email);
        //User2register
        newUserRequest = new NewUserRequest("r@r.rr", "Marcin", "Krawczyk", "Gliwice", "111111");
        userService.saveUser(newUserRequest);
        //adv
        AdvertisementRequest advertisementRequest = new AdvertisementRequest("Zajebiste moje ogloszenie");
        advertisementService.setAdvertisement(user, advertisementRequest);
        //Meeting
        MeetingRequest meetingRequest = new MeetingRequest((long) 2, "Ziom dzis rano ustawka", new Location(50.243788, 50.243788), new EventRequest(new Date(235423342), new Date(12313231), new Time(1231413), new Time(12414111), false, false, null, null));
        meetingService.addMeeting(user, meetingRequest);

        //User2
        email = "r@r.rr";
        user = userService.findUserByEmail(email);
        //adv
        advertisementRequest = new AdvertisementRequest("Zajebiste moje ogloszenie");
        advertisementService.setAdvertisement(user, advertisementRequest);
        //Meeting
        meetingRequest = new MeetingRequest((long) 1, "Wieczorowe napierdalanie na silowni", new Location(50.243788, 50.243788), new EventRequest(new Date(235423342), new Date(12313231), new Time(1231413), new Time(12414111), false, false, null, null));
        meetingService.addMeeting(user, meetingRequest);


        advertisementService.setAdvertisement(user, advertisementRequest);


        return new ResponseEntity(HttpStatus.OK);
    }
}
