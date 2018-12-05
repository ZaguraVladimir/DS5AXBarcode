package com.bss.ds5_axbarcode;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BarcodeReceiver extends BroadcastReceiver {

    private String dataBarCode;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getStringExtra(BarcodeService.fieldBarCode) != null) {
            // DS5-AX это наша ветка
            this.dataBarCode = intent.getStringExtra(BarcodeService.fieldBarCode);
            if (this.dataBarCode.length() > 0) {
                context.sendBroadcast(getIntentFrom1C());
            }
        } else if (intent.getByteArrayExtra(BarcodeService.fieldBarCode) != null) {
            byte[] byteArrayExtra = intent.getByteArrayExtra(BarcodeService.fieldBarCode);
            int intExtra = intent.getIntExtra("length", 0);
            this.dataBarCode = new String(byteArrayExtra, 0, intExtra);
            if (intExtra > 0) {
                context.sendBroadcast(getIntentFrom1C());
            }
        } else if (intent.getStringExtra("com.hht.emdk.datawedge.data_string") != null) {
            int intExtra2 = intent.getIntExtra("com.hht.emdk.datawedge.data_length", 0);
            this.dataBarCode = intent.getStringExtra("com.hht.emdk.datawedge.data_string");
            if (intExtra2 > 0) {
                context.sendBroadcast(getIntentFrom1C());
            }
        } else if (intent.getAction().equals("com.cipherlab.barcodebaseapi.PASS_DATA_2_APP")) {
            this.dataBarCode = intent.getStringExtra("Decoder_Data");
            if (this.dataBarCode.length() > 0) {
                context.sendBroadcast(getIntentFrom1C());
            }
        }
        Toast.makeText(context, dataBarCode, Toast.LENGTH_SHORT).show();
    }

    private Intent getIntentFrom1C() {
        Intent intentFrom1C = new Intent("com.bss.ds5_axbarcode.intent.RECEIVE");
        intentFrom1C.putExtra("text", BarcodeService.eventID);
        intentFrom1C.putExtra("title", "1C");
        intentFrom1C.putExtra("data", this.dataBarCode);
        if (!(BarcodeService.baseName == null && BarcodeService.baseName == "")) {
            intentFrom1C.putExtra("base", BarcodeService.baseName);
        }

        return intentFrom1C;
    }

}
