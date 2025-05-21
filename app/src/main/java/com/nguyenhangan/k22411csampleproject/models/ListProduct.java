package com.nguyenhangan.k22411csampleproject.models;

import java.io.Serializable;
import java.util.ArrayList;

public class ListProduct implements Serializable {
    private ArrayList<Product>products;
    public ListProduct() {
        products = new ArrayList<>();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void addProduct(Product p) {
        products.add(p);
    }

//    public void generate_sample_dataset(ArrayList<Category> categories) {
//        for (int i = 1; i <= 50; i++) {
//            // Random category
//            int randomIndex = (int) (Math.random() * categories.size());
//            Category randomCategory = categories.get(randomIndex);
//
//            // Random quantity: 0 to 100
//            int quantity = (int) (Math.random() * 101);
//
//            // Random price: 5.0 to 100.0
//            double price = 5.0 + (Math.random() * (100.0 - 5.0));
//
//            Product p = new Product(
//                    i,                              // id
//                    "Product " + i,                 // name
//                    quantity,                       // quantity
//                    Math.round(price * 100.0) / 100.0, // làm tròn 2 chữ số sau dấu phẩy
//                    randomCategory.getId(),         // cate_id
//                    "Description for product " + i  // description
//            );
//
//            addProduct(p);
//        }
//    }
}
