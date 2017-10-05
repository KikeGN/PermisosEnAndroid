package com.kikebmx.permisos;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.SweepGradient;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Principal extends AppCompatActivity {
    Button llamar,btnFoto;
    ImageView img;
    static final int Capture = 1;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        llamar = (Button) findViewById(R.id.btnLlamar);
        btnFoto = (Button) findViewById(R.id.btnFoto);
        img = (ImageView)findViewById(R.id.imageView);

        llamar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:4451250561"));
                if (ActivityCompat.checkSelfPermission(Principal.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                    return;
                startActivity(callIntent);

            }
        });
        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                llamarIntent();
            }
        });
    }
    public void llamarIntent()
    {
        Intent takeFoto = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takeFoto.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(takeFoto,Capture);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == Capture && resultCode == RESULT_OK)
        {
            Bundle extras = data.getExtras();
            Bitmap mapaBit = (Bitmap) extras.get("data");
            img.setImageBitmap(mapaBit);
        }
    }
}
