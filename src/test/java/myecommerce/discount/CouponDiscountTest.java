package myecommerce.discount;

import com.burakkaraoglan.myecommerce.main.BaseTest;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CouponDiscountTest extends BaseTest {

    @Test
    public void givenCouponWithInsufficientTotalShouldReturnNotApplicable() {
        // given
        Discount coupon = new Coupon(100, 10.0, DiscountType.Amount);

        // when
        boolean isApplicable = coupon.isApplicable(50);

        // then
        assertThat(isApplicable).isEqualTo(false);
    }

    @Test
    public void givenCouponWithSufficientTotalShouldReturnApplicable() {
        // given
        Discount coupon = new Coupon(100, 20.0, DiscountType.Rate);

        // when
        boolean isApplicable = coupon.isApplicable(120);

        // then
        assertThat(isApplicable).isEqualTo(true);
    }

    @Test
    public void givenCouponWithRateDiscountShouldReturnDiscountAmount() {
        // given
        Discount coupon = new Coupon(45, 20.0, DiscountType.Rate);

        // when
        double discountAmount = coupon.getDiscount(50);

        // then
        assertThat(discountAmount).isEqualTo(10);
    }

    @Test
    public void givenCouponWithAmountDiscountShouldReturnDiscountAmount() {
        // given
        Discount coupon = new Coupon(90, 15.0, DiscountType.Amount);

        // when
        double discountAmount = coupon.getDiscount(100);

        // then
        assertThat(discountAmount).isEqualTo(15);
    }

    @Test
    public void givenAmountCouponWhenToStringCalledShouldReturnAmountCoupon() {
        // given
        // when
        Discount amountCoupon = new Coupon(8.0, 2.5, DiscountType.Amount);

        // then
        assertThat(amountCoupon.toString()).isEqualTo("Amount: minLimit: 8.0, amount: 2.50");
    }

    @Test
    public void givenRateCouponWhenToStringCalledShouldReturnRateCoupon() {
        // given
        // when
        Discount rateCoupon = new Coupon(10.0, 10.0, DiscountType.Rate);

        // then
        assertThat(rateCoupon.toString()).isEqualTo("Rate: minLimit: 10.0, rate: 10.00");
    }
}
