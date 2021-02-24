package repository;

import model.Product;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductInMemoryRepositoryImplTest {

    private Repository<Product> productRepository;


    @BeforeEach
    void setUp() {
        productRepository = new ProductInMemoryRepositoryImpl(
                new ArrayList<>(Arrays.asList(new Product(1L, "Sunlight", 10.00, 50)))
        );
    }

    @Test
    void create() {
        Product product = new Product(null, "Sunlight", 10.00, 50);
        Product expected = new Product(2L, "Sunlight", 10.00, 50);

        assertEquals(expected, productRepository.create(product));
    }

    @Test
    void edit() {
        Product edit = new Product(null, "Sunlight", 20.00, 50);
        Product expected = new Product(1L, "Sunlight", 20.00, 50);

        assertEquals(expected, productRepository.edit(1, edit));
    }

    @Test
    void editNegative() {
        Product edit = new Product(null, "Sunlight", 20.00, 50);
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                productRepository.edit(0, edit));

        assertEquals("id doesn't exist " + 0L, exception.getMessage());
    }

    @Test
    void getById() {
        Product expected = new Product(1L, "Sunlight", 10.00, 50);

        assertEquals(expected, productRepository.getById(1));
    }

    @Test
    void list() {
        List<Product> expected = Arrays.asList(new Product(1L, "Sunlight", 10.00, 50));

        assertEquals(expected, productRepository.list());
    }
}