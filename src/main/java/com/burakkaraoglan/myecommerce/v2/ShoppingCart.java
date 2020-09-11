package com.burakkaraoglan.myecommerce.v2;

import com.burakkaraoglan.myecommerce.v2.cargo.DeliveryCostCalculator;
import com.burakkaraoglan.myecommerce.v2.domain.Product;
import com.burakkaraoglan.myecommerce.v2.printer.ShoppingCartPrinter;

import java.util.List;

public class ShoppingCart {
    private final DeliveryCostCalculator deliveryCostCalculator;
    private final CartProductRepository productRepository;
    private final ShoppingCartPrinter cartPrinter;

    public ShoppingCart(final DeliveryCostCalculator deliveryCostCalculator,
                        final CartProductRepository productRepository,
                        final ShoppingCartPrinter cartPrinter) {
        this.deliveryCostCalculator = deliveryCostCalculator;
        this.productRepository = productRepository;
        this.cartPrinter = cartPrinter;
    }

    public void addItem(final Product product, int quantity) {
        productRepository.add(product, quantity);
    }

    public String productPrint(final Product product) {
        return productRepository.productToString(product);
    }

    public String print() {
        return cartPrinter.print(this);
    }

    public List<Product> getProducts() {
        return productRepository.getProducts();
    }

    public int getNumberOfDeliveries() {
        return productRepository.getNumberOfDeliveries();
    }

    public int getNumberOfProducts() {
        return productRepository.getNumberOfProducts();
    }

    public void applyDiscounts() {

    }

    private double getTotalAmountAfterDiscounts() {
        return 0;
    }

    private double getCouponDiscounts() {
        return 0;
    }

    private double getDeliveryCost() {
        return deliveryCostCalculator.calculateFor(this);

    }
}
