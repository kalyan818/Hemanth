package com.example.kalya.hemanth;

import android.app.Service;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.text.format.Formatter;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

public class MyService extends Service {
    private FirebaseAuth mAuth;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReferenceFromUrl("https://hemanth-5993c.firebaseio.com/2/sir");
    Handler  handler;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {

        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this,"started",Toast.LENGTH_LONG).show();
        handler = new Handler();
        mAuth = FirebaseAuth.getInstance();
        String name = mAuth.getCurrentUser().getEmail();

        String namevalue = "";
        for (int i = 0;i<name.length();i++)
        {
            if (Pattern.matches(String.valueOf(name.charAt(i)),"[a-zA-Z]"))
            {
                namevalue = namevalue + name.charAt(i);
            }
        }


        Toast.makeText(this,name,Toast.LENGTH_LONG).show();
        final String finalNamevalue = namevalue;
        final Runnable r = new Runnable() {
            public void run() {
                WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
                String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
                String currentDateandTime = sdf.format(new Date());
                Toast.makeText(MyService.this,ip.toString(),Toast.LENGTH_LONG).show();
                Toast.makeText(MyService.this,"somethig is running",Toast.LENGTH_LONG).show();
                String value = ip + currentDateandTime;
                ref.child("xyz").setValue(value);
                handler.postDelayed(this, 20000);
            }
        };
        handler.postDelayed(r, 20000);
       // Toast.makeText(this,"serrvice started",Toast.LENGTH_LONG).show();
        return Service.START_STICKY_COMPATIBILITY;
    }
}
