package com.onexshield.wmm.repository;


import com.onexshield.wmm.authentication_configuration.token.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ITokenRepository extends JpaRepository<Token, Integer> {
    @Query("""
    select t from Token t inner join account a on t.account.accountId = a.accountId
    where a.accountId = :userId and (t.expired = false or t.revoked = false)
""")
    List<Token> findAllValidTokensByUser(Integer userId);
    Optional<Token> findByToken(String token);
}
