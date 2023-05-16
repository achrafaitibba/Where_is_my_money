package com.onexshield.wmm.repository;


import com.onexshield.wmm.model.account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAccountRepository extends JpaRepository<account,Integer> {

    Optional<account> findByEmail(String email);
}
