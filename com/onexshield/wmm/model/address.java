package com.onexshield.wmm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class address {
    @Id
    @Column(name = "address_id")
    @GeneratedValue
    private Long addressId ;

    @Column(name = "address_label")
    private String addressLabel;

    @NotNull
    private String country;

    @NotNull
    private String city;
}
