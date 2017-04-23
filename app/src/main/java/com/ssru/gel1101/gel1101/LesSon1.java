package com.ssru.gel1101.gel1101;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;



public class LesSon1 extends Activity{
    ImageView icon1_group1,icon2_group1,icon3_group1;
    Button btn1_group1,btn2_group1,btn3_group1,btn1_group4;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson1);


        icon1_group1 = (ImageView)findViewById(R.id.icon1_group1);
        icon2_group1 = (ImageView)findViewById(R.id.icon2_group1);
        icon3_group1 = (ImageView)findViewById(R.id.icon3_group1);
        btn1_group1 = (Button)findViewById(R.id.btn1_group1);
        btn2_group1 = (Button)findViewById(R.id.btn2_group1);
        btn3_group1 = (Button)findViewById(R.id.btn3_group1);
        btn1_group4 = (Button)findViewById(R.id.btn1_group4);

        btn1_group1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickImage1();
            }
        });
        btn2_group1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickImage2();
            }
        });
        btn2_group1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickImage3();
            }
        });
        btn1_group4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String asdasd = onCreateDialog(savedInstanceState);
            }
        });

    }

    public void PickImage1() {
        Intent startCrop = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startCrop.putExtra("crop", "true");
        startCrop.putExtra("aspectX", 0);
        startCrop.putExtra("aspectY", 0);
        startCrop.putExtra("outputX", 0);
        startCrop.putExtra("outputY", 0);
        startCrop.putExtra("return-data", true);
        startActivityForResult(startCrop, 1);
    }
    public void PickImage2() {
        Intent startCrop = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startCrop.putExtra("crop", "true");
        startCrop.putExtra("aspectX", 0);
        startCrop.putExtra("aspectY", 0);
        startCrop.putExtra("outputX", 0);
        startCrop.putExtra("outputY", 0);
        startCrop.putExtra("return-data", true);
        startActivityForResult(startCrop, 2);
    }
    public void PickImage3() {
        Intent startCrop = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startCrop.putExtra("crop", "true");
        startCrop.putExtra("aspectX", 0);
        startCrop.putExtra("aspectY", 0);
        startCrop.putExtra("outputX", 0);
        startCrop.putExtra("outputY", 0);
        startCrop.putExtra("return-data", true);
        startActivityForResult(startCrop, 3);
    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data) {
        if(data != null) {
            Bundle extras = data.getExtras();
            Bitmap tempBitmap = extras.getParcelable("data");

            if (requestCode == 1 && resultCode == RESULT_OK ) {
                icon1_group1.setImageBitmap(tempBitmap);
                icon1_group1.setVisibility(View.VISIBLE);
                btn1_group1.setVisibility(View.GONE);

            } else if (requestCode == 2 && resultCode == RESULT_OK) {
                icon2_group1.setImageBitmap(tempBitmap);
                icon2_group1.setVisibility(View.VISIBLE);
                btn2_group1.setVisibility(View.GONE);

            } else if (requestCode == 3 && resultCode == RESULT_OK) {
                icon3_group1.setImageBitmap(tempBitmap);
                icon3_group1.setVisibility(View.VISIBLE);
                btn3_group1.setVisibility(View.GONE);

            } else {

            }
        }else {
            //Do not thing when user cancel
        }

    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("asd")
                .setItems(R.array.group4choice, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                    }
                });
        return builder.create();
    }
}
