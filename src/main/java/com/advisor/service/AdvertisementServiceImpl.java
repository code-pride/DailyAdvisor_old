package com.advisor.service;

import com.advisor.model.entity.Advertisement;
import com.advisor.model.entity.User;
import com.advisor.model.request.AdvertisementRequest;
import com.advisor.repository.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service("AdvertisementService")
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    @Qualifier("advertisementRepository")
    private AdvertisementRepository advertisementRepository;

    @Override
    public void setAdvertisement(User user, AdvertisementRequest advertisementRequest) {
        ZoneId zoneId = ZoneId.of( "Europe/Paris" );
        ZonedDateTime zdt = ZonedDateTime.now( zoneId );
        java.util.Date date = java.util.Date.from( zdt.toInstant() );
        Advertisement advertisement = new Advertisement(user, advertisementRequest.getAdvText());
        advertisementRepository.save(advertisement);
    }
}
