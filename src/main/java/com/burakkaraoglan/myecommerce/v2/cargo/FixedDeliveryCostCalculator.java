package com.burakkaraoglan.myecommerce.v2.cargo;

import com.burakkaraoglan.myecommerce.v2.ShoppingCart;

import java.text.DecimalFormat;

public class FixedDeliveryCostCalculator implements DeliveryCostCalculator {
    private static final double FIXED_COST = 2.99;

    private double costPerDelivery;
    private double costPerProduct;

    public FixedDeliveryCostCalculator(final double costPerDelivery, final double costPerProduct) {
        this.costPerDelivery = costPerDelivery;
        this.costPerProduct = costPerProduct;
    }

    @Override
    public double calculateFor(final ShoppingCart cart) {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        double result =
            cart.getNumberOfDeliveries() * costPerDelivery + cart.getNumberOfProducts() * costPerProduct + FIXED_COST;
        return Double.valueOf(decimalFormat.format(result));
    }
}
