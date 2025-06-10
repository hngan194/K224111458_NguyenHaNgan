package com.nguyenhangan.k22411csampleproject.connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nguyenhangan.k22411csampleproject.models.Customer;
import com.nguyenhangan.k22411csampleproject.models.ListCustomer;
import com.nguyenhangan.k22411csampleproject.models.OrdersViewer;

import java.util.ArrayList;

public class OrdersViewerConnector {
    public ArrayList<OrdersViewer> getAllOrdersViewers(SQLiteDatabase database) {
        ArrayList<OrdersViewer> datasets = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        builder.append("SELECT ");
        builder.append("o.Id AS OrderId, ");
        builder.append("o.Code AS OrderCode, ");
        builder.append("o.OrderDate, ");
        builder.append("e.Name AS EmployeeName, ");
        builder.append("c.Name AS CustomerName, ");
        builder.append("SUM((od.Quantity * od.Price - od.Discount / 100.0 * od.Quantity * od.Price) * (1 + od.VAT / 100.0)) AS TotalOrderValue ");
        builder.append("FROM 'Order' o ");
        builder.append("JOIN Employee e ON o.EmployeeId = e.Id ");
        builder.append("JOIN Customer c ON o.CustomerId = c.Id ");
        builder.append("JOIN OrderDetail od ON o.Id = od.OrderId ");
        builder.append("GROUP BY o.Id, o.Code, o.OrderDate, e.Name, c.Name;");
        String sql = builder.toString();
        Cursor cursor = database.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String code = cursor.getString(1);
            String orderdate = cursor.getString(2);
            String employeename = cursor.getString(3);
            String customername = cursor.getString(4);
            double totalvalue = cursor.getDouble(5);
            OrdersViewer ov = new OrdersViewer();
            ov.setId(id);
            ov.setCode(code);
            ov.setOrderDate(orderdate);
            ov.setEmployeeName(employeename);
            ov.setCustomerName(customername);
            ov.setTotalOrderValue(totalvalue);
            datasets.add(ov);
        }
        cursor.close();
        return datasets;
    }
}
