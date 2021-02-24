package repository;

import model.Product;
import model.Transaction;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransactionInMemoryRepositoryImplTest {

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    private Repository<Transaction> transctionRepository;
    private Transaction transaction = new Transaction(
                    1L,
                    1L,
                    new Date(),
                    new ArrayList<>(Arrays.asList(new Product(1L, "Sunlight", 10.0, 2)))
            );
    private Transaction secondTransaction = new Transaction(
            1L,
            1L,
            new Date(),
            new ArrayList<>(Arrays.asList(new Product(2L, "Sunlight", 10.0, 2)))
    );
    private Transaction editTransaction = new Transaction(
            1L,
            1L,
            new Date(),
            new ArrayList<>(Arrays.asList(new Product(1L, "Sunlight", 10.0, 5)))
    );
    private Transaction transactionWithoutId = new Transaction(
            1L,
            1L,
            new Date(),
            new ArrayList<>(Arrays.asList(new Product(null, "Sunlight", 10.0, 2)))
    );

    @BeforeEach
    void setUp() {
        transctionRepository = new TransactionInMemoryRepositoryImpl(
                new ArrayList<>(Arrays.asList(transaction))
        );
    }

    @Test
    void create() {
        Transaction transction = transactionWithoutId;
        Transaction expected = transction;

        assertEquals(expected, transctionRepository.create(transction));
    }

    @Test
    void edit() {
        Transaction edit = editTransaction;
        Transaction expected = editTransaction;

        assertEquals(expected, transctionRepository.edit(1, edit));
    }

    @Test
    void editNegative() {
        Transaction edit = editTransaction;
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                transctionRepository.edit(0, edit));

        assertEquals("id doesn't exist " + 0L, exception.getMessage());
    }

    @Test
    void getById() {
        Transaction expected = transaction;

        assertEquals(expected, transctionRepository.getById(1));

    }

    @Test
    void list() {
        List<Transaction> expected = Arrays.asList(transaction);

        assertEquals(expected, transctionRepository.list());
    }
}