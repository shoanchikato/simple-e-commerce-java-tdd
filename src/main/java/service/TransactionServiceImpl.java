package service;

import model.Customer;
import model.Product;
import model.Transaction;
import model.Wallet;
import repository.Repository;

import java.util.List;

public class TransactionServiceImpl implements TransactionService {
    private Repository<Customer> customerRepository;
    private Repository<Wallet> walletRepository;
    private Repository<Product> productRepository;
    private Repository<Transaction> transactionRepository;

    public TransactionServiceImpl(
            Repository<Customer> customerRepository,
            Repository<Wallet> walletRepository,
            Repository<Product> productRepository,
            Repository<Transaction> transactionRepository
    ) {
        this.customerRepository = customerRepository;
        this.walletRepository = walletRepository;
        this.productRepository = productRepository;
        this.transactionRepository = transactionRepository;
    }

    @Override
    public Transaction create(Transaction transaction) {
        try {
            checkForStockAvailability(transaction);
            checkForSufficientFunds(transaction);
            deductCostFromWallet(transaction);
            deductPurchasedInventory(transaction);
        } catch (Throwable e) {
            throw e;
        }

        return transactionRepository.create(transaction);
    }

    private double totalCost(Transaction transaction) {

        double totalCost = 0;
        for(Product product : transaction.getProducts()) {
            Product fullDetails = productRepository.getById(product.getId().intValue());
            totalCost += product.getQuantity() * fullDetails.getPrice();
        }

        return totalCost;
    }

    private void checkForSufficientFunds(Transaction transaction) {
        Wallet wallet = walletRepository.getById(transaction.getCustomerId().intValue());

        if(wallet.getBalance() < totalCost(transaction)) {
            throw new IllegalArgumentException("insufficient funds");
        }
    }

    private void checkForStockAvailability(Transaction transaction) {

        for(Product product : transaction.getProducts()) {
            Product availableStock = productRepository.getById(product.getId().intValue());
            if(availableStock.getQuantity() == 0) {
                throw new IllegalArgumentException("out of stock of " + availableStock.getName());
            } else if (product.getQuantity() > availableStock.getQuantity()){
                throw new IllegalArgumentException("only " +
                        availableStock.getQuantity() + " items of " +
                        availableStock.getName()+ " with id number: " + availableStock.getId() + " are available");
            }
        }
    }

    private void deductCostFromWallet(Transaction transaction) {

        int walletId = transaction.getCustomerId().intValue();
        Wallet walletDetails = walletRepository.getById(walletId);

        double oldBalance = walletDetails.getBalance();
        double newBalance = oldBalance - totalCost(transaction);

        walletDetails.setBalance(newBalance);

        walletRepository.edit(walletId, walletDetails);
    }

    private void deductPurchasedInventory(Transaction transaction) {
        for(Product product : transaction.getProducts()) {
            Product availableStock = productRepository.getById(product.getId().intValue());
            int newAvailableQuantity = availableStock.getQuantity() - product.getQuantity();

            availableStock.setQuantity(newAvailableQuantity);
            productRepository.edit(availableStock.getId().intValue(), availableStock);
        }
    }

    @Override
    public Transaction edit(int id, Transaction transaction) {
        return null;
    }

    @Override
    public Transaction getById(int id) {
        return null;
    }

    @Override
    public List<Transaction> list() {
        return null;
    }

    @Override
    public List<Transaction> getByCustomerId(int id) {
        return null;
    }

    @Override
    public List<Transaction> getByProductId(int id) {
        return null;
    }
}
