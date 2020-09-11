package myecommerce.main;

import com.burakkaraoglan.myecommerce.cargo.FixedCargoDeliveryCostCalculator;
import com.burakkaraoglan.myecommerce.discount.Campaign;
import com.burakkaraoglan.myecommerce.discount.Coupon;
import com.burakkaraoglan.myecommerce.discount.Discount;
import com.burakkaraoglan.myecommerce.discount.DiscountType;
import com.burakkaraoglan.myecommerce.model.Category;
import com.burakkaraoglan.myecommerce.model.Product;
import org.junit.Before;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class ShoppingCartTest extends BaseTest {

    private ShoppingCart shoppingCart;

    @Before
    public void setUp() {
        shoppingCart = new ShoppingCart(new FixedCargoDeliveryCostCalculator(2.0, 3.0));
    }

    @Test
    public void givenProductsForDifferentCategoriesWithoutAnyDiscountsWhenGetTotalAmountAfterDiscountsCalledShouldReturnTotalAmount() {
        // given
        shoppingCart.addProduct(2, getProduct1());
        shoppingCart.addProduct(3, getProduct2());
        shoppingCart.addProduct(5, getProduct3());

        // when
        double totalAmount = shoppingCart.getTotalAmountAfterDiscounts();

        // then
        assertThat(totalAmount).isEqualTo(119.0);
    }

    @Test
    public void givenTwoProductsWithSingleCategoryWhenGetTotalAmountAfterDiscountCalledShouldReturnTwoTimesProductPrice() {
        // given
        shoppingCart.addProduct(2, getProduct());

        // when
        double totalAmount = shoppingCart.getTotalAmountAfterDiscounts();

        // then
        assertThat(totalAmount).isEqualTo(2.0);
    }

    @Test
    public void givenCartWithOneProductWhenAddProductCalledWithSameProductAndQuantity1ShouldReturn2() {
        // given
        shoppingCart.addProduct(1, getProduct());

        // when
        int productQuantity = shoppingCart.addProduct(1, getProduct());

        // then
        assertThat(productQuantity).isEqualTo(2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenEmptyCartWhenAddProductCalledWithInvalidQuantityShouldThrowInvalidArgumentException() {
        shoppingCart.addProduct(0, getProduct());
    }

    @Test(expected = IllegalArgumentException.class)
    public void givenEmptyCartWhenAddProductCalledWithNullProductShouldThrowInvalidArgumentException() {
        shoppingCart.addProduct(1, null);
    }

    @Test
    public void givenCartWithProductAndCouponsWhenGetTotalAmountAfterDiscountsCalledShouldReturnTotalAmount() {
        // given
        shoppingCart.addProduct(10, getProduct());
        Discount rateCoupon = new Coupon(10.0, 10.0, DiscountType.Rate);
        Discount amountCoupon = new Coupon(8.0, 2.5, DiscountType.Amount);
        shoppingCart.addCoupon(rateCoupon);
        shoppingCart.addCoupon(amountCoupon);

        // when
        double totalAmount = shoppingCart.getTotalAmountAfterDiscounts();

        // then
        assertThat(totalAmount).isEqualTo(6.5);
    }

    @Test
    public void givenCartWhenGetDeliveryCostCalledShouldReturnDeliveryCost() {
        // given
        Category category1 = new Category("category1");
        Category category2 = new Category("category2");

        Product product1 = new Product("product1", 5.0, category1);
        Product product2 = new Product("product2", 3.0, category1);
        Product product3 = new Product("product3", 20.0, category2);

        ShoppingCart shoppingCart = new ShoppingCart(new FixedCargoDeliveryCostCalculator(2.0, 3.0));
        shoppingCart.addProduct(2, product1);
        shoppingCart.addProduct(3, product2);
        shoppingCart.addProduct(5, product3);

        // when
        double deliveryCost = shoppingCart.getDeliveryCost();

        // then
        assertThat(deliveryCost).isEqualTo(15.99);
    }

    @Test
    public void givenEmptyCartWhenGetDeliveryCostCalledShouldReturnFixedCost() {
        assertThat(this.shoppingCart.getDeliveryCost()).isEqualTo(2.99);
    }

    @Test
    public void givenShoppingCartWhenToStringCalledThenShouldReturnShoppingCart() {

        Discount amountCampaign = new Campaign(1.0, 2, DiscountType.Amount);
        getCategory().addCampaign(amountCampaign);

        shoppingCart.addProduct(10, getProduct());

        Discount amountCoupon = new Coupon(8.0, 2.5, DiscountType.Amount);
        shoppingCart.addCoupon(amountCoupon);

        assertThat(shoppingCart.toString()).isEqualTo(
                "=== PRODUCTS ===\n" +
                        "- Optional[category] -> subcategory\n" +
                        "  * product: Unit Price: 1.00TL, quantity: 10, totalPrice: 10.00TL\n" +
                        "- category\n" +
                        "  * product: Unit Price: 1.00TL, quantity: 10, totalPrice: 10.00TL\n" +
                        "=== CAMPAIGNS ===\n" +
                        "no campaigns found.\n" +
                        "=== COUPONS ===\n" +
                        "- Amount: minLimit: 8.0, amount: 2.50\n" +
                        "=== SUMMARY ===\n" +
                        "totalCampaignDiscount: 0.0\n" +
                        "totalCouponDiscount: 2.5\n" +
                        "totalPrice: 10.0\n" +
                        "totalAmount: 7.50TL\n" +
                        "deliveryCost: 9.99TL\n");
    }

    @Test
    public void givenEmptyShoppingCartWhenToStringCalledShouldReturnEmptyShoppingCartOutput() {
        assertThat(shoppingCart.toString()).isEqualTo(
                "=== PRODUCTS ===\n" +
                "no products found.\n" +
                "=== CAMPAIGNS ===\n" +
                "no campaigns found.\n" +
                "=== COUPONS ===\n" +
                "no coupons found.\n" +
                "=== SUMMARY ===\n" +
                "totalCampaignDiscount: 0.0\n" +
                "totalCouponDiscount: 0.0\n" +
                "totalPrice: 0.0\n" +
                "totalAmount: 0.00TL\n" +
                "deliveryCost: 2.99TL\n");
    }

    private Category getCategory() {
        return new Category("category");
    }

    private Category getSubcategory() {
        return new Category("subcategory", Optional.of(getCategory()));
    }

    private Product getProduct() {
        return new Product("product", 1.0, getSubcategory());
    }

    private Product getSubProduct() {
        return new Product("subproduct", 2.0, getSubcategory());
    }

    private Category getCategory1() {
        return new Category("category1");
    }

    private Category getSubcategory1() {
        return new Category("subcategory1", Optional.of(getCategory1()));
    }

    private Category getSubcategory2() {
        return new Category("subcategory2", Optional.of(getCategory1()));
    }

    private Category getCategory2() {
        return new Category("category2");
    }

    private Product getProduct1() {
        return new Product("product1", 5.0, getSubcategory1());
    }

    private Product getProduct2() {
        return new Product("product2", 3.0, getSubcategory2());
    }

    private Product getProduct3() {
        return new Product("product3", 20.0, getCategory2());
    }

}
