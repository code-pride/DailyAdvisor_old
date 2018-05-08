package com.advisor.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Data
public class BlacklistTokenJWT {

    @Id
    @org.hibernate.annotations.Type(type = "pg-uuid")
    @Column(name = "tokenId",unique=true, nullable = false)
    private UUID tokenId;

    @ManyToOne(cascade = CascadeType.ALL)
    private User user;
}
