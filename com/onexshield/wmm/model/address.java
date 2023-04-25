package com.onexshield.wmm.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class address {
    private String addressLabel;
    private String country;
    private String city;
}
