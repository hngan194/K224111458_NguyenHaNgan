package com.nguyenhangan.k22411csampleproject.models;

import java.io.Serializable;
import java.util.ArrayList;

public class ListCategory implements Serializable {
    private ArrayList<Category>categories;
    public ArrayList<Category>getCategories(){
        return categories;
    }
    public void setCategories(ArrayList<Category> categories) {
        this.categories = categories;
    }
    public ListCategory(){
        categories=new ArrayList<>();
    }
    public void addCategory(Category c) {
        categories.add(c);
    }

    public void generate_sample_dataset() {
        String[] sampleNames = {
                "Electronics", "Books", "Fashion", "Home & Kitchen", "Toys", "Sports", "Health", "Beauty", "Automotive", "Music"
        };

        for (int i = 0; i < sampleNames.length; i++) {
            int id = i + 1;
            String name = sampleNames[i];
            Category c = new Category(id, name);
            addCategory(c);
        }
    }
}
