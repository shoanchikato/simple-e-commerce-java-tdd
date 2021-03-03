package repository;

import model.Product;

import java.util.List;

public class ProductInMemoryRepositoryImpl extends RepositoryImpl<Product> {

    public ProductInMemoryRepositoryImpl(List<Product> entities) {
        super(entities);
    }
}
