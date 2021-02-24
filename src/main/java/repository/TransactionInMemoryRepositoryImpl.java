package repository;

import model.Transaction;

import java.util.List;

public class TransactionInMemoryRepositoryImpl implements Repository<Transaction> {
    private List<Transaction> transactions;


    public TransactionInMemoryRepositoryImpl(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public Transaction create(Transaction entity) {
        Long id = transactions.size() + 1L;

        entity.setId(id);
        transactions.add(entity);

        return entity;
    }

    public Transaction edit(int id, Transaction entity) {
        // guard against out of bound exception
        if(id <= 0 || id > transactions.size()) {
            throw new IllegalArgumentException("id doesn't exist " + id);
        }

        Transaction transaction;
        transactions.get(id - 1);
        transaction = entity;
        transaction.setId(((long) id));

        return transaction;
    }

    public Transaction getById(int id) {
        // guard against out of bound exception
        if(id <= 0 || id > transactions.size()) {
            throw new IllegalArgumentException("id doesn't exist " + id);
        }

        return transactions.get(id - 1);
    }

    public List<Transaction> list() {
        return transactions;
    }
}
