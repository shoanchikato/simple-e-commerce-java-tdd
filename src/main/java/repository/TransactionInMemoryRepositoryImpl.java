package repository;

import model.Transaction;

import java.util.List;

public class TransactionInMemoryRepositoryImpl extends RepositoryImpl<Transaction> {

    public TransactionInMemoryRepositoryImpl(List<Transaction> entities) {
        super(entities);
    }
}
