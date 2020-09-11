package com.burakkaraoglan.myecommerce.v2.domain;

public class Category {
    private String title;
    private Category parentCategory;

    public Category(final String title) {
        this.title = title;
    }

    public Category(final String title, final Category parentCategory) {
        this(title);
        this.parentCategory = parentCategory;
    }

    public String getTitle() {
        return title;
    }

    public Category getParentCategory() {
        return parentCategory;
    }

    @Override
    public String toString() {
        return "Category{" +
            "title='" + title + '\'' +
            '}';
    }
}
