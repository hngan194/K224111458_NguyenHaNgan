package com.nguyenhangan.k22411csampleproject.models;

import com.nguyenhangan.k22411csampleproject.R;

import java.io.Serializable;
import java.util.ArrayList;

public class ListProduct implements Serializable {
    private ArrayList<Product>products;
    public ListProduct()
    {
        products = new ArrayList<>();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
    public void generate_sample_dataset() {
        Product p1 = new Product(1, "Điện thoại thông minh", 45, 899.99, "Thiết kế mới nhất năm 2025, tích hợp AI tối tân", R.mipmap.iphone);
        products.add(p1);
        Product p2 = new Product(2, "Laptop gaming", 30, 1299.99, "Cấu hình mạnh mẽ, tản nhiệt hiệu quả", R.mipmap.laptop);
        products.add(p2);
        Product p3 = new Product(3, "Máy ảnh DSLR", 25, 799.99, "Chụp ảnh sắc nét với độ phân giải cao", R.mipmap.canon);
        products.add(p3);
        Product p4 = new Product(5, "Smart TV 4K", 20, 599.99, "Hình ảnh sống động, kết nối thông minh", R.mipmap.tivi);
        products.add(p4);
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
