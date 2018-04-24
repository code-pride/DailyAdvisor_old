package com.advisor.model.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class BlacklistTokenJWT {

    @Id
    private String tokenId;
}
