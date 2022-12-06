package com.verification.main.Registration.Token;

import com.verification.main.appUser.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@Entity
//@AllArgsConstructor
public class ConfirmationToken {

@SequenceGenerator(
        name = "confirmation_sequence_token",
        sequenceName = "confirmation_sequence_token",
        allocationSize = 1
)
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "student_sequence"
    )@Column(nullable =false)
    private String token;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime expiresAt;
    private LocalDateTime confirmedAt;

    @ManyToOne
    @JoinColumn(
            nullable = false,
            name = "app_user_id"
    )
    private AppUser appUser;

    public ConfirmationToken(String token, LocalDateTime createdAt, LocalDateTime expiresAt, AppUser appUser){
        this.token = token;
        this.createdAt  = createdAt;
        this.expiresAt = expiresAt;
        this.appUser = appUser;
    }
}
