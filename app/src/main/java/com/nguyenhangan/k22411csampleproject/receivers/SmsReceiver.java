package com.nguyenhangan.k22411csampleproject.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SmsReceiver extends BroadcastReceiver
{
    private DatabaseReference mDatabase;

    @Override
    public void onReceive(Context context, Intent intent) {
        // Khởi tạo Firebase Database
        mDatabase = FirebaseDatabase.getInstance().getReference();

        Bundle bundle = intent.getExtras();
        Object[] arrMessages = (Object[]) bundle.get("pdus");
        String phone, time, content;
        Date date;
        byte[] bytes;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        for(int i = 0; i < arrMessages.length; i++) {
            bytes = (byte[]) arrMessages[i];
            SmsMessage message = SmsMessage.createFromPdu(bytes);
            phone = message.getDisplayOriginatingAddress();
            date = new Date(message.getTimestampMillis());
            content = message.getMessageBody();
            time = sdf.format(date);

            // Lưu vào Firebase
            saveSmsToFirebase(phone, time, content, context);

            String infor = phone + "\n" + time + "\n" + content;
            Toast.makeText(context, infor, Toast.LENGTH_LONG).show();
        }
    }

    private void saveSmsToFirebase(String phone, String time, String content, Context context) {
        // Tạo key unique cho mỗi SMS
        String smsId = mDatabase.child("sms").push().getKey();

        // Tạo object SMS
        Map<String, Object> smsData = new HashMap<>();
        smsData.put("phone", phone);
        smsData.put("time", time);
        smsData.put("content", content);
        smsData.put("timestamp", System.currentTimeMillis());
        smsData.put("device_id", android.provider.Settings.Secure.getString(
                context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID));

        // Lưu vào Firebase
        mDatabase.child("sms").child(smsId).setValue(smsData)
                .addOnSuccessListener(aVoid -> {
                    Toast.makeText(context, "SMS saved to Firebase", Toast.LENGTH_SHORT).show();
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(context, "Failed to save SMS: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                });
    }

}

