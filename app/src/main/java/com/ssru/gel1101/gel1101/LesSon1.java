package com.ssru.gel1101.gel1101;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.github.chrisbanes.photoview.PhotoView;



public class LesSon1 extends Activity{
    ImageView icon1_group1;
    Button btn1_group1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson1);

        icon1_group1 = (ImageView)findViewById(R.id.icon1_group1);
        btn1_group1 = (Button)findViewById(R.id.btn1_group1);
    }

    static final int REQUEST_PICK_PHOTO = 3;
    public void PickImage(View view) {
        Intent startCrop = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startCrop.putExtra("crop", "true");
        startCrop.putExtra("aspectX", 0);
        startCrop.putExtra("aspectY", 0);
        startCrop.putExtra("outputX", 0);
        startCrop.putExtra("outputY", 0);
        startCrop.putExtra("return-data", true);
        startActivityForResult(startCrop, REQUEST_PICK_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
       if (requestCode == REQUEST_PICK_PHOTO && resultCode == RESULT_OK && data != null) {
           Bundle extras = data.getExtras();
           Bitmap tempbm = extras.getParcelable("data");
           icon1_group1.setImageBitmap(tempbm);
           icon1_group1.setVisibility(View.VISIBLE);
           btn1_group1.setVisibility(View.GONE);

        }else if (requestCode== RESULT_CANCELED){

        }

    }
}
