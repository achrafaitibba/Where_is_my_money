package com.onexshield.wmm.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Component
public class person {

    @Id
    @Column(name = "person_id")
    @GeneratedValue
    private Integer personId;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @NotNull
    @Column(name = "birth_date")
    private Date birthDate;

    @Enumerated(EnumType.STRING)
    @NotNull
    private gender gender;

    @NotNull
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private address address;



}
