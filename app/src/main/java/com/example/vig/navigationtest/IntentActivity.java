package com.example.vig.navigationtest;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static android.content.Intent.EXTRA_TEXT;

public class IntentActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        Button cmdCallSecond = findViewById(R.id.cmdButton);

        TextView txtMessage = findViewById(R.id.txtMessage);
        TextView txtNome = findViewById(R.id.txtNome);
        TextView txtPassword = findViewById(R.id.txtPassword);
        Intent i= getIntent();
        Bundle b = i.getExtras();

        if(b!=null)
        {
            String messaggio =(String) b.get("MioParametro");
            txtMessage.setText(messaggio);

            txtMessage.setTextColor(Color.BLACK);
            txtNome.setText("");
            txtPassword.setText("");
        }



    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String returnedResult = data.getData().toString();
                TextView txtMessage = findViewById(R.id.txtMessage);
                TextView txtNome = findViewById(R.id.txtNome);
                TextView txtPassword = findViewById(R.id.txtPassword);
                txtMessage.setText(returnedResult);
                txtNome.setText("");
                txtPassword.setText("");
                // OR
                // String returnedResult = data.getDataString();
            }
        }
    }

    public void call2ndActivity(View v)
    {
        TextView txtNome = findViewById(R.id.txtNome);
        TextView txtPassword = findViewById(R.id.txtPassword);
        Intent intent = new Intent(this, IntentSecondActivity.class);
        Bundle extras = new Bundle();
        extras.putString("EXTRA_USERNAME",txtNome.getText().toString());
        extras.putString("EXTRA_PASSWORD",txtPassword.getText().toString());
        intent.putExtras(extras);

        startActivityForResult(intent,0);
    }
}
