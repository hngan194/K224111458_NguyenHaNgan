package com.nguyenhangan.k22411csampleproject.connectors;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.nguyenhangan.k22411csampleproject.models.ListPaymentMethod;
import com.nguyenhangan.k22411csampleproject.models.PaymentMethod;

public class PaymentMethodConnector {
    public ListPaymentMethod getAllPaymentMethods(SQLiteDatabase database) {
        ListPaymentMethod listPaymentMethod = new ListPaymentMethod();
        Cursor cursor = database.rawQuery("SELECT * FROM PaymentMethod", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            String description = cursor.getString(2);
            PaymentMethod pm = new PaymentMethod();
            pm.setId(id);
            pm.setName(name);
            pm.setDescription(description);
            listPaymentMethod.getPaymentMethods().add(pm);
        }
        cursor.close();
        return listPaymentMethod;
    }

}
