package service;

import model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.ProductInMemoryRepositoryImpl;
import repository.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceImplTest {

    private Service<Product> productService;
    private Product productWithoutId = new Product(null, "Sunlight", 10.00, 50);
    private Product productWithId = new Product(1L, "Sunlight", 10.00, 50);
    private Product editProductWithId = new Product(1L, "Sunlight", 20.00, 50);
    private Product editProductWithoutId = new Product(null, "Sunlight", 20.00, 50);
    private Product secondProduct = new Product(2L, "Sunlight", 10.00, 50);

    @BeforeEach
    void setUp() {
        List<Product> productList = new ArrayList<>(Arrays.asList(productWithId ));
        Repository<Product> productRepository = new ProductInMemoryRepositoryImpl(productList);

        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void create() {
        Product product = productWithoutId;
        Product expected = secondProduct;

        assertEquals(expected , productService.create(product));
    }

    @Test
    void edit() {
        Product edit = editProductWithoutId;
        Product expected = editProductWithId;

        assertEquals(expected , productService.edit(1 , edit));
    }

    @Test
    void getById() {
        Product product = productWithId;

        assertEquals(product , productService.getById(1));
    }

    @Test
    void list() {
        List<Product> expected = new ArrayList<>(Arrays.asList(productWithId));

        assertEquals(expected , productService.list());
    }
}