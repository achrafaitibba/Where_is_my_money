package com.onexshield.wmm.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.onexshield.wmm.authentication_configuration.token.token;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Component
//you can also extend the spring class Userdetails that have many other methods
public class account implements UserDetails {
    @Id
    @Column(name = "account_id")
    private Integer accountId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_person_id", referencedColumnName = "person_id")
    @NotNull
    private person person;

    @Column(unique = true)
    @NotNull
    private String email;

    @NotNull
    private String password;

    @Enumerated(EnumType.STRING)
    private role role;

    @Column(name = "creation_date")
    private Date creationDate ;

    @NotNull
    @Enumerated(EnumType.STRING)
    private currency currency;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private status accountStatus;

    @ManyToMany(cascade = CascadeType.ALL)
    @NotNull
    @Size(max = 3, min = 3)
    @JsonManagedReference
    private List<securityAnswer> securityAnswers;

    @OneToMany(mappedBy = "account")
    private List<token> tokens;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return  List.of(new SimpleGrantedAuthority(role.name()));
    }
    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}
