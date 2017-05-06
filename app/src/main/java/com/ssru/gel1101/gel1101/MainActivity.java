package com.ssru.gel1101.gel1101;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    private static File Imageforsave;
    private Uri file;
    Button btn2_group3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (android.os.Build.VERSION.SDK_INT >= 23) {
            RequestPermissionAndroid6();
        }


        Button goTolesson1 = (Button) findViewById(R.id.button1);
        goTolesson1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goTolesson1page = new Intent(MainActivity.this, LesSon1.class);
                startActivity(goTolesson1page);
            }
        });

        Button goTolesson1_1 = (Button) findViewById(R.id.button2);
        goTolesson1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goTolesson1_1page = new Intent(MainActivity.this, LesSon1_1.class);
                startActivity(goTolesson1_1page);
            }
        });
    }

    // Asking For Permission.
    static final int ASK_MULTIPLE_PERMISSION_REQUEST_CODE = 123;

    @TargetApi(Build.VERSION_CODES.M)
    public void RequestPermissionAndroid6() {
        requestPermissions(new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                ASK_MULTIPLE_PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == ASK_MULTIPLE_PERMISSION_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            } else {
                Toast.makeText(MainActivity.this, "แอพพฃิเคชั่นนี้ต้องการ Permission ในการเข้าถึงไฟล์ ", Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


    }
}
