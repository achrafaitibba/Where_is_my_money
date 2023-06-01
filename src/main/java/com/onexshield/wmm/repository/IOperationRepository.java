package com.onexshield.wmm.repository;

import com.onexshield.wmm.model.operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOperationRepository extends JpaRepository<operation, Long> {
    List<operation> findAllByAccount_AccountId(Long id);

}
