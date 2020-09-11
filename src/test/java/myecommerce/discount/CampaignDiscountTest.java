package myecommerce.discount;

import com.burakkaraoglan.myecommerce.main.BaseTest;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CampaignDiscountTest extends BaseTest {

    @Test
    public void givenCampaignWithInsufficientTotalShouldReturnNotApplicable() {
        // given
        Discount campaign = new Campaign(5, 10.0, DiscountType.Amount);

        // when
        boolean isApplicable = campaign.isApplicable(2);

        // then
        assertThat(isApplicable).isEqualTo(false);
    }

    @Test
    public void givenCampaignWithSufficientTotalShouldReturnApplicable() {
        // given
        Discount campaign = new Campaign(5, 20.0, DiscountType.Rate);

        // when
        boolean isApplicable = campaign.isApplicable(7);

        // then
        assertThat(isApplicable).isEqualTo(true);
    }

    @Test
    public void givenCampaignWithRateDiscountShouldReturnDiscountAmount() {
        // given
        Discount campaign = new Campaign(5, 21.3, DiscountType.Rate);

        // when
        double discountAmount = campaign.getDiscount(100);

        // then
        assertThat(discountAmount).isEqualTo(21.3);
    }

    @Test
    public void givenCampaignWithAmountDiscountShouldReturnDiscountAmount() {
        // given
        Discount campaign = new Campaign(5, 15, DiscountType.Amount);

        // when
        double discountAmount = campaign.getDiscount(100);

        // then
        assertThat(discountAmount).isEqualTo(15);
    }
}
