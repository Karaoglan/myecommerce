package com.burakkaraoglan.myecommerce.v2;


import com.burakkaraoglan.myecommerce.v2.cargo.FixedDeliveryCostCalculator;
import com.burakkaraoglan.myecommerce.v2.domain.Category;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Optional;

@SpringBootApplication
@Slf4j
public class MainApplication {
    public static void main(String[] args) {
        SpringApplication.run(MainApplication.class, args);


        ShoppingCart shoppingCart =
            new ShoppingCart(new FixedDeliveryCostCalculator(3.5, 1.5), null, null);

        Category parentShoes = new Category("Shoes");
        Category subSneakers = new Category("Sneakers", parentShoes);
        Category subFootball = new Category("Football", parentShoes);
        Category watches = new Category("Watches");

        /*Discount rateCampaign = new Campaign(2, 10, DiscountType.Rate);
        Discount amountCampaign = new Campaign(3, 150, DiscountType.Amount);
        subFootball.addCampaign(rateCampaign);
        subSneakers.addCampaign(amountCampaign);

        Product newBalance = new Product("New Balance 123", 350.0, subSneakers);
        Product adidasPredator = new Product("Adidas Predator", 400.0, subFootball);
        Product orientAC09005W = new Product("Orient AC00005W", 1500.0, watches);
        shoppingCart.addProduct(3, newBalance);
        shoppingCart.addProduct(2, adidasPredator);
        shoppingCart.addProduct(5, orientAC09005W);

        Discount rateCoupon = new Coupon(300.0, 10, DiscountType.Rate);
        Discount amountCoupon = new Coupon(1000, 100, DiscountType.Amount);

        shoppingCart.addCoupon(rateCoupon);
        shoppingCart.addCoupon(amountCoupon);
        log.info("CART -> {}", shoppingCart);*/
    }
}
