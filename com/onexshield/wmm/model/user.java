package com.onexshield.wmm.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
@Component
public class user  {

    @Id
    @Column(name = "user_id")
    private UUID user_id;

    @NotNull
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Column(name = "last_name")
    private String lastName;

    @Enumerated(EnumType.STRING)
    private gender gender;

    @Column(unique = true)
    private String email;

    private String password;

    @NotNull
    @Column(name = "birth_date")
    private Date birthDate;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private address address;

    @NotNull
    @Column(name = "phone_number")
    private String phoneNumber;

}
