package com.anna.myfinance.services;

import com.anna.myfinance.dal.entities.Operation;
import com.anna.myfinance.dal.repos.AccountRepo;
import com.anna.myfinance.dal.repos.ItemRepo;
import com.anna.myfinance.dal.repos.OperationRepo;
import com.anna.myfinance.errors.ValidationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class OperationService {

    private final OperationRepo operationRepo;

    private final ItemRepo itemRepo;

    private final AccountRepo accountRepo;

    public OperationService(OperationRepo operationRepo, ItemRepo itemRepo, AccountRepo accountRepo) {
        this.operationRepo = operationRepo;
        this.itemRepo = itemRepo;
        this.accountRepo = accountRepo;
    }

    public Operation save(Operation operation) throws ValidationException {
        if (operation.getItem() == null || operation.getItem().getId() == null) {
            throw new ValidationException("Категория пуста");
        }
        if (!itemRepo.findById(operation.getItem().getId()).isPresent()) {
            throw new ValidationException(String.format("Категория с id: %d не найдена", operation.getItem().getId()));
        }

        if (operation.getDate() == null) {
            throw new ValidationException("Дата пуста");
        }
        if (operation.getAmount() == null || operation.getAmount().compareTo(BigDecimal.ZERO) == 0) {
            throw new ValidationException("Сумма пуста");
        }

        if (operation.getAccount() == null || operation.getAccount().getId() == null) {
            throw new ValidationException("Счет пуст");
        }
        if (!accountRepo.findById(operation.getAccount().getId()).isPresent()) {
            throw new ValidationException(String.format("Счёт с id: %d не найден", operation.getAccount().getId()));
        }

        return operationRepo.save(operation);
    }
}