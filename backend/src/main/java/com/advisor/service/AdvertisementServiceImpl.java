package com.advisor.service;

import com.advisor.model.entity.Advertisement;
import com.advisor.model.entity.CoachType;
import com.advisor.model.entity.User;
import com.advisor.model.request.AdvertisementRequest;
import com.advisor.model.response.AdvertisementResponse;
import com.advisor.repository.AdvertisementRepository;
import com.advisor.repository.CoachTypeRepository;
import com.advisor.service.Exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("AdvertisementService")
public class AdvertisementServiceImpl implements AdvertisementService {

    private static final String ADVERTISEMENT_NOT_FOUND_MESSAGE_CODE = "exception.entityNotFoundException.advertisement";

    private static final String ADVERTISEMENT_EXISTS_MESSAGE_CODE = "exception.entityNotFoundException.coach";

    @Autowired
    @Qualifier("advertisementRepository")
    private AdvertisementRepository repository;

    @Autowired
    @Qualifier("coachTypeRepository")
    private CoachTypeRepository coachTypeRepository;

    @Override
    public Advertisement create(Advertisement advertisement) throws EntityExists {
        if(advertisement.getId() == null || !repository.findById(advertisement.getId()).isPresent()) {
            advertisement.setVisits(advertisement.getVisits() + 1);
            return repository.save(advertisement);
        } else {
            throw new EntityExists(ADVERTISEMENT_EXISTS_MESSAGE_CODE);
        }
    }

    @Override
    public void delete(UUID id) throws DataRepositoryException {
        if (repository.existsById(id)) {

            repository.deleteById(id);
        } else {
            throw new EntityNotFoundException(ADVERTISEMENT_NOT_FOUND_MESSAGE_CODE);
        }
    }

    @Override
    public List<Advertisement> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Advertisement> findById(UUID id) {
        return repository.findById(id);
    }

    @Override
    public Advertisement update(Advertisement advertisement) throws DataRepositoryException, NoSuchElementException {
        if(repository.findById(advertisement.getId()).isPresent()){
            return repository.save(advertisement);
        } else {
            throw new EntityNotFoundException(ADVERTISEMENT_NOT_FOUND_MESSAGE_CODE);
        }
    }

    @Override
    public void setAdvertisement(User user, AdvertisementRequest advertisementRequest) {
        CoachType coachType = coachTypeRepository.findByType(advertisementRequest.getCoachType());
        Advertisement advertisement = new Advertisement(user, advertisementRequest.getAdvText(), coachType);
        repository.save(advertisement);
    }

    @Override
    public List<AdvertisementResponse> selectAll() {
        List<AdvertisementResponse> advertisementResponseList = new ArrayList<>();
        for (Advertisement advertisement : repository.findByStatus("active")) {
            advertisementResponseList.add(new AdvertisementResponse(advertisement));
        }
        return advertisementResponseList;
    }

    @Override
    public AdvertisementResponse findByUser(User user) {
        Advertisement advertisement = repository.findByUser(user);
        if (advertisement != null){
            return new AdvertisementResponse(advertisement);
        }
        else return null;
    }

    @Override
    public Advertisement findActiveAdvertisementByUser(User user) {
        return repository.findByUserAndStatus(user, "active");
    }

    @Override
    public void updateAdvertisement(User user, AdvertisementRequest advertisementRequest) {
        repository.updateAdvertisement(advertisementRequest.getAdvText(), user);
    }

    @Override
    public void updateStatus(UUID advId, User user, String status) throws EntityNotFoundException {
        if(repository.updateStatus(advId, user, status) == 0){
            throw new EntityNotFoundException(ADVERTISEMENT_NOT_FOUND_MESSAGE_CODE);
        }
    }

    @Override
    public List<Advertisement> getByCriteria(List<User> users, String type) {
        CoachType coachType = coachTypeRepository.findByType(type);
        return repository.findByUserInAndCoachTypeAndStatus(users, coachType, "active");
    }
}
