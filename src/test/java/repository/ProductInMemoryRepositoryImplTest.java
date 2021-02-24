package repository;

import model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductInMemoryRepositoryImplTest {

    private Repository<Product> productRepository;
    private Product productWithoutId = new Product(null, "Sunlight", 10.00, 50);
    private Product productWithId = new Product(1L, "Sunlight", 10.00, 50);
    private Product secondProduct = new Product(2L, "Sunlight", 10.00, 50);
    private Product editWithoutId = new Product(null, "Sunlight", 20.00, 50);
    private Product editWithId = new Product(1L, "Sunlight", 20.00, 50);


    @BeforeEach
    void setUp() {
        productRepository = new ProductInMemoryRepositoryImpl(
                new ArrayList<>(Arrays.asList(productWithId))
        );
    }

    @Test
    void create() {
        Product product = productWithoutId;
        Product expected = secondProduct;

        assertEquals(expected, productRepository.create(product));
    }

    @Test
    void edit() {
        Product edit = editWithoutId;
        Product expected = editWithId;

        assertEquals(expected, productRepository.edit(1, edit));
    }

    @Test
    void editNegative() {
        Product edit = editWithoutId;
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                productRepository.edit(0, edit));

        assertEquals("id doesn't exist " + 0L, exception.getMessage());
    }

    @Test
    void getById() {
        Product expected = productWithId;

        assertEquals(expected, productRepository.getById(1));
    }

    @Test
    void list() {
        List<Product> expected = Arrays.asList(productWithId);

        assertEquals(expected, productRepository.list());
    }
}