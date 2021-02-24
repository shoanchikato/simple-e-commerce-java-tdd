package repository;

import model.Customer;
import model.Wallet;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerInMemoryRepositoryImplTest {

    private Repository<Customer> customerRepository;
    private Repository<Wallet> walletRepository;

    @BeforeEach
    void setUp() {
        List<Customer> customers = new ArrayList<>(Arrays.asList(new Customer(1L, "Jane", "Doe", 23)));
        walletRepository = new WalletInMemoryRepositoryImpl(new ArrayList<>(Arrays.asList()));

        customerRepository = new CustomerInMemoryRepositoryImpl(
                customers,
                walletRepository
        );
    }

    @Test
    void create() {
        Customer customer = new Customer(null, "John", "Doe", 23);
        Wallet wallet = new Wallet(null, 2L, 0.0);

        Customer expectedCustomer = new Customer(2L, "John", "Doe", 23);
        Wallet expectedWallet = new Wallet(2L, 2L, 0.0);

        assertEquals(expectedCustomer, customerRepository.create(customer));
        assertEquals(expectedWallet, walletRepository.create(wallet));
    }

    @Test
    void edit() {
        Customer edit = new Customer(1L, "Jenny", "Doe", 23);
        Customer expected = new Customer(1L, "Jenny", "Doe", 23);

        assertEquals(expected, customerRepository.edit(1, edit));
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    void editNegative() {
        Customer edit = new Customer(null, "Jane", "Doe", 23);
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                customerRepository.edit(0, edit));

        assertEquals("id doesn't exist " + 0L, exception.getMessage());
    }

    @Test
    void getById() {
        Customer expected = new Customer(1L, "Jane", "Doe", 23);

        assertEquals(expected, customerRepository.getById(1));

    }

    @Test
    void list() {
        List<Customer> expected = Arrays.asList(new Customer(1L, "Jane", "Doe", 23));

        assertEquals(expected, customerRepository.list());
    }
}