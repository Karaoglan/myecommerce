package com.burakkaraoglan.myecommerce.v2;

import com.burakkaraoglan.myecommerce.v2.domain.Category;
import com.burakkaraoglan.myecommerce.v2.domain.Product;
import com.burakkaraoglan.myecommerce.v2.exception.IncorrectProductException;
import com.burakkaraoglan.myecommerce.v2.exception.IncorrectQuantityException;

import java.util.*;
import java.util.stream.Collectors;

public class MemoryBasedCartProductRepository implements CartProductRepository {

    private Map<Product, Integer> cartItems;

    public MemoryBasedCartProductRepository() {
        this.cartItems = new HashMap<>();
    }

    @Override
    public List<Product> getProducts() {
        return cartItems.keySet().stream().collect(Collectors.toList());
    }

    @Override
    public void add(final Product product, final int quantity) {
        if (product == null) throw new IncorrectProductException();
        if (quantity <= 0) throw new IncorrectQuantityException();

        if (cartItems.containsKey(product)) {
            cartItems.put(product, quantity);
            return;
        }
        cartItems.replace(product, cartItems.get(product) + quantity);
    }

    @Override
    public int getNumberOfDeliveries() {
        Set<Category> distinctCategories = new HashSet<>();
        cartItems.keySet().forEach(product -> {
            distinctCategories.add(product.getCategory());
        });
        return distinctCategories.size();
    }

    @Override
    public int getNumberOfProducts() {
        return cartItems.keySet().size();
    }

    // TODO move
    @Override
    public String productToString(final Product product) {
        return product + ", quantity=" + cartItems.get(product) +
            ", totalPrice=" + product.getPrice() * cartItems.get(product) + "}";
    }
}
