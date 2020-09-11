package myecommerce.v2.repository.service;

import com.burakkaraoglan.myecommerce.main.BaseTest;
import com.burakkaraoglan.myecommerce.v2.domain.Category;
import com.burakkaraoglan.myecommerce.v2.domain.Product;
import com.burakkaraoglan.myecommerce.v2.printer.CartPrinter;
import com.burakkaraoglan.myecommerce.v2.printer.ShoppingCartPrinter;
import com.burakkaraoglan.myecommerce.v2.repository.CartProductRepository;
import com.burakkaraoglan.myecommerce.v2.service.ShoppingCart;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

public class CartPrinterTest extends BaseTest {
    private ShoppingCartPrinter cartPrinter;

    @Mock
    private CartProductRepository repository;

    @Before
    public void setUp() {
        cartPrinter = new CartPrinter();
    }

    @Test
    public void givenCartPrinter_whenPrintCalledWithCartWith_shouldReturnPrintString() {
        ShoppingCart cart = new ShoppingCart(null, repository, cartPrinter);
        List<Product> products = new ArrayList<>();
        Category shoes = new Category("shoes");
        Product adidas = new Product("adidas", 100.0, shoes);
        Product nike = new Product("nike", 99.0, shoes);
        Product puma = new Product("puma", 89.0, new Category("shirt"));
        products.add(adidas);
        products.add(nike);
        products.add(puma);
        Mockito.when(repository.getProducts()).thenReturn(products);
        Mockito.when(repository.productToString(adidas)).thenReturn("Product{name='adidas', unitPrice=100.0, " +
            "quantity=1, totalPrice=100.0}");
        Mockito.when(repository.productToString(nike)).thenReturn("Product{name='nike', unitPrice=99.0, " +
            "quantity=1, totalPrice=99.0}");
        Mockito.when(repository.productToString(puma)).thenReturn("Product{name='puma', unitPrice=89.0, " +
            "quantity=1, totalPrice=89.0}");
        cartPrinter.print(cart);
    }
}
