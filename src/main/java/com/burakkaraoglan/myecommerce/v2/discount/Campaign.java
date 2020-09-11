package com.burakkaraoglan.myecommerce.v2.discount;

import com.burakkaraoglan.myecommerce.v2.domain.Category;

public class Campaign {
    private Category category;
    private double amount;
    private double minimumLimitConstraint;
    private DiscountType discountType;

    public Campaign(final Category category,
                    final double amount,
                    final double minimumLimitConstraint,
                    final DiscountType discountType) {
        this.category = category;
        this.amount = amount;
        this.minimumLimitConstraint = minimumLimitConstraint;
        this.discountType = discountType;
    }
}
