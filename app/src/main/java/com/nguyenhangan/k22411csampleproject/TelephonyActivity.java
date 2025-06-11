package com.nguyenhangan.k22411csampleproject;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nguyenhangan.k22411csampleproject.models.TelephonyInfor;

public class TelephonyActivity extends AppCompatActivity {
    ListView lvtelephonyinfor;
    ArrayAdapter<TelephonyInfor> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_telephony);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        addViews();
        getAllContacts();
    }

    private void getAllContacts() {
        Uri uri =ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri, null, null, null,  null);
        adapter.clear();
        while (cursor.moveToNext()){
            int nameIndex =cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
            String name = cursor.getString(nameIndex); //Get Name
            int phoneIndex =cursor.getColumnIndex(ContactsContract.CommonDataKinds. Phone.NUMBER);
            String phone = cursor.getString(phoneIndex); //Get Phone Number
            //Todo something …
            TelephonyInfor ti=new TelephonyInfor();
            ti.setName(name);
            ti.setPhone(phone);
            adapter.addAll(ti);
        }
        cursor.close();


    }

    private void addViews() {
        lvtelephonyinfor=findViewById(R.id.lvtelephonyinfor);
        adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        lvtelephonyinfor.setAdapter(adapter);
    }
}