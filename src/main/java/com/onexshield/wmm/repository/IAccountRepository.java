package com.onexshield.wmm.repository;


import com.onexshield.wmm.model.account;
import com.onexshield.wmm.model.profileImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;


public interface IAccountRepository extends JpaRepository<account, Long> {

    Optional<account> findByEmail(String email);

    @Query("""
    select case when count(a) > 0 then true else false end from account a where a.email = :email 
""")
    boolean existsByEmail(@Param("email") String email);

    account findByAccountId(Long integer);

    @Modifying
    @Query("update account a set" +
            " a.accountStatus = com.onexshield.wmm.model.status.INACTIVE " +
            "where a.accountId = ?1 ")
    int setInactive(@Param("email") Long id);


    @Modifying
    @Query("update account a set a.password = ?2 where a.accountId = ?1")
    int updatePassword(@Param("id") Long id, @Param("newPassword") String newPassword);

    @Modifying
    @Query("update account a set a.password = ?2 where a.email = ?1")
    int updatePassword(@Param("email") String email, @Param("newPassword") String newPassword);

//    @Modifying
//    @Query("""
//update account a set a.profileImage.imageId = :imageId where a.accountId = :accountId
//""")
//    int addImage(@Param("accountId") Long accountId, @Param("imageId") UUID imageId);
//
//    int updateByAccountId(Long accountId);
}