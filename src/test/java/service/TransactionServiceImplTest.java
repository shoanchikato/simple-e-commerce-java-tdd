package service;

import model.Customer;
import model.Product;
import model.Transaction;
import model.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TransactionServiceImplTest {

    private TransactionService transactionService;

    private Repository<Wallet> walletRepository;
    private Repository<Customer> customerRepository;
    private Repository<Product> productRepository;
    private Repository<Transaction> transactionRepository;

    private Customer customer = new Customer(1L, "Jane", "Doe", 23);
    private Wallet wallet = new Wallet(1L, 1L, 50.00);
    private Product product = new Product(1L, "Sunlight", 10.00, 50);
    private Product secondProduct = new Product(2L, "Cook Oil", 0.10, 0);
    List<Product> productList = new ArrayList<>(Arrays.asList(product, secondProduct));
    private Transaction transaction = new Transaction(1L, 1L, new Date(), productList);

    @BeforeEach
    void setUp() {
        List<Customer> customers = new ArrayList<>(Arrays.asList(customer));
        List<Wallet> wallets = new ArrayList<>(Arrays.asList(wallet));
        List<Transaction> transactionList = new ArrayList<>(Arrays.asList(transaction));

        walletRepository = new WalletInMemoryRepositoryImpl(wallets);
        customerRepository = new CustomerInMemoryRepositoryImpl(customers, walletRepository);
        productRepository = new ProductInMemoryRepositoryImpl(productList);
        transactionRepository = new TransactionInMemoryRepositoryImpl(transactionList);

        transactionService = new TransactionServiceImpl(
                customerRepository,
                walletRepository,
                productRepository,
                transactionRepository
        );
    }

    // when creating a transaction service should retrieve each product details from the provided
    // product id and check for the in stock quantities or throw out of stock exception also check
    // product id and then multiple the retrieved price by provided quantity (price * quantity)
    // then check for the customer balance from the customer repository
    // if customer balance is greater than total cost the transaction is created otherwise
    // throw exception with "insufficient funds" message

    @Test
    void create() {

    }

    @Test
    void createSufficientFunds() {
        Product sunlight = new Product(1L,10);
        List<Product> products = new ArrayList<>(Arrays.asList(sunlight));
        Transaction transaction = new Transaction(null, 1L, new Date(), products);

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                transactionService.create(transaction));

        assertEquals("insufficient funds", exception.getMessage());
    }

    @Test
    void createStockAvailability1() {
        Product cookingOil = new Product(2L,1);
        List<Product> products = new ArrayList<>(Arrays.asList(cookingOil));
        Transaction transaction = new Transaction(null, 1L, new Date(), products);

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                transactionService.create(transaction));

        assertEquals("out of stock of " + secondProduct.getName(), exception.getMessage());
    }

    @Test
    void createStockAvailability2() {
        Product cookingOil = new Product(1L,100);
        Product productDetails = product;
        List<Product> products = new ArrayList<>(Arrays.asList(cookingOil));
        Transaction transaction = new Transaction(null, 1L, new Date(), products);

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                transactionService.create(transaction));

        assertEquals("only " + productDetails.getQuantity() + " items of " +
                productDetails.getName() + " with id number: " + productDetails.getId() + " are available",
                exception.getMessage());
    }

    @Test
    void createDeductCostFromWallet() {
        Product sunlight = new Product(1L, 4);
        List<Product> products = new ArrayList<>(Arrays.asList(sunlight));
        Transaction transaction = new Transaction(null, 1L, new Date(), products);

        transactionService.create(transaction);
        double expected = 10.00;
        Wallet wallet = walletRepository.getById(1);

        assertEquals(expected, wallet.getBalance());
    }

    @Test
    void createDeductPurchasedInventory() {
        Product sunlight = new Product(1L, 4);
        List<Product> products = new ArrayList<>(Arrays.asList(sunlight));
        Transaction transaction = new Transaction(null, 1L, new Date(), products);

        transactionService.create(transaction);
        double expected = 46;
        Product product = productRepository.getById(1);

        assertEquals(expected, product.getQuantity());
    }

    @Test
    void edit() {
    }

    @Test
    void getById() {
    }

    @Test
    void list() {
    }

    @Test
    void getByCustomerId() {
    }

    @Test
    void getByProductId() {
    }
}