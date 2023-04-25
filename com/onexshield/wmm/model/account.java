package com.onexshield.wmm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class account {
    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private Integer accountId;

    @OneToOne()
    @JoinColumn(name = "user_id")
    @PrimaryKeyJoinColumn(name = "account_id")
    private user user;

    @NotNull
    @Column(name = "creation_date")
    private Date creationDate;

    @NotNull
    @Column(name = "currency")
    private currency currency;


}
