package com.example.vig.navigationtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class TestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        //immagine = findViewById(R.id.imageView);
        //immagine.setImageResource(R.drawable.logo_astrea);
        VideoView videoView = findViewById(R.id.videoView);
        String PATH = "android.resource://" + TestActivity.this.getPackageName() + "/" + R.raw.myvid;
        videoView.findViewById(R.id.videoView);
        videoView.setVideoPath(PATH);

        MediaController mediacontroller = new MediaController(TestActivity.this);
        mediacontroller.setAnchorView(videoView);
        videoView.setMediaController(mediacontroller);
        videoView.start();
    }
}
