package com.example.vig.navigationtest;

import android.os.AsyncTask;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Locale;

import static com.example.vig.navigationtest.WebService.invokeDoppioniSanGiorgioStr;

public class WebServiceActivity extends AppCompatActivity {
    private Button cmdInsert;
    private TextView displayResult;
    private TextView lblContenuto;
    private Boolean IsDebuggingMode = false;
    private Boolean isConnectionValid = true;
    private RadioButton rbITA;
    private RadioButton rbENG;
    private RadioButton rbCHI;
    private RadioButton rbSilent;

    String displayText = "";
    TextToSpeech t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_service);
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });

        cmdInsert = (Button) findViewById (R.id.cmdInsert);
        rbENG = (RadioButton) findViewById(R.id.rbENG);
        rbITA = (RadioButton) findViewById(R.id.rbITA);
        rbCHI = (RadioButton) findViewById(R.id.rbCHI);
        rbSilent = (RadioButton) findViewById(R.id.rbSilent);

        cmdInsert.setOnClickListener (new View.OnClickListener ()
        {
            //@Override
            public void onClick(View view)
            {
                //txtResult.getText ().toString ();
                AsyncCallWS task = new AsyncCallWS ();
                task.execute ();
            }
        });


    }

    private class AsyncCallWS extends AsyncTask<String, Void, Void>
    {
        @Override
        protected Void doInBackground(String... params)
        {
            String aResult =  "";

            displayText = invokeDoppioniSanGiorgioStr( );
            displayResult = (TextView) findViewById (R.id.displayResult);
            lblContenuto = (TextView) findViewById(R.id.lblContenuto);

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            if (!displayText.equals("Error")) {
                lblContenuto.setText("Perizie auto presenti su DB  :  ");

                displayResult.setText(displayText);
                if(rbENG.isChecked())
                    t1.setLanguage(Locale.ENGLISH);
                else if (rbITA.isChecked())
                    t1.setLanguage((Locale.ITALIAN));
                else if (rbCHI.isChecked())
                    t1.setLanguage((Locale.CHINA));
                if(!rbSilent.isChecked())
                    t1.speak(lblContenuto.getText().toString()+ "  " + displayResult.getText().toString() , TextToSpeech.QUEUE_FLUSH, null);
            } else
            {
                lblContenuto.setText("Risultato non disponibile :");
                displayResult.setText("");
                if(rbENG.isChecked())
                    t1.setLanguage(Locale.ENGLISH);
                else if (rbITA.isChecked())
                    t1.setLanguage((Locale.ITALIAN));
                else if (rbCHI.isChecked())
                    t1.setLanguage((Locale.CHINA));
                if(!rbSilent.isChecked())
                    t1.speak(lblContenuto.getText().toString() + "  " + displayResult.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);
            }

        }


    }


}

