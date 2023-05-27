package com.onexshield.wmm.configuration.token;

import com.onexshield.wmm.model.account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class token {
    @Id
    @GeneratedValue
    private UUID id;

    private String token;

    @Enumerated(EnumType.STRING)
    private tokenType tokenType;

    private boolean expired;
    private boolean revoked;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private account account;
}
