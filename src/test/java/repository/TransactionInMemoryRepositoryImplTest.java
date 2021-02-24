package repository;

import model.Product;
import model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TransactionInMemoryRepositoryImplTest {

    private Repository<Transaction> transctionRepository;
    private Transaction transactionWithId = new Transaction(
                    2L,
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
    private Transaction editTransactionWithId = new Transaction(
            1L,
            1L,
            new Date(),
            new ArrayList<>(Arrays.asList(new Product(1L, "Sunlight", 10.0, 5)))
    );
    private Transaction editTransactionWithoutId = new Transaction(
            null,
            1L,
            new Date(),
            new ArrayList<>(Arrays.asList(new Product(1L, "Sunlight", 10.0, 5)))
    );
    private Transaction transactionWithoutId = new Transaction(
            null,
            1L,
            new Date(),
            new ArrayList<>(Arrays.asList(new Product(1L, "Sunlight", 10.0, 2)))
    );

    @BeforeEach
    void setUp() {
        transctionRepository = new TransactionInMemoryRepositoryImpl(
                new ArrayList<>(Arrays.asList(transactionWithId))
        );
    }

    @Test
    void create() {
        Transaction transction = transactionWithoutId;
        Transaction expected = transactionWithId;

        assertEquals(expected, transctionRepository.create(transction));
    }

    @Test
    void edit() {
        Transaction edit = editTransactionWithoutId;
        Transaction expected = editTransactionWithId;

        assertEquals(expected, transctionRepository.edit(1, edit));
    }

    @Test
    void editNegative() {
        Transaction edit = editTransactionWithId;
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                transctionRepository.edit(0, edit));

        assertEquals("id doesn't exist " + 0L, exception.getMessage());
    }

    @Test
    void getById() {
        Transaction expected = transactionWithId;

        assertEquals(expected, transctionRepository.getById(1));

    }

    @Test
    void list() {
        List<Transaction> expected = Arrays.asList(transactionWithId);

        assertEquals(expected, transctionRepository.list());
    }
}