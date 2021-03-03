package repository;

import model.Wallet;
import java.util.List;

public class WalletInMemoryRepositoryImpl extends RepositoryImpl<Wallet> {

    public WalletInMemoryRepositoryImpl(List<Wallet> entities) {
        super(entities);
    }
}
