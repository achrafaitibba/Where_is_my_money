package com.onexshield.wmm.repository;


import com.onexshield.wmm.model.account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface IAccountRepository extends JpaRepository<account,Integer> {

    Optional<account> findByEmail(String email);

    account findByAccountId(Integer integer);

    @Modifying
    @Query("update account a set" +
            " a.accountStatus = com.onexshield.wmm.model.status.INACTIVE " +
            "where a.accountId = ?1 ")
    void setInactive(@Param("email") Integer id);


    @Modifying
    @Query("update account a set a.password = ?2 where a.accountId = ?1")
    int updatePassword(@Param("id") Integer id, @Param("newPassword") String newPassword);

    @Modifying
    @Query("update account a set a.password = ?2 where a.email = ?1")
    int updatePassword(@Param("email") String email, @Param("newPassword") String newPassword);

}
