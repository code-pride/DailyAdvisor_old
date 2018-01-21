package com.advisor.repository;

import com.advisor.model.entity.Meeting;
import com.advisor.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository("meetingRepository")
public interface MeetingRepository extends JpaRepository<Meeting, Integer> {

    Meeting findMeetingByMeetingId(Long meetingId);

    List<Meeting> findMeetingsByUserIdOrUserId2(User user, User user2);

    @Transactional
    @Modifying
    @Query("update Meeting m set m.isAccepted = :accepted where m.userId2 = :user")
    void updateMeeting(@Param("user") User user, @Param("accepted") boolean accepted);

    Meeting findMeetingByMeetingIdAndUserId2(Long meetingId, User user);
}
