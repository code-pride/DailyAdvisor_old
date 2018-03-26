package com.advisor.repository;

import com.advisor.model.entity.Meeting;
import com.advisor.model.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.UUID;

@Repository("meetingRepository")
public interface MeetingRepository extends SimplyRepository<Meeting> {

    Meeting findMeetingById(UUID meetingId);

    List<Meeting> findMeetingsByUserIdOrUserId2(User user, User user2);

    Meeting findByIdAndUserId2(UUID meetingId, User user);

    @Transactional
    @Modifying
    @Query("UPDATE Meeting m SET m.status = :status WHERE m.userId2 = :user")
    void updateMeeting(@Param("user") User user, @Param("status") String status);

    @Query("SELECT m FROM Meeting m WHERE m.id = :id AND (m.userId2 = :user OR m.userId = :user)")
    List<Meeting> findMeetingByIdAndUserId(@Param("id") UUID meetingId, @Param("user") User user);

    @Transactional
    @Modifying
    @Query("UPDATE Meeting m SET m = :meeting WHERE m = :meeting")
    void updateMeeting(@Param("meeting") Meeting meeting);

    @Query("SELECT m FROM Meeting m WHERE m.id=:id ")
    Meeting findByIdd(@Param("id") UUID id);


}
