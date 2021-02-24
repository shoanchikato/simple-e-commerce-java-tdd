package service;

import model.Product;
import repository.Repository;

import java.util.List;


public class ProductServiceImpl implements Service<Product> {


    private Repository<Product>  productRepository;

    public ProductServiceImpl(Repository<Product> productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product entity) {
        return productRepository.create(entity);
    }

    @Override
    public Product edit(int id, Product entity) {
        return productRepository.edit(id,entity);
    }

    @Override
    public Product getById(int id) {
        return productRepository.getById(id);
    }

    @Override
    public List<Product> list() {
        return productRepository.list();
    }
}
