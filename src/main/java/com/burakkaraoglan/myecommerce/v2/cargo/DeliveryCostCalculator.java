package com.burakkaraoglan.myecommerce.v2.cargo;


import com.burakkaraoglan.myecommerce.v2.ShoppingCart;

public interface DeliveryCostCalculator {
    double calculateFor(ShoppingCart cart);
}
