package com.nguyenhangan.k22411csampleproject.connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nguyenhangan.k22411csampleproject.models.OrderDetails;
import com.nguyenhangan.k22411csampleproject.models.OrderDetailsViewer;

import java.util.ArrayList;

public class OrderDetailsConnector {
    public ArrayList<OrderDetailsViewer> getAllOrderDetails(SQLiteDatabase database, int orderId) {
        ArrayList<OrderDetailsViewer> datasets = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT ");
        builder.append("o.Id AS OrderId, ");
        builder.append("o.Code AS OrderCode, ");
        builder.append("o.OrderDate, ");
        builder.append("e.Name AS EmployeeName, ");
        builder.append("c.Name AS CustomerName, ");
        builder.append("od.ProductId, ");
        builder.append("p.Name AS ProductName, ");
        builder.append("od.Quantity, ");
        builder.append("od.Price, ");
        builder.append("od.Discount, ");
        builder.append("od.VAT, ");
        builder.append("od.TotalValue ");
        builder.append("FROM 'Order' o ");
        builder.append("JOIN Employee e ON o.EmployeeId = e.Id ");
        builder.append("JOIN Customer c ON o.CustomerId = c.Id ");
        builder.append("JOIN OrderDetail od ON o.Id = od.OrderId ");
        builder.append("JOIN Product p ON od.ProductId = p.Id ");
        builder.append("WHERE o.Id = ? ");
        builder.append("ORDER BY od.ProductId;");
        Cursor cursor = database.rawQuery(builder.toString(), new String[]{String.valueOf(orderId)});
        while (cursor.moveToNext()) {
            String orderCode = cursor.getString(1);
            String orderDate = cursor.getString(2);
            String employeeName = cursor.getString(3);
            String customerName = cursor.getString(4);
            int productId = cursor.getInt(5);
            String productName = cursor.getString(6);
            int quantity = cursor.getInt(7);
            double price = cursor.getDouble(8);
            double discount = cursor.getDouble(9);
            double vat = cursor.getDouble(10);
            double totalValue = cursor.getDouble(11);

            OrderDetailsViewer odv = new OrderDetailsViewer();
            odv.setOrderId(orderId);
            odv.setOrderCode(orderCode);
            odv.setOrderDate(orderDate);
            odv.setEmployeeName(employeeName);
            odv.setCustomerName(customerName);
            odv.setProductId(productId);
            odv.setProductName(productName);
            odv.setQuantity(quantity);
            odv.setPrice(price);
            odv.setDiscount(discount);
            odv.setVAT(vat);
            odv.setTotalValue(totalValue);

            datasets.add(odv);
        }
        cursor.close();
        return datasets;
    }
}
