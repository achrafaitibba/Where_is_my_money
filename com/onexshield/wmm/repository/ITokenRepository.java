package com.onexshield.wmm.repository;


import com.onexshield.wmm.configuration.token.token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ITokenRepository extends JpaRepository<token, Integer> {
    @Query("""
    select t from token t inner join account a on t.account.accountId = a.accountId
    where a.accountId = :userId and (t.expired = false or t.revoked = false)
""")
    List<token> findAllValidTokensByUser(Integer userId);
    Optional<token> findByToken(String token);
}
