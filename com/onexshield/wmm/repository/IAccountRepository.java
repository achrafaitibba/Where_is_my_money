package com.onexshield.wmm.repository;

import com.onexshield.wmm.model.account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IAccountRepository extends JpaRepository<account, UUID> {
    account findByAccountId(UUID id);

    account findByUser_Email(String email);

    @Modifying
    @Query("update account a set" +
            " a.accountStatus = com.onexshield.wmm.model.status.INACTIVE " +
            "where a.accountId =" +
            " (select u.user_id from user u where u.email = ?1)")
    void setInactive(@Param("email") String email);




}
