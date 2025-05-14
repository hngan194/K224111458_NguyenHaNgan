package com.nguyenhangan.k22411csampleproject.connectors;

import com.nguyenhangan.k22411csampleproject.models.Category;
import com.nguyenhangan.k22411csampleproject.models.ListCategory;

import java.util.ArrayList;

public class CategoryConnector {
    private ListCategory listCategory;

    public CategoryConnector() {
        listCategory = new ListCategory();
        listCategory.generate_sample_dataset();
    }

    public ArrayList<Category> get_all_categories() {
        if (listCategory == null) {
            listCategory = new ListCategory();
            listCategory.generate_sample_dataset();
        }
        return listCategory.getCategories();
    }

    public Category get_category_by_id(int id) {
        if (listCategory == null) {
            listCategory = new ListCategory();
            listCategory.generate_sample_dataset();
        }
        for (Category c : listCategory.getCategories()) {
            if (c.getId() == id) {
                return c;
            }
        }
        return null; // Không tìm thấy
    }

    public ArrayList<Category> get_categories_by_name_keyword(String keyword) {
        if (listCategory == null) {
            listCategory = new ListCategory();
            listCategory.generate_sample_dataset();
        }
        ArrayList<Category> results = new ArrayList<>();
        for (Category c : listCategory.getCategories()) {
            if (c.getName().toLowerCase().contains(keyword.toLowerCase())) {
                results.add(c);
            }
        }
        return results;
    }
}
