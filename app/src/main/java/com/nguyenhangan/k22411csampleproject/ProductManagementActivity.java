package com.nguyenhangan.k22411csampleproject;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nguyenhangan.k22411csampleproject.connectors.CategoryConnector;
import com.nguyenhangan.k22411csampleproject.connectors.ProductConnector;
import com.nguyenhangan.k22411csampleproject.models.Category;
import com.nguyenhangan.k22411csampleproject.models.Product;

import java.util.ArrayList;

public class ProductManagementActivity extends AppCompatActivity {
    ListView lvProduct;
    ArrayAdapter<Product>adapter;
    ProductConnector connector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_management);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
    }

    private void addViews() {
        lvProduct=findViewById(R.id.lvProduct);
        adapter=new ArrayAdapter<>(
                ProductManagementActivity.this,
                android.R.layout.simple_list_item_1
        );
        CategoryConnector categoryConnector = new CategoryConnector();
        ArrayList<Category> categories = categoryConnector.get_all_categories();
        connector = new ProductConnector(categories);
        adapter.addAll(connector.get_all_products());
        lvProduct.setAdapter(adapter);
    }
}