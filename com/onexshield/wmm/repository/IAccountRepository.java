package com.onexshield.wmm.repository;

import com.onexshield.wmm.model.account;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends JpaRepository<account, Integer> {
    account findByAccountId(Integer id);

    account findByUser_Email(String email);



}
