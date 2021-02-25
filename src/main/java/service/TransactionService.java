package service;

import model.Transaction;

import java.util.List;

public interface TransactionService extends Service<Transaction> {
    List<Transaction> getByCustomerId(int id);
    List<Transaction> getByProductId(int id);
}
