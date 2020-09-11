package com.burakkaraoglan.myecommerce.v2;

import com.burakkaraoglan.myecommerce.v2.domain.Product;

import java.util.List;

public interface CartProductRepository {
    List<Product> getProducts();
    void add(Product product, int quantity);
    int getNumberOfDeliveries();
    int getNumberOfProducts();
    String productToString(final Product product);
}
