package repository;

import model.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WalletInMemoryRepositoryImplTest {

    private Repository<Wallet> walletRepository;

    @BeforeEach
    void setUp() {
        walletRepository = new WalletInMemoryRepositoryImpl(
                new ArrayList<>(Arrays.asList(new Wallet(1L, 1L, 0.0)))
        );
    }

    @Test
    void create() {
        Wallet wallet = new Wallet(null, 2L, 0.0);
        Wallet expected = new Wallet(2L, 2L, 0.0);

        assertEquals(expected, walletRepository.create(wallet));
    }

    @Test
    void edit() {
        Wallet edit = new Wallet(1L, 1L, 10.0);
        Wallet expected = new Wallet(1L, 1L, 10.0);

        assertEquals(expected, walletRepository.edit(1, edit));
    }

    @Test
    void editNegative() {
        Wallet edit = new Wallet(1L, 1L, 0.0);
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                walletRepository.edit(0, edit));

        assertEquals("id doesn't exist " + 0L, exception.getMessage());
    }

    @Test
    void getById() {
        Wallet expected = new Wallet(1L, 1L, 0.0);

        assertEquals(expected, walletRepository.getById(1));
    }

    @Test
    void list() {
        List<Wallet> expected = Arrays.asList(new Wallet(1L, 1L, 0.0));

        assertEquals(expected, walletRepository.list());
    }
}