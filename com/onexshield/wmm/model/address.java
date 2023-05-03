package com.onexshield.wmm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class address {
    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "address_id")
    private Integer addressId;
    @Column(name = "address_label")
    private String addressLabel;
    private String country;
    private String city;
}
