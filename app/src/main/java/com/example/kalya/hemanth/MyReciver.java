package com.example.kalya.hemanth;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.widget.Toast;

public class MyReciver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
        if (info != null && info.isConnected()) {
            // Do your work.

            // e.g. To check the Network Name or other info:
            WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
            WifiInfo wifiInfo = wifiManager.getConnectionInfo();
            String ssid = wifiInfo.getSSID();
            //Toast.makeText(context,ssid.toString(), Toast.LENGTH_LONG).show();
            String id = (String) ssid.subSequence(1,7);
            //Toast.makeText(context,id, Toast.LENGTH_LONG).show();
            if (id.equals("passwo")) {
                Toast.makeText(context,"service starting.....", Toast.LENGTH_LONG).show();
                    Intent a = new Intent(context, MyService3.class);
                    context.startService(a);
            }
        }
    }
}
