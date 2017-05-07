package com.ssru.gel1101.gel1101;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (android.os.Build.VERSION.SDK_INT >= 23){
            RequestPermissionAndroid6();
        }


        Button goTolesson1 = (Button)findViewById(R.id.button1);
        goTolesson1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goTolesson1page = new Intent(MainActivity.this,LesSon1.class);
                startActivity(goTolesson1page);
            }
        });

        Button goTolesson1_1 = (Button)findViewById(R.id.button2);
        goTolesson1_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goTolesson1_1page = new Intent(MainActivity.this,LesSon1_1.class);
                startActivity(goTolesson1_1page);
            }
        });
        Button buttonexit = (Button)findViewById(R.id.button4);
        buttonexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Exit");
                dialog.setIcon(R.drawable.icon_lable2);
                dialog.setCancelable(true);
                dialog.setMessage("คุณต้องการออกจากแอปหรือไม่ ?");
                dialog.setPositiveButton("ใช่", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                dialog.setNegativeButton("ไม่", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                dialog.show();
            }
        });
        Button gotonew = (Button)findViewById(R.id.button5);
        gotonew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NewActivity.class);
                startActivity(intent);
            }
        });
        final File file = assetToFile("worksheet/GEL1101_1.pdf");
        Button buttonIntent = (Button)findViewById(R.id.button3);
        buttonIntent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                String mimeType = MimeTypeMap.getSingleton()
                        .getMimeTypeFromExtension(MimeTypeMap
                                .getFileExtensionFromUrl(file.getPath()));
                intent.setDataAndType(Uri.fromFile(file), mimeType);
                startActivity(Intent.createChooser(intent, "Open file with"));
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

    public File assetToFile(String filePath) {
        new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                , "tmp").mkdir();
        String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);
        File file = new File(Environment.getExternalStorageDirectory()
                , "tmp/" + fileName);

        try {
            InputStream is = getResources().getAssets().open(filePath);
            OutputStream out = new FileOutputStream(file);
            byte[] buffer = new byte[is.available()];
            is.read(buffer);
            out.write(buffer, 0, buffer.length);
            is.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;

    }
}
