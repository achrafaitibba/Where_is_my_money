package com.onexshield.wmm.repository;

import com.onexshield.wmm.model.operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IOperationRepository extends JpaRepository<operation, Integer> {
    List<operation> getAllByAccountId(Integer id);

}
