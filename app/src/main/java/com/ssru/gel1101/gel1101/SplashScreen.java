package com.ssru.gel1101.gel1101;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class SplashScreen extends Activity {

    Handler handler;
    Runnable runnable;
    long delay_time;
    long time = 3000L;
    TextView preld;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        //Check API Level at start App.
        if (android.os.Build.VERSION.SDK_INT >= 23){
            RequestPermissionAndroid6();
        }

        handler = new Handler();
        preld =(TextView)findViewById(R.id.preld);
        runnable = new Runnable() {
            public void run() {
                copyfileassets();
                Intent intent_tomain = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent_tomain);
                finish();
            }
        };

    }
    // Asking For Permission.
    static final int ASK_MULTIPLE_PERMISSION_REQUEST_CODE = 123;
    @TargetApi(Build.VERSION_CODES.M)
    public void RequestPermissionAndroid6() {
        requestPermissions(new String[]{
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA},
                ASK_MULTIPLE_PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,int[] grantResults){
        if (requestCode == ASK_MULTIPLE_PERMISSION_REQUEST_CODE){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED){
            }else {
                Toast.makeText(SplashScreen.this,"Permission needed",Toast.LENGTH_LONG).show();
            }
        }
    }

    //Copy Language file to device
    private void copyfileassets() {
        File CheckLangDir1 = new File(Environment.getExternalStorageDirectory() + "/GEL1101/Image/img1_group1.png");
        File CheckLangDir2 = new File(Environment.getExternalStorageDirectory() + "/GEL1101/Image/img1_group3.png");
        File CheckLangDir3 = new File(Environment.getExternalStorageDirectory() + "/GEL1101/Image/img2_group1.png");
        File CheckLangDir4 = new File(Environment.getExternalStorageDirectory() + "/GEL1101/Image/img2_group3.png");
        File CheckLangDir5 = new File(Environment.getExternalStorageDirectory() + "/GEL1101/Image/img3_group1.png");
        File CheckLangDir6 = new File(Environment.getExternalStorageDirectory() + "/GEL1101/Image/img3_group3.png");
        File CheckLangDir7 = new File(Environment.getExternalStorageDirectory() + "/GEL1101/Image/img4_group3.png");
        File CheckLangDir8 = new File(Environment.getExternalStorageDirectory() + "/GEL1101/Image/img5_group3.png");

        if (CheckLangDir1.exists()&&CheckLangDir2.exists()&&CheckLangDir3.exists()&&CheckLangDir4.exists()&&CheckLangDir5.exists()&&
                CheckLangDir6.exists()&&CheckLangDir7.exists()&&CheckLangDir8.exists()) {
            //Do nothing
            preld.setText("Image File was found ");
        } else {
            preld.setText("Image File not found Start Copy Image File");
            File LangDataDirectory = new File(Environment.getExternalStorageDirectory() + "/GEL1101/Image");
            LangDataDirectory.mkdirs();

            AssetManager assetManager = getAssets();
            String[] files = null;
            try {
                files = assetManager.list("Files");
            } catch (Exception e) {
                Log.e("Tag", e.getMessage());
            }
            for (String filename : files) {
                System.out.println("file :" + filename);
                InputStream in = null;
                OutputStream out = null;

                try {
                    in = assetManager.open("Files/" + filename);
                    out = new FileOutputStream(Environment.getExternalStorageDirectory().toString() + "/GEL1101/Image/" + filename);
                    copyFiles(in, out);
                    in.close();
                    in = null;
                    out.flush();
                    out.close();
                    out = null;
                    File asd= new File(Environment.getExternalStorageDirectory().toString() + "/GEL1101/Image/" + filename);
                    sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.fromFile(asd)));
                } catch (Exception e) {
                    Log.e("Tag", e.getMessage());
                }
            }
        }
    }

    private void copyFiles(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1) {
            out.write(buffer, 0, read);

        }
    }

    public void onResume() {
        super.onResume();
        delay_time = time;
        handler.postDelayed(runnable, delay_time);
        time = System.currentTimeMillis();
    }

    public void onPause() {
        super.onPause();
        handler.removeCallbacks(runnable);
        time = delay_time - (System.currentTimeMillis() - time);
    }
}
