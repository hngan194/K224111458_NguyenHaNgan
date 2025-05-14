package com.nguyenhangan.k22411csampleproject.connectors;

import com.nguyenhangan.k22411csampleproject.models.Product;
import com.nguyenhangan.k22411csampleproject.models.ListProduct;
import com.nguyenhangan.k22411csampleproject.models.Category;

import java.util.ArrayList;

public class ProductConnector {
    private ListProduct listProduct;

    // Constructor nhận vào danh sách Category và tạo dataset mẫu cho sản phẩm
    public ProductConnector(ArrayList<Category> categories) {
        if (listProduct == null) {
            listProduct = new ListProduct(); // Khởi tạo nếu listProduct là null
        }
        listProduct.generate_sample_dataset(categories); // Sử dụng categories để sinh sản phẩm
    }



    // Lấy tất cả sản phẩm
    public ArrayList<Product> get_all_products() {
        if (listProduct == null) {
            listProduct = new ListProduct(); // Khởi tạo nếu listProduct là null
        }
        return listProduct.getProducts();
    }

    // Lấy sản phẩm theo ID
    public Product get_product_by_id(int id) {
        if (listProduct == null) {
            listProduct = new ListProduct(); // Khởi tạo nếu listProduct là null
        }

        for (Product p : listProduct.getProducts()) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null; // Không tìm thấy sản phẩm
    }

    // Lọc sản phẩm theo tên (chứa từ khóa)
    public ArrayList<Product> get_products_by_name_keyword(String keyword) {
        ArrayList<Product> result = new ArrayList<>();
        if (listProduct == null) {
            listProduct = new ListProduct(); // Khởi tạo nếu listProduct là null
        }

        for (Product p : listProduct.getProducts()) {
            if (p.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(p);
            }
        }
        return result;
    }

    // Lọc sản phẩm theo ID danh mục (category_id)
    public ArrayList<Product> get_products_by_category_id(int cate_id) {
        ArrayList<Product> result = new ArrayList<>();
        if (listProduct == null) {
            listProduct = new ListProduct(); // Khởi tạo nếu listProduct là null
        }

        for (Product p : listProduct.getProducts()) {
            if (p.getCate_id() == cate_id) {
                result.add(p);
            }
        }
        return result;
    }
}
