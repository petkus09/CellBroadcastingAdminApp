package com.example.CellBroadcastingAppLauncher;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;

import java.io.File;

public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        refresh();
    }

    public void refresh(){
        TextView myAwesomeTextView = (TextView)findViewById(R.id.cbstatus);
        try {
            int CBValue = Settings.Global.getInt(getContentResolver(), "cdma_cell_broadcast_sms");
            if (CBValue == 0){
                myAwesomeTextView.setText("OFF");
            }
            else if (CBValue == 1){
                myAwesomeTextView.setText("ON");
            }
            else{
                myAwesomeTextView.setText("UNKNOWN VALUE "+CBValue);
            }
        } catch (Exception e) {
            myAwesomeTextView.setText("ERROR");
        }
    }

    public void LaunchCellBroadcasting(View v){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setComponent(new ComponentName("com.android.cellbroadcastreceiver", "com.android.cellbroadcastreceiver.CellBroadcastSettings"));
        startActivity(intent);
    }

    public void LaunchCellBroadcastingList(View v){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setComponent(new ComponentName("com.android.cellbroadcastreceiver", "com.android.cellbroadcastreceiver.CellBroadcastListActivity"));
        startActivity(intent);
    }

    public void turnOnCellBroadcasting(View v){
        Settings.Global.putInt(getContentResolver(), "cdma_cell_broadcast_sms", 1);
        refresh();
    }

    public void turnOffCellBroadcasting(View v){
        Settings.Global.putInt(getContentResolver(), "cdma_cell_broadcast_sms", 0);
        refresh();
    }
}
