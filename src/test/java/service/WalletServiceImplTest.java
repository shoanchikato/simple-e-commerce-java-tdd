package service;

import model.Wallet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.Repository;
import repository.WalletInMemoryRepositoryImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WalletServiceImplTest {

    private Service<Wallet> walletService;
    private Wallet walletWithId = new Wallet(1L, 1L, 0);
    private Wallet secondWallet = new Wallet(2L, 1L, 0);
    private Wallet walletWithoutId = new Wallet(null, 1L, 0);

    @BeforeEach
    void setUp() {
        List<Wallet> wallets = new ArrayList<>(Arrays.asList(walletWithId));
        Repository<Wallet> walletRepository = new WalletInMemoryRepositoryImpl(wallets);
        walletService = new WalletServiceImpl(walletRepository);
    }

    @Test
    void create() {
        Wallet wallet = walletWithoutId;
        Wallet expected = secondWallet;

        assertEquals(expected, walletService.create(wallet));
    }

    @Test
    void edit() {

        Wallet edit = new Wallet(1L, 1L, 10);

        assertEquals(edit,walletService.edit(1,edit));

    }

    @Test
    void getById() {
        Wallet expected =walletWithId;
        assertEquals(expected,walletService.getById(1));




    }

    @Test
    void list() {

        List<Wallet> expected=new ArrayList<>(Arrays.asList(walletWithId));

        assertEquals(expected,walletService.list());




    }
}