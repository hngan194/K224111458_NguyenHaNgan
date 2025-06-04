package com.nguyenhangan.k22411csampleproject;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nguyenhangan.k22411csampleproject.models.ListProduct;

import adapters.ProductAdapter;

public class AdvancedProductManagementActivity extends AppCompatActivity {
    ListView lvAdvancedProduct;
    ProductAdapter adapter;
    ListProduct listProductt ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_advanced_product_management);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
    }

    private void addViews() {
        lvAdvancedProduct=findViewById(R.id.lvAdvancedProduct);
        adapter=new ProductAdapter(AdvancedProductManagementActivity.this,
                R.layout.item_advanced_product);
        lvAdvancedProduct.setAdapter(adapter);
        listProductt=new ListProduct();
        listProductt.generate_sample_dataset();
        adapter.addAll(listProductt.getProducts());
    }
}