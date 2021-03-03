package repository;

import model.Customer;
import model.Wallet;

import java.util.List;

public class CustomerInMemoryRepositoryImpl extends RepositoryImpl<Customer> {
    private Repository<Wallet> walletRepository;

    public CustomerInMemoryRepositoryImpl(
            List<Customer> entities,
            Repository<Wallet> walletRepository
    ) {
        super(entities);
        this.walletRepository = walletRepository;
    }

    @Override
    public Customer create(Customer entity) {
        Long id =  this.entities.size() + 1L;

        walletRepository.create(new Wallet(id, id, 0.0));
        entity.setId(id);
        this.entities.add(entity);

        return entity;
    }
}
