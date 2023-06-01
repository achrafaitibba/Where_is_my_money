package com.onexshield.wmm.repository;

import com.onexshield.wmm.model.address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface IAddressRepository extends JpaRepository<address, Long> {
    @Modifying
    @Query("""
update address a set a.addressLabel = ?1 , a.country = ?2 , a.city = ?3 where a.addressId = ?4
""")
    int updateByAddressId(String addressLabel,
                              String country,
                              String city,
                              Long id);
}
