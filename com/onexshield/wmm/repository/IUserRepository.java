package com.onexshield.wmm.repository;

import com.onexshield.wmm.model.user;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Repository
public interface IUserRepository extends JpaRepository<user, UUID> {
    @Modifying
    @Query("update user u set u.password = ?3 where u.user_id = ?1 and u.password = ?2")
    int updatePassword(@Param("id") UUID id, @Param("oldPassword") String oldPassword, @Param("newPassword") String newPassword);
}
