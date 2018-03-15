package com.advisor.controllers;

import com.advisor.model.entity.Train;
import com.advisor.model.entity.User;
import com.advisor.model.entity.UserTrain;
import com.advisor.model.request.TrainListRequest;
import com.advisor.model.request.TrainShareRequest;
import com.advisor.model.response.TrainResponse;
import com.advisor.service.Exceptions.TrainNotFoundException;
import com.advisor.service.TrainService;
import com.advisor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TrainController {

    @Autowired
    private UserService userService;

    @Autowired
    private TrainService trainService;

    @RequestMapping(value = { "train/addTrainList" }, method = RequestMethod.POST)
    public ResponseEntity addTrainList(@Valid @RequestBody TrainListRequest trainListRequest)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        trainService.addTrainList(user, trainListRequest);

        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = { "train/share" }, method = RequestMethod.PUT)
    public ResponseEntity shareTrainPlan(@Valid @RequestBody TrainShareRequest trainShareRequest)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        Train train = trainService.findByCreatorAndId(user, trainShareRequest.getTrainId());
        User user2 = userService.findUserById(trainShareRequest.getShareUser());
        if(train != null && user2 !=null && train.getStatus() != "disabled"){
            if(trainService.findUserTrainByTrainIdAndUser(train, user2) != null){
                return new ResponseEntity(HttpStatus.IM_USED);
            }
            if(user2 != null){
                trainService.addUserTrain(user2, train);
                trainService.updateTrain(train);
                return new ResponseEntity(HttpStatus.OK);
            }
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = { "train/use" }, method = RequestMethod.POST)
    public ResponseEntity useTrainPlan(@Valid @RequestBody long trainId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        Train train = trainService.findTrainById(trainId);
        if(train!=null) {
            UserTrain userTrain = trainService.findUserTrainByTrainIdAndUser(train, user);
            if (userTrain != null && userTrain.getStatus().equals("used")) {
                return new ResponseEntity(HttpStatus.IM_USED);
            }
            if (train != null && user != null) {
                if (userTrain == null) {
                    return new ResponseEntity(HttpStatus.BAD_REQUEST);
                } else {
                    trainService.useTrainList(userTrain);
                    return new ResponseEntity(HttpStatus.OK);
                }
            }
        }
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = { "train/disableTrainList" }, method = RequestMethod.PUT)
    public ResponseEntity disableTrainList(@Valid @RequestBody long trainId)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        try{
            trainService.setStatus(user, trainId, "disabled");
        } catch (TrainNotFoundException e){
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = { "train/getAllTrainLists" }, method = RequestMethod.GET)
    public ResponseEntity<List<TrainResponse>> getAllTrainLists()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        try{
            List<Train> trainList = trainService.getAllTrainLists(user);
            List<TrainResponse> trainResponses = new ArrayList<>();
            for (Train train : trainList) {
                if(!train.getStatus().equals("disabled")) {
                    trainResponses.add(new TrainResponse(train));
                }
            }
            return new ResponseEntity<>(trainResponses, HttpStatus.OK);
        } catch (TrainNotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = { "train/getTrainList/{trainId}" }, method = RequestMethod.GET)
    public ResponseEntity<TrainResponse> getTrainList(@PathVariable long trainId)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        try{
            Train train = trainService.findTrainByUserAndTrainId(user, trainId);
            return new ResponseEntity<>(new TrainResponse(train), HttpStatus.OK);
        } catch (TrainNotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = { "train/getAllTrainings" }, method = RequestMethod.GET)
    public ResponseEntity<List<TrainResponse>> getAllTrainings()
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        try{
            List<Train> trainList = trainService.getAllTrainings(user);
            List<TrainResponse> trainResponses = new ArrayList<>();
            for (Train train : trainList) {
                trainResponses.add(new TrainResponse(train));
            }
            return new ResponseEntity<>(trainResponses, HttpStatus.OK);
        } catch (TrainNotFoundException e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = { "train/remove" }, method = RequestMethod.POST)
    public ResponseEntity removeTrain(@Valid @RequestBody long trainId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        Train train = trainService.findTrainById(trainId);
        UserTrain userTrain = trainService.findUserTrainByTrainIdAndUser(train, user);
        if(userTrain != null && userTrain.getStatus().equals("used")){
            trainService.removeTrain(userTrain);
            return new ResponseEntity(HttpStatus.OK);
        } else{
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }

}
