package com.example.vig.navigationtest;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ImageSelectedActivity extends AppCompatActivity {

    private Bitmap bitmap;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_selected);
    }

    public void onClick(View view) {
        /*// avvio la chiamata alla fotocamera
        Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(i, 0);*/
        Intent intent = new Intent();
        intent.setType("image/*"); // filtro solo le immagini
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, 0);
    }

   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent databack) {
        Bitmap immagine = (Bitmap) databack.getExtras().get("data");
        // img è un componente imageView presente sul layout
        ImageView img = findViewById(R.id.img);
        img.setImageBitmap(immagine);
        Toast.makeText(this, "Immagine recuperate che posso ora visualizzare in un ImageView", Toast.LENGTH_SHORT).show();
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        InputStream stream = null;
        if (bitmap != null) {
            bitmap.recycle();
        }
        try {
            stream = getContentResolver().openInputStream(data.getData());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Bitmap immagine = BitmapFactory.decodeStream(stream);
        ImageView img = findViewById(R.id.img);
        img.setImageBitmap(immagine);
    }
}
