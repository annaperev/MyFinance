package com.anna.myfinance.dal.repos;

import com.anna.myfinance.dal.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepo extends JpaRepository<Transfer,Long> {
}
