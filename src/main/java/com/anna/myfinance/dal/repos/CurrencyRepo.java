package com.anna.myfinance.dal.repos;

import com.anna.myfinance.dal.entities.Currency;
import com.anna.myfinance.dal.entities.Item;
import com.anna.myfinance.dal.entities.Operation;
import com.anna.myfinance.dal.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyRepo extends JpaRepository<Currency,Long> {
}

