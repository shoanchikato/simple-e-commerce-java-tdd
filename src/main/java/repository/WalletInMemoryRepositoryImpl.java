package repository;

import model.Wallet;

import java.util.List;

public class WalletInMemoryRepositoryImpl implements Repository<Wallet> {
    private List<Wallet> wallets;

    public WalletInMemoryRepositoryImpl(List<Wallet> wallets) {
        this.wallets = wallets;
    }

    public Wallet create(Wallet entity) {
        Long id = wallets.size() + 1L;

        entity.setId(id);
        wallets.add(entity);

        return entity;
    }

    public Wallet edit(int id, Wallet entity) {
        // guard against out of bound exception
        if(id <= 0 || id > wallets.size()) {
            throw new IllegalArgumentException("id doesn't exist " + id);
        }

        Wallet product;
        wallets.get(id - 1);
        product = entity;
        product.setId(((long) id));

        return product;
    }

    public Wallet getById(int id) {
        // guard against out of bound exception
        if(id <= 0 || id > wallets.size()) {
            throw new IllegalArgumentException("id doesn't exist " + id);
        }

        return wallets.get(id - 1);
    }

    public List<Wallet> list() {
        return wallets;
    }
}
