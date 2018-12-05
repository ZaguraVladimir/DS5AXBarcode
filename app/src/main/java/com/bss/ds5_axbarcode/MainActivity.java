package com.bss.ds5_axbarcode;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Intent parentIntent = getIntent();

        String State = parentIntent.getStringExtra("State");
        State = State == null ? "start" : State;

        String fromApp = parentIntent.getStringExtra("FromApp");
        fromApp = fromApp == null ? "app.dsic.barcodetray.BARCODE_BR_DECODING_DATA" : fromApp;

        Intent serviceIntent = new Intent(this, BarcodeService.class);
        serviceIntent.setPackage(getPackageName());

        if (State.equalsIgnoreCase("start")) {

            String fieldBarCode = "EXTRA_BARCODE_DECODED_DATA";
            if (parentIntent.getStringExtra("FieldBarCode") != null) {
                fieldBarCode = parentIntent.getStringExtra("FieldBarCode");
            }

            String eventID = "1";
            if (parentIntent.getStringExtra("EventID") != null) {
                eventID = parentIntent.getStringExtra("EventID");
            }

            String baseName = "";
            if (parentIntent.getStringExtra("BaseName") != null) {
                baseName = parentIntent.getStringExtra("BaseName");
            }

            serviceIntent.putExtra("FromApp", fromApp);
            serviceIntent.putExtra("FieldBarCode", fieldBarCode);
            serviceIntent.putExtra("EventID", eventID);
            serviceIntent.putExtra("BaseName", baseName);

            startService(serviceIntent);
            setResult(88);

        } else if (State.equalsIgnoreCase("stop")) {
            stopService(serviceIntent);
            setResult(88);
        }

        finish();
    }
}
