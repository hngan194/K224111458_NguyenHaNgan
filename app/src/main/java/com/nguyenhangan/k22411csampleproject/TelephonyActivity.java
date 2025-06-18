package com.nguyenhangan.k22411csampleproject;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.nguyenhangan.k22411csampleproject.models.TelephonyInfor;
import com.nguyenhangan.k22411csampleproject.utils.CarrierUtils;

import java.util.ArrayList;
import java.util.List;

import adapters.TelephonyInforAdapter;

public class TelephonyActivity extends AppCompatActivity {
    ListView lvtelephonyinfor;
    //ArrayAdapter<TelephonyInfor> adapter;
    TelephonyInforAdapter adapter;
    private List<TelephonyInfor> originalList = new ArrayList<>();
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
        addEvents();
    }

    private void addEvents() {
        lvtelephonyinfor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> AdapterView, View view, int i, long l) {
                TelephonyInfor ti=adapter.getItem(i);
                makeAPhoneCall(ti);
            }
        });
    }

    private void makeAPhoneCall(TelephonyInfor ti) {
        Uri uri=Uri.parse("tel:"+ti.getPhone());
        Intent intent=new Intent(Intent.ACTION_CALL);
        intent.setData(uri);
        startActivity(intent);
    }

    private void getAllContacts() {
        originalList.clear();
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
            originalList.add(ti);
        }
        cursor.close();
        adapter.updateList(originalList); // [Thêm vào]

    }

    private void addViews() {
        lvtelephonyinfor=findViewById(R.id.lvtelephonyinfor);
//        adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        adapter=new TelephonyInforAdapter(this, R.layout.item_telephony_infor, new ArrayList<>());
        lvtelephonyinfor.setAdapter(adapter);
    }
    public void directCall(TelephonyInfor ti)
    {
        Uri uri=Uri.parse("tel:"+ti.getPhone());
        Intent intent=new Intent(Intent.ACTION_CALL);
        intent.setData(uri);
        startActivity(intent);
    }
    public void dialUp(TelephonyInfor ti)
    {
        Uri uri=Uri.parse("tel:"+ti.getPhone());
        Intent intent=new Intent(Intent.ACTION_DIAL);
        intent.setData(uri);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.filter_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.filter_viettel) {
            filterContacts("VIETTEL");
            return true;
        } else if (id == R.id.filter_mobifone) {
            filterContacts("MOBIFONE");
            return true;
        } else if (id == R.id.filter_other) {
            filterContacts("VINAPHONE");
            return true;
        } else if (id == R.id.filter_all) {
            showAllContacts();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void filterContacts(String targetCarrier) {
        List<TelephonyInfor> filteredList = new ArrayList<>();

        for (TelephonyInfor contact : originalList) {
            String detectedCarrier = CarrierUtils.detectCarrier(contact.getPhone());
            if (detectedCarrier.equalsIgnoreCase(targetCarrier)) {
                filteredList.add(contact);
            }
        }

        adapter.updateList(filteredList);
    }

    private void showAllContacts() {
        adapter.updateList(originalList);
    }
}