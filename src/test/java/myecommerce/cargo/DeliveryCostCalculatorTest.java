package myecommerce.cargo;

import com.burakkaraoglan.myecommerce.main.BaseTest;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class DeliveryCostCalculatorTest extends BaseTest {

    private DeliveryCostCalculator deliveryCostCalculator;

    @Test
    public void givenCalculatorWhenCalculateForCalledThenItShouldReturnDeliveryCost() {
        // given
        deliveryCostCalculator = new FixedCargoDeliveryCostCalculator(1.0, 1.5);

        // when
        int numberOfDeliveries = 2;
        int numberOfProducts = 3;
        double deliveryCost = deliveryCostCalculator.calculateFor(numberOfDeliveries, numberOfProducts);

        // then
        assertThat(deliveryCost).isEqualTo(9.49);
    }

    @Test
    public void givenEmptyShoppingCartWhenGetDeliveryCostCalledThenItShouldReturnFixedCost() {
        // given
        deliveryCostCalculator = new FixedCargoDeliveryCostCalculator(2.0, 3.0);

        // When
        double deliveryCost = deliveryCostCalculator.calculateFor(0, 0);

        // Then
        assertThat(deliveryCost).isEqualTo(2.99);
    }
}
