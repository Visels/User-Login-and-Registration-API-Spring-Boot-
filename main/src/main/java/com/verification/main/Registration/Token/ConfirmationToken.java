package com.verification.main.Registration.Token;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConfirmationToken {


    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )
    Long id;
    private String token;
    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;


    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt, LocalDateTime confirmedAT){
        this.token = token;
        this.createdAt  = createdAt;
        this.confirmedAt = confirmedAt;
        this.expiresAt = expiresAt;
    }
}
