package com.example.vig.navigationtest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class WifiListActivity extends AppCompatActivity {

    private static final String TAG = "";
    private ListView listView;
    private ArrayList<String> lista;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi_list);
        listView = findViewById(R.id.listView);
        lista = new ArrayList<>();
        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, lista);
        listView.setAdapter(adapter);

        getCurrentSsid(WifiListActivity.this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_wifi_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.mnu_goto_settings:
                OpenWifiCenter();
                //getCurrentSsid(WifiListActivity.this);
            case R.id.mnu_rescan:
                getCurrentSsid(WifiListActivity.this);
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void OpenWifiCenter() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Open WIFI settings...");
        builder.setMessage("Press OK to go to WIFI settings !");

        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                startActivityForResult(new Intent(Settings.ACTION_WIFI_SETTINGS), 0);
                dialog.dismiss();
                //lblWiFiConnName.performClick();
            }

        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {


            public void onClick(DialogInterface dialog, int which) {
                // I do not need any action here you might
                dialog.dismiss();
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public String getCurrentSsid(Context context) {

        String ssid = null;
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (networkInfo.isConnected()) {
            final WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            final WifiInfo connectionInfo = wifiManager.getConnectionInfo();
            if (connectionInfo != null && !(connectionInfo.getSSID().equals(""))) {
                //if (connectionInfo != null && !StringUtil.isBlank(connectionInfo.getSSID())) {
                ssid = connectionInfo.getSSID();
                lista.clear();
                lista.add(ssid);
                adapter.notifyDataSetChanged();
            }

        } else {
            lista.clear();
            lista.add("Not connected !");
            adapter.notifyDataSetChanged();
        }
        return ssid;
    }


}
