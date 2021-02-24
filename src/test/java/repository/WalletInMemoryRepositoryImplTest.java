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
    private Wallet walletWithId = new Wallet(1L, 1L, 0.0);
    private Wallet walletWithoutId = new Wallet(null, 1L, 0.0);
    private Wallet secondWallet = new Wallet(2L, 1L, 0.0);
    private Wallet editWalletWithoutId = new Wallet(null, 1L, 20.0);
    private Wallet editWalletWithId = new Wallet(1L, 1L, 20.0);

    @BeforeEach
    void setUp() {
        walletRepository = new WalletInMemoryRepositoryImpl(
                new ArrayList<>(Arrays.asList(walletWithId))
        );
    }

    @Test
    void create() {
        Wallet wallet = walletWithoutId;
        Wallet expected = secondWallet;

        assertEquals(expected, walletRepository.create(wallet));
    }

    @Test
    void edit() {
        Wallet edit = editWalletWithoutId;
        Wallet expected = editWalletWithId;

        assertEquals(expected, walletRepository.edit(1, edit));
    }

    @Test
    void editNegative() {
        Wallet edit = editWalletWithoutId;
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                walletRepository.edit(0, edit));

        assertEquals("id doesn't exist " + 0L, exception.getMessage());
    }

    @Test
    void getById() {
        Wallet expected = walletWithId;

        assertEquals(expected, walletRepository.getById(1));
    }

    @Test
    void list() {
        List<Wallet> expected = Arrays.asList(walletWithId);

        assertEquals(expected, walletRepository.list());
    }
}