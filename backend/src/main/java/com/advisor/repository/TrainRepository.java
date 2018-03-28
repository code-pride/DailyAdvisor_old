package com.advisor.repository;

import com.advisor.model.entity.Train;
import com.advisor.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository("trainRepository")
public interface TrainRepository extends SimplyRepository<Train> {

    @Query("SELECT t FROM Train t WHERE t.id = :id AND t.createdBy = :creator")
    List<Train> findByCreatorAndId(@Param("creator") User creator, @Param("id") UUID id);

    Train findOneById(UUID dietId);

    List<Train>  findByCreatedBy(User user);
}