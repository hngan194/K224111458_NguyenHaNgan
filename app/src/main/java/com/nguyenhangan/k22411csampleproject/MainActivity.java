package com.nguyenhangan.k22411csampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    ImageView imgEmployee;
    TextView txtEmployee;
    ImageView imgCustomer;
    TextView txtCustomer;
    ImageView imgAdvancedProduct;
    TextView txtAdvancedProduct;
    TextView txtProduct;
    ImageView imgProduct;
    ImageView imgPaymentMethod;
    ImageView imgOrder;
    TextView txtPaymentMethod;
    TextView txtOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
//        addEvents();
        addView();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void addView() {
        imgEmployee=findViewById(R.id.imgEmployee);
        txtEmployee=findViewById(R.id.txtEmployee);
        imgCustomer=findViewById(R.id.imgCustomer);
        txtCustomer=findViewById(R.id.txtCustomer);
        imgProduct=findViewById(R.id.imgProduct);
        txtProduct=findViewById(R.id.txtProduct);
        imgPaymentMethod=findViewById(R.id.imgPaymentMethod);
        txtPaymentMethod=findViewById(R.id.txtPaymentMethod);
        imgOrder=findViewById(R.id.imgOrder);
        txtOrder=findViewById(R.id.txtOrder);
    }

    public void open_employee_management(View view) {
        Intent intent= new Intent(MainActivity.this, EmployeeManagementActivity.class);;
        startActivity(intent);
    }
    public void open_payment_method(View view) {
        Intent intent= new Intent(MainActivity.this, PaymentMethodActivity.class);;
        startActivity(intent);
    }
    public void open_customer_management(View view) {
        Intent intent= new Intent(MainActivity.this, CustomerManagementActivity.class);;
        startActivity(intent);
    }
//    public void open_category_management(View view) {
//        Intent intent= new Intent(MainActivity.this, CategoryManagementActivity.class);;
//        startActivity(intent);
//    }
    public void open_product_management(View view) {
        Intent intent= new Intent(MainActivity.this, ProductManagementActivity.class);;
        startActivity(intent);
    }

    public void open_advanced_product_management(View view) {
        Intent intent= new Intent(MainActivity.this, AdvancedProductManagementActivity.class);
        startActivity(intent);
    }

    public void open_order_viewer(View view) {
        Intent intent= new Intent(MainActivity.this, OrderViewActivity.class);
        startActivity(intent);
    }

//    private void addEvents(){
//        imgEmployee.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //gọi code mở màn hình quản trị nhân sự:
//                openEmployeeManagementActivity();
//            }
//        });
//        txtEmployee.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //gọi code mở màn hình quản trị nhân sự
//                openEmployeeManagementActivity();
//            }
//        });
//    }
//    void openEmployeeManagementActivity(){
//        Intent intent=new Intent(MainActivity.this,EmployeeManagementActivity.class);
//        startActivity(intent);
//    }
}