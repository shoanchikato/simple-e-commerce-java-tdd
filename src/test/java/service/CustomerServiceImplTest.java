package service;

import model.Customer;
import model.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.CustomerInMemoryRepositoryImpl;
import repository.Repository;
import repository.WalletInMemoryRepositoryImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceImplTest {

    private Service<Customer> customerService;
    private Customer customer = new Customer(1L, "Jane", "Doe", 23);
    private Customer underAgeCustomer = new Customer(1L, "Jane", "Doe", 16);
    private Customer customerWithoutId = new Customer(null, "Jane", "Doe", 23);
    private Customer editCustomerWithId = new Customer(1L, "Jenny", "Doe", 23);
    private Customer editCustomerWithoutId = new Customer(null, "Jenny", "Doe", 23);

    @BeforeEach
    void setUp() {
        List<Customer> customers = new ArrayList<>(Arrays.asList(customer));
        List<Wallet> wallets = new ArrayList<>();

        Repository<Wallet> walletRepository = new WalletInMemoryRepositoryImpl(wallets);
        Repository<Customer> customerRepository = new CustomerInMemoryRepositoryImpl(customers, walletRepository);

        customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    void create() {
        Customer customer = customerWithoutId;
        Customer expected = customer;

        assertEquals(expected, customerService.create(customer));
    }

    @Test
    void createUnderAge() {
        Customer customer = underAgeCustomer;
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                customerService.create(customer));

        assertEquals("user is too young", exception.getMessage());
    }

    @Test
    void edit() {
        Customer edit = editCustomerWithoutId;
        Customer expected = editCustomerWithId;

        assertEquals(expected, customerService.edit(1, edit));
    }

    @Test
    void getById() {
        Customer expected = customer;

        assertEquals(expected, customerService.getById(1));
    }

    @Test
    void list() {
        List<Customer> expected = new ArrayList<>(Arrays.asList(customer));

        assertEquals(expected, customerService.list());
    }
}