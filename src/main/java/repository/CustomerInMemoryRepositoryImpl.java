package repository;

import model.Customer;
import model.Wallet;

import java.util.List;

public class CustomerInMemoryRepositoryImpl implements Repository<Customer> {
    private List<Customer> customers;
    private Repository<Wallet> walletRepository;

    public CustomerInMemoryRepositoryImpl(
            List<Customer> customers,
            Repository<Wallet> walletRepository
    ) {
        this.customers = customers;
        this.walletRepository = walletRepository;
    }

    public Customer create(Customer entity) {
        Long id = customers.size() + 1L;

        walletRepository.create(new Wallet(id, id, 0.0));
        entity.setId(id);
        customers.add(entity);

        return entity;
    }

    public Customer edit(int id, Customer entity) {
        // guard against out of bound exception
        if(id <= 0 || id > customers.size()) {
            throw new IllegalArgumentException("id doesn't exist " + id);
        }

        Customer customer;
        customers.get(id - 1);
        customer = entity;
        customer.setId(((long) id));

//        try {
//             customers.get(id.intValue() - 1);
//             customer = entity;
//             customer.setId(id);
//        } catch (Throwable e) {
//            throw new IllegalArgumentException("id doesn't exist " + id);
//        }

        return customer;
    }

    public Customer getById(int id) {
        // guard against out of bound exception
        if(id <= 0 || id > customers.size()) {
            throw new IllegalArgumentException("id doesn't exist " + id);
        }

        return customers.get(id - 1);
    }

    public List<Customer> list() {
        return customers;
    }
}
