package service;

import model.Customer;
import repository.Repository;

import java.util.List;

public class CustomerServiceImpl implements Service<Customer> {
    private Repository<Customer> customerRepository;

    public CustomerServiceImpl(Repository<Customer> customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer create(Customer entity) {
        if(entity.getAge() < 18) {
            throw new IllegalArgumentException("user is too young");
        }

        return customerRepository.create(entity);
    }

    @Override
    public Customer edit(int id, Customer entity) {
        return customerRepository.edit(id, entity);
    }

    @Override
    public Customer getById(int id) {
        return customerRepository.getById(id);
    }

    @Override
    public List<Customer> list() {
        return customerRepository.list();
    }
}
