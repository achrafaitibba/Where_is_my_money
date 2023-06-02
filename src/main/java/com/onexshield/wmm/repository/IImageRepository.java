package com.onexshield.wmm.repository;

import com.onexshield.wmm.model.profileImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface IImageRepository extends JpaRepository<profileImage, UUID> {

    //Optional<profileImage> findByImageIdAndAccountId(UUID imageId, Long accountId);


    @Query("""
        select i from profileImage i, account a 
        where i.imageId = :imageId 
        and a.profileImage.imageId = i.imageId 
        and a.accountId = :accountId 
""")
    Optional<profileImage> findByImageIdAndAccountId(@Param("imageId") UUID imageId, @Param("accountId") Long accountId);
}
