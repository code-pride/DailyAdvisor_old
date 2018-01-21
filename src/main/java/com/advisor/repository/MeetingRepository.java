package com.advisor.repository;

import com.advisor.model.entity.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("meetingRepository")
public interface MeetingRepository extends JpaRepository<Meeting, Integer> {


}
