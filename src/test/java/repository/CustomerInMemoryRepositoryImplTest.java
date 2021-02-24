package repository;

import model.Customer;
import model.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerInMemoryRepositoryImplTest {

    private Repository<Customer> customerRepository;
    private Repository<Wallet> walletRepository;
    private Customer customerWithId = new Customer(1L, "Jane", "Doe", 23);
    private Customer customerWithoutId = new Customer(null, "John", "Doe", 23);
    private Wallet walletWithoutId = new Wallet(null, 2L, 0.0);
    private Customer secondCustomer = new Customer(2L, "John", "Doe", 23);
    private Wallet secondCustomerWallet = new Wallet(2L, 2L, 0.0);
    private Customer editCustomerWithoutId = new Customer(null, "Jenny", "Doe", 23);
    private Customer editCustomerWithId = new Customer(1L, "Jenny", "Doe", 23);

    @BeforeEach
    void setUp() {
        List<Customer> customers = new ArrayList<>(Arrays.asList(customerWithId));
        walletRepository = new WalletInMemoryRepositoryImpl(new ArrayList<>(Arrays.asList()));

        customerRepository = new CustomerInMemoryRepositoryImpl(
                customers,
                walletRepository
        );
    }

    @Test
    void create() {
        Customer customer = customerWithoutId;
        Wallet wallet = walletWithoutId;

        Customer expectedCustomer = secondCustomer;
        Wallet expectedWallet = secondCustomerWallet;

        assertEquals(expectedCustomer, customerRepository.create(customer));
        assertEquals(expectedWallet, walletRepository.create(wallet));
    }

    @Test
    void edit() {
        Customer edit = editCustomerWithoutId;
        Customer expected = editCustomerWithId;

        assertEquals(expected, customerRepository.edit(1, edit));
    }

    @Test
    void editNegative() {
        Customer edit = customerWithoutId;
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                customerRepository.edit(0, edit));

        assertEquals("id doesn't exist " + 0L, exception.getMessage());
    }

    @Test
    void getById() {
        Customer expected = customerWithId;

        assertEquals(expected, customerRepository.getById(1));
    }

    @Test
    void list() {
        List<Customer> expected = Arrays.asList(customerWithId);

        assertEquals(expected, customerRepository.list());
    }
}