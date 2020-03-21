package com.anna.myfinance.dal.repos;

import com.anna.myfinance.dal.entities.Operation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OperationRepo extends JpaRepository<Operation,Long> {
}
