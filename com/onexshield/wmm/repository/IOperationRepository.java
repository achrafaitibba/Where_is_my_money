package com.onexshield.wmm.repository;

import com.onexshield.wmm.model.operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository
public interface IOperationRepository extends JpaRepository<operation, UUID> {
    List<operation> getAllByAccount_AccountId(UUID id);

}
