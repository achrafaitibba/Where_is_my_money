package com.onexshield.wmm.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
@Component
public class account {
    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private Integer accountId;

    @NotNull
    @Column(name = "creation_date")
    private Date creationDate = new Date();

    @NotNull
    @Column(name = "currency")
    @Enumerated(EnumType.STRING)
    private currency currency;

    @OneToMany
    @JoinColumn(name = "fk_account_id",referencedColumnName = "account_id")
    private List<operation> operationList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private user user;
}
