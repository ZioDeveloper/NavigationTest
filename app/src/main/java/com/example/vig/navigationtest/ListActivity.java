package com.example.vig.navigationtest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> lista;
    private ArrayAdapter<String> adapter;


    WifiManager mainWifi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.listView);

        lista = new ArrayList<>();
        inizializzaLista(6);

        adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, lista);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(ListActivity.this, " Elemento : " + position, Toast.LENGTH_LONG).show();
            }
        });
    }

    public void inizializzaLista(int numero) {
        for (int i = 0; i < numero; i++) {
            lista.add("Elemento " + String.valueOf(i));
        }
    }


}
