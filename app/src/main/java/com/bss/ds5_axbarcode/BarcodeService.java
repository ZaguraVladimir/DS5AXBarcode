package com.bss.ds5_axbarcode;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.widget.Toast;

public class BarcodeService extends Service {

    public static String eventID;
    public static String baseName;
    public static String fieldBarCode;
    private static String fromApp;
    public boolean serviceRunning = false;

    public BarcodeService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        fromApp = intent.getStringExtra("FromApp");
        eventID = intent.getStringExtra("EventID");
        baseName = intent.getStringExtra("BaseName");
        fieldBarCode = intent.getStringExtra("FieldBarCode");
        registerReceiver(new BarcodeReceiver(), new IntentFilter(fromApp));
        this.serviceRunning = true;

        Toast.makeText(this, "Service started", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.serviceRunning = false;
        Toast.makeText(this, "Service stoped", Toast.LENGTH_SHORT).show();
    }
}
