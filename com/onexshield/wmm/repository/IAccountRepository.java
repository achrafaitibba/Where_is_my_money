package com.onexshield.wmm.repository;

import com.onexshield.wmm.model.account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IAccountRepository extends JpaRepository<account, Integer> {
    account findByAccountId(Integer id);

    account findByUser_Email(String email);

    @Modifying
    @Query("update account a set a.accountStatus=com.onexshield.wmm.model.status.INACTIVE where a.user.email = ?1")
    void setInactive(@Param("email") String email);



}
