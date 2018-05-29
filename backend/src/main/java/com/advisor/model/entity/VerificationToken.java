package com.advisor.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.time.OffsetDateTime;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Objects;
import java.util.UUID;

@Entity
@Data
@Table(name = "verification_token")
public class VerificationToken {
    private static final int EXPIRATION = 60 * 60 * 24;

    @Id
    @org.hibernate.annotations.Type(type = "pg-uuid")
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",unique=true, nullable = false)
    private UUID id;

    @Column(nullable = false, name = "token")
    private String token;

    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;

    @Column(name = "expire_date")
    private OffsetDateTime expiryDate;

    public VerificationToken(User user, String token) {
        this.token = token;
        this.user = user;
        OffsetDateTime zdt = OffsetDateTime.now();
        this.expiryDate = zdt.plusSeconds(EXPIRATION);
    }

    public VerificationToken() {}

    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        return new Date(cal.getTime().getTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VerificationToken that = (VerificationToken) o;
        return Objects.equals(getToken(), that.getToken()) &&
                Objects.equals(getUser(), that.getUser()) &&
                Objects.equals(getExpiryDate(), that.getExpiryDate());
    }

}
