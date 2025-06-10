package com.nguyenhangan.k22411csampleproject;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nguyenhangan.k22411csampleproject.connectors.OrderDetailsConnector;
import com.nguyenhangan.k22411csampleproject.connectors.SQLiteConnector;
import com.nguyenhangan.k22411csampleproject.models.OrderDetailsViewer;

import java.util.ArrayList;

import adapters.OrderDetailsAdapter;

public class OrderDetailsActivity extends AppCompatActivity {
    ListView lvOrderDetail;
    OrderDetailsAdapter adapter;
    TextView txtOrderId;
    TextView txtOrderDetailDate;
    TextView txtDetailEmployeeName;
    TextView txtDetailCustomerName;
    TextView txtDiscount;
    TextView txtVAT;
    TextView txtTotalValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_order_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        int orderId = getIntent().getIntExtra("ORDER_ID", -1);
        if (orderId != -1) {
            addViews(orderId); // Truyền orderId vào addViews
        } else {
            // Xử lý trường hợp không có orderId (có thể finish() activity)
            finish();
    }}

    private void addViews(int orderId) {
        // Ánh xạ các view
        lvOrderDetail = findViewById(R.id.lvOrderDetail);
        txtOrderId = findViewById(R.id.txtOrderId);
        txtOrderDetailDate = findViewById(R.id.txtOrderDetailDate);
        txtDetailEmployeeName = findViewById(R.id.txtDetailEmployeeName);
        txtDetailCustomerName = findViewById(R.id.txtDetailCustomerName);
        txtDiscount = findViewById(R.id.txtDiscount);
        txtVAT = findViewById(R.id.txtVAT);
        txtTotalValue = findViewById(R.id.txtTotalValue);
        SQLiteConnector connectorDB = new SQLiteConnector(this);
        OrderDetailsConnector odc = new OrderDetailsConnector();
        ArrayList<OrderDetailsViewer> detailList = odc.getAllOrderDetails(connectorDB.openDatabase(), orderId);

        // Thiết lập adapter cho ListView
        adapter = new OrderDetailsAdapter(this, R.layout.item_orderdetail);
        lvOrderDetail.setAdapter(adapter);

        // Hiển thị dữ liệu nếu có
        if (!detailList.isEmpty()) {
            // Lấy thông tin chung đơn hàng từ phần tử đầu tiên
            OrderDetailsViewer orderInfo = detailList.get(0);
            txtOrderId.setText(orderInfo.getOrderCode());
            txtOrderDetailDate.setText(orderInfo.getOrderDate());
            txtDetailEmployeeName.setText(orderInfo.getEmployeeName());
            txtDetailCustomerName.setText(orderInfo.getCustomerName());

            // Tính tổng Discount, VAT, TotalValue
            double totalDiscount = 0;
            double totalVAT = 0;
            double totalValue = 0;

            for (OrderDetailsViewer odv : detailList) {
                totalDiscount += odv.getDiscount();
                totalVAT += odv.getVAT();
                totalValue += odv.getTotalValue();
            }

            txtDiscount.setText(String.valueOf(totalDiscount));
            txtVAT.setText(String.valueOf(totalVAT));
            txtTotalValue.setText(String.valueOf(totalValue) + " VNĐ");

            // Đưa danh sách sản phẩm vào adapter
            adapter.clear();
            adapter.addAll(detailList);
        }
    }
    }
