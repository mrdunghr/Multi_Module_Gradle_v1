package com.example;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private static final List<Product> products = new ArrayList<>();

    // To initialize all the products.
    // khởi tạo một danh sách sản phẩm
    static {
        for (long i = 1; i <= 5; i++) {
            Product p = new Product(i, "Product-" + i, 1000d * i);
            products.add(p);
        }
    }


    public static List<Product> getProducts() {
        return products;
    }

    public static boolean createProduct(Product product) {
        try {
            product.setId(products.size() + 1);
            products.add(product);
        } catch(Exception e) {
            return false;
        }
        return true;
    }
}