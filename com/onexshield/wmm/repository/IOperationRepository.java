package com.onexshield.wmm.repository;

import com.onexshield.wmm.model.operation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IOperationRepository extends JpaRepository<operation, Integer> {

    operation findByOperationId(UUID id); // todo , why I created this method ?


    List<operation> findAllByAccount_AccountId(Integer id);
}
