package com.onexshield.wmm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
@Component
public class account {
    @Id
    @Column(name = "account_id")
    private UUID accountId ;

    @NotNull
    @Column(name = "creation_date")
    private Date creationDate = new Date();

    @NotNull
    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private currency currency;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumns(
            {
                    @JoinColumn(name = "fk_user_id", referencedColumnName = "user_id"),
                    @JoinColumn(name = "fk_user_email", referencedColumnName = "email")
            }
    )
    @NotNull
    private user user;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private status accountStatus = status.ACTIVE;

    @ManyToMany(cascade = CascadeType.ALL)
    @NotNull
    @Size(max = 3, min = 3)
    private List<securityAnswer> securityAnswers;

}
