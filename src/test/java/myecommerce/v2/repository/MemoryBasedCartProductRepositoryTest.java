package myecommerce.v2.repository;

import com.burakkaraoglan.myecommerce.v2.CartProductRepository;
import com.burakkaraoglan.myecommerce.v2.domain.Product;
import com.burakkaraoglan.myecommerce.v2.exception.IncorrectQuantityException;
import myecommerce.main.BaseTest;
import org.junit.Before;
import org.junit.Test;

public class MemoryBasedCartProductRepositoryTest extends BaseTest {
    private CartProductRepository productRepository;

    @Before
    public void setUp() {
        productRepository = new MemoryBasedCartProductRepository();
    }

    @Test(expected = IncorrectQuantityException.class)
    public void givenProductRepository_whenAddItemsWitNegativeQuantity_shouldThrowIncorrectQuantity() {
        Product product = new Product("shoes", 100.0, null);
        productRepository.add(product, -1);
    }

    @Test(expected = IncorrectQuantityException.class)
    public void givenProductRepository_whenAddItemsWitZeroQuantity_shouldThrowIncorrectQuantity() {
        Product product = new Product("shoes", 100.0, null);
        productRepository.add(product, 0);
    }

    @Test
    public void givenProductRepository_whenAddItemsWitQuantity_shouldAddProduct() {
        Product product = new Product("shoes", 100.0, null);
        productRepository.add(product, 3);
    }
}
