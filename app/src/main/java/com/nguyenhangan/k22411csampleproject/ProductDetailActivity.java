package com.nguyenhangan.k22411csampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nguyenhangan.k22411csampleproject.models.Product;

public class ProductDetailActivity extends AppCompatActivity {
    EditText edtProductID;
    EditText edtProductName;
    EditText edtProductQuan;
    EditText edtProductPrice;
    EditText edtCategoryID;
    EditText edtProductDes;
    EditText edtProductImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_product_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
    }

    private void addViews() {
        edtProductID=findViewById(R.id.edtProductID);
        edtProductName=findViewById(R.id.edtProductName);
        edtProductQuan=findViewById(R.id.edtProductQuan);
        edtProductPrice=findViewById(R.id.edtProductPrice);
        edtCategoryID=findViewById(R.id.edtCategoryID);
        edtProductDes=findViewById(R.id.edtProductDes);
        edtProductImg=findViewById(R.id.edtProductImg);
        display_product_infor();
    }

    private void display_product_infor() {
        Intent intent=getIntent();
        Product p=(Product) intent.getSerializableExtra("SELECTED_PRODUCT");
        if (p==null)
            return;
        edtProductID.setText(String.valueOf(p.getId()));
        edtProductName.setText(p.getName());
        edtProductQuan.setText(String.valueOf(p.getQuantity()));
        edtProductPrice.setText(String.valueOf(p.getPrice()));
        edtCategoryID.setText(String.valueOf(p.getCate_id()));
        edtProductDes.setText(p.getDescription());
        edtProductImg.setText(String.valueOf(p.getImage_id()));
    }
}