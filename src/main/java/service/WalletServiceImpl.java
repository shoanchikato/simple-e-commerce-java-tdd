package service;

import model.Wallet;
import repository.Repository;

import java.util.List;

public class WalletServiceImpl implements Service<Wallet> {

    private Repository<Wallet> walletRepository;

    public WalletServiceImpl(Repository<Wallet> walletRepository) {
        this.walletRepository = walletRepository;
    }

    @Override
    public Wallet create(Wallet entity) {
        return walletRepository.create(entity);
    }

    @Override
    public Wallet edit(int id, Wallet entity) {
        return walletRepository.edit(id, entity);
    }

    @Override
    public Wallet getById(int id) {
        return walletRepository.getById(id);
    }

    @Override
    public List<Wallet> list() {
        return walletRepository.list();
    }
}
