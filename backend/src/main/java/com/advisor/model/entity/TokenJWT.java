package com.advisor.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Data
public class TokenJWT {

    @Id
    @org.hibernate.annotations.Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "tokenId",unique=true, nullable = false)
    private UUID tokenId;

    @ManyToOne(cascade = CascadeType.MERGE)
    private User user;

    @Column(nullable = false)
    private Date timestamp;
}
