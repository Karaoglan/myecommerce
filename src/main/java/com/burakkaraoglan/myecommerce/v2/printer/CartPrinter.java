package com.burakkaraoglan.myecommerce.v2.printer;


import com.burakkaraoglan.myecommerce.v2.ShoppingCart;
import com.burakkaraoglan.myecommerce.v2.domain.Category;
import com.burakkaraoglan.myecommerce.v2.domain.Product;

import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import static java.util.stream.Collectors.groupingBy;

public class CartPrinter implements ShoppingCartPrinter {
    @Override
    public String print(final ShoppingCart shoppingCart) {
        Map<Category, List<Product>> map = shoppingCart.getProducts().stream()
            .collect(groupingBy(Product::getCategory));

        final StringBuilder print = new StringBuilder();

        map.keySet().forEach(category -> {
            StringJoiner productStringJoiner = new StringJoiner(",\n\t");
            print.append(category)
                .append(" => {\n\t");

            map.get(category)
                .stream()
                .forEach(product -> {
                    productStringJoiner.add(shoppingCart.productPrint(product));
                });
            print.append(productStringJoiner)
                .append("\n}\n");

        });
        return print.toString();
    }
}
