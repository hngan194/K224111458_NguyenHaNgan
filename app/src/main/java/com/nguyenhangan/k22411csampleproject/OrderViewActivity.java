package com.nguyenhangan.k22411csampleproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nguyenhangan.k22411csampleproject.connectors.OrdersViewerConnector;
import com.nguyenhangan.k22411csampleproject.connectors.SQLiteConnector;
import com.nguyenhangan.k22411csampleproject.models.OrdersViewer;

import java.util.ArrayList;

import adapters.OrdersViewerAdapter;

public class OrderViewActivity extends AppCompatActivity {
    ListView lvOrdersViewer;
    OrdersViewerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_view);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
    }

    private void addViews() {
        lvOrdersViewer=findViewById(R.id.lvOrdersViewer);
        adapter=new OrdersViewerAdapter(this, R.layout.item_ordersviewer);
        lvOrdersViewer.setAdapter(adapter);
        SQLiteConnector connector=new SQLiteConnector(this);
        OrdersViewerConnector ovc=new OrdersViewerConnector();
        ArrayList<OrdersViewer> dataset=ovc.getAllOrdersViewers(connector.openDatabase());
        adapter.addAll(dataset);
        lvOrdersViewer.setOnItemClickListener((parent, view, position, id) -> {
            OrdersViewer selectedOrder = adapter.getItem(position);
            if (selectedOrder != null) {
                Intent intent = new Intent(OrderViewActivity.this, OrderDetailsActivity.class);
                intent.putExtra("ORDER_ID", selectedOrder.getId());
                startActivity(intent);
            }
        });
}}