package com.example.vig.navigationtest;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class IntentSecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent_second);

        Intent i= getIntent();
        Bundle b = i.getExtras();

        if(b!=null)
        {
            String nome =(String) b.get("EXTRA_USERNAME");
            String pwd =(String) b.get("EXTRA_PASSWORD");

            TextView tvnome = findViewById(R.id.tvnome);
            TextView tvpassword = findViewById(R.id.tvpassword);
            tvnome.setText(nome);
            tvpassword.setText(pwd);
        }



    }

    public void endActivity(View v)
    {
        Intent data = new Intent();
        String text = "Result to be returned....";
        //---set the data to pass back---
        data.setData(Uri.parse(text));
        setResult(RESULT_OK, data);
        //---close the activity---
        finish();
    }
}
