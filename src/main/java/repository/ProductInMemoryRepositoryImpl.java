package repository;

import model.Product;

import java.util.List;

public class ProductInMemoryRepositoryImpl implements Repository<Product> {
    private List<Product> products;

    public ProductInMemoryRepositoryImpl(List<Product> products) {
        this.products = products;
    }

    public Product create(Product entity) {
        Long id = products.size() + 1L;

        entity.setId(id);
        products.add(entity);

        return entity;
    }

    public Product edit(int id, Product entity) {
        // guard against out of bound exception
       if(id <= 0 || id > products.size()) {
            throw new IllegalArgumentException("id doesn't exist " + id);
        }

        Product product;
        products.get(id - 1);
        product = entity;
        product.setId(((long) id));

        return product;
    }

    public Product getById(int id) {
        // guard against out of bound exception
        if(id <= 0 || id > products.size()) {
            throw new IllegalArgumentException("id doesn't exist " + id);
        }

        return products.get(id - 1);
    }

    public List<Product> list() {
        return products;
    }
}
