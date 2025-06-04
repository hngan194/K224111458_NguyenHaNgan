package com.nguyenhangan.k22411csampleproject;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nguyenhangan.k22411csampleproject.connectors.PaymentMethodConnector;
import com.nguyenhangan.k22411csampleproject.connectors.SQLiteConnector;
import com.nguyenhangan.k22411csampleproject.models.ListCustomer;
import com.nguyenhangan.k22411csampleproject.models.ListPaymentMethod;

import adapters.PaymentMethodAdapter;

public class PaymentMethodActivity extends AppCompatActivity {
    ListView lvPaymentMethod;
    PaymentMethodAdapter adapter;
    ListPaymentMethod lpm;
    PaymentMethodConnector connector;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_payment_method);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        connector = new PaymentMethodConnector();
        addViews();
    }

    private void addViews() {
        lvPaymentMethod = findViewById(R.id.lvPaymentMethod);
        adapter = new PaymentMethodAdapter(PaymentMethodActivity.this, R.layout.item_paymentmethod);
        lvPaymentMethod.setAdapter(adapter);
        lpm = connector.getAllPaymentMethods(new SQLiteConnector(this).openDatabase());
        adapter.addAll(lpm.getPaymentMethods());
    }
}