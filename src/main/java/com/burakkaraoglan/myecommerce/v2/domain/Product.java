package com.burakkaraoglan.myecommerce.v2.domain;


public class Product {
    private String title;
    private double price;
    private Category category;

    public Product(final String title, final double price, final Category category) {
        this.title = title;
        this.price = price;
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Product{" +
            "name='" + title + '\'' +
            ", unitPrice=" + price;
    }
}
