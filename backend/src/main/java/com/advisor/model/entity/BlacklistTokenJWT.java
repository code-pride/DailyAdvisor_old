package com.advisor.model.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class BlacklistTokenJWT {

    @Id
    private String tokenId;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
}
