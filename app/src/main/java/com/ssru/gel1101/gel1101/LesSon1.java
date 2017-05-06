package com.ssru.gel1101.gel1101;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Typeface;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;


public class LesSon1 extends Activity {
    ImageView icon1_group1, icon2_group1, icon3_group1, img1_group3, icon1_group2, icon2_group2, img2_group3;
    Button btn2_group3, btn1_group1, btn2_group1, btn3_group1, btn1_group4, btn2_group4, btn3_group4, btn4_group4, btn5_group4, btn1_group3, btn1_group2, btn2_group2;
    EditText edt1_group2, edt2_group2;
    private static File Imageforsave;
    private Uri file;

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson1);

        //image
        icon1_group1 = (ImageView) findViewById(R.id.icon1_group1);
        icon2_group1 = (ImageView) findViewById(R.id.icon2_group1);
        icon3_group1 = (ImageView) findViewById(R.id.icon3_group1);
        icon1_group2 = (ImageView) findViewById(R.id.icon1_group2);
        icon2_group2 = (ImageView) findViewById(R.id.icon2_group2);
        img1_group3 = (ImageView) findViewById(R.id.img1_group3);
        //button
        btn1_group1 = (Button) findViewById(R.id.btn1_group1);
        btn2_group1 = (Button) findViewById(R.id.btn2_group1);
        btn3_group1 = (Button) findViewById(R.id.btn3_group1);
        btn1_group4 = (Button) findViewById(R.id.btn1_group4);
        btn2_group4 = (Button) findViewById(R.id.btn2_group4);
        btn3_group4 = (Button) findViewById(R.id.btn3_group4);
        btn4_group4 = (Button) findViewById(R.id.btn4_group4);
        btn5_group4 = (Button) findViewById(R.id.btn5_group4);
        btn1_group3 = (Button) findViewById(R.id.btn1_group3);
        img2_group3 = (ImageView) findViewById(R.id.img2_group3);
        btn1_group2 = (Button) findViewById(R.id.btn1_group2);
        btn2_group2 = (Button) findViewById(R.id.btn2_group2);

        //edit text
        edt1_group2 = (EditText) findViewById(R.id.edt1_group2);
        edt2_group2 = (EditText) findViewById(R.id.edt2_group2);


        btn1_group1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickImage(1);
            }
        });
        btn2_group1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickImage(2);
            }
        });
        btn3_group1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PickImage(3);
            }
        });
        btn1_group4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog(1);
            }
        });
        btn2_group4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog(2);
            }
        });
        btn3_group4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog(3);
            }
        });
        btn4_group4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog(4);
            }
        });
        btn5_group4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog(5);
            }
        });
        btn1_group2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt1_group2.getText().toString().equals("จุดประสงค์ในการสื่อสาร")) {
                    Toast.makeText(LesSon1.this, "คุณตอบถูก", Toast.LENGTH_LONG).show();
                    icon1_group2.setVisibility(View.VISIBLE);
                    btn1_group2.setVisibility(View.GONE);
                } else {
                    Toast.makeText(LesSon1.this, "คุณตอบผิด", Toast.LENGTH_LONG).show();
                }
            }
        });
        btn2_group2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt2_group2.getText().toString().equals("ผู้รับสารเพื่อเลือกใช้ภาษา")) {
                    Toast.makeText(LesSon1.this, "คุณตอบถูก", Toast.LENGTH_LONG).show();
                    icon2_group2.setVisibility(View.VISIBLE);
                    btn2_group2.setVisibility(View.GONE);
                } else {
                    Toast.makeText(LesSon1.this, "คุณตอบผิด", Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    public void PickImage(int whatButton) {
        Intent startCrop = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startCrop.putExtra("crop", "true");
        startCrop.putExtra("aspectX", 0);
        startCrop.putExtra("aspectY", 0);
        startCrop.putExtra("outputX", 0);
        startCrop.putExtra("outputY", 0);
        startCrop.putExtra("return-data", true);
        startActivityForResult(startCrop, whatButton);
    }


    public void openCamera(View view) {
        try {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            file = FileProvider.getUriForFile(this, this.getApplicationContext().getPackageName() + ".provider", getOutputMediaFile());
            intent.putExtra(MediaStore.EXTRA_OUTPUT, file);
            startActivityForResult(intent, 22);
        } catch (Exception e) {
            Log.d("error_camera", String.valueOf(e));
        }
    }

    private static File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory() + "/GEL1101/Image");
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        Imageforsave = new File(mediaStorageDir.getPath() + File.separator +
                "IMG_" + timeStamp + ".jpg");
        return Imageforsave;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data != null) {
            Bundle extras = data.getExtras();
            Bitmap tempBitmap = extras.getParcelable("data");

            if (requestCode == 1 && resultCode == RESULT_OK) {
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

            } else if (requestCode == 22 && resultCode == RESULT_OK) {
                img2_group3.setImageBitmap(tempBitmap);
                Toast.makeText(this, "Take Photo is ok", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(this, "Take Photo is not ok", Toast.LENGTH_LONG).show();
            }

        } else if (Imageforsave != null) {
            Bitmap tempbit2 = BitmapFactory.decodeFile(String.valueOf(Imageforsave));
            img2_group3.setImageBitmap(tempbit2);
        } else if (requestCode == 55 ){
            Glide.with(LesSon1.this).load(Data.imageforshow).into(img1_group3);
            img1_group3.setVisibility(View.VISIBLE);
            btn1_group3.setVisibility(View.GONE);
        }else {
            //Do not thing when user cancel
            Toast.makeText(this, "Take Photo is not ok 2", Toast.LENGTH_LONG).show();
        }

    }

    final String[] answer4 = new String[]{"ภาษาระดับกันเอง", "ภาษาระดับไม่เป็นทางการ", "ภาษาระดับกึ่งทางการ", "ภาษาระดับทางการ", "ภาษาระดับพิธีการ"};

    public void createDialog(int whatBtn) {
        final int WhaBTN1 = whatBtn;
        final AlertDialog.Builder builder = new AlertDialog.Builder(LesSon1.this);
        builder.setTitle("เลือกคำตอบที่ถูกต้อง");
        builder.setItems(
                answer4,
                new DialogInterface.OnClickListener() // Item click listener
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String selectedItem = Arrays.asList(answer4).get(i);
                        if (selectedItem == "ภาษาระดับกันเอง" && WhaBTN1 == 1) {
                            Toast.makeText(LesSon1.this, "ถูกต้อง", Toast.LENGTH_SHORT).show();
                            btn1_group4.setText(selectedItem);
                        } else if (selectedItem == "ภาษาระดับไม่เป็นทางการ" && WhaBTN1 == 2) {
                            Toast.makeText(LesSon1.this, "ถูกต้อง", Toast.LENGTH_SHORT).show();
                            btn2_group4.setText(selectedItem);
                        } else if (selectedItem == "ภาษาระดับกึ่งทางการ" && WhaBTN1 == 3) {
                            Toast.makeText(LesSon1.this, "ถูกต้อง", Toast.LENGTH_SHORT).show();
                            btn3_group4.setText(selectedItem);
                        } else if (selectedItem == "ภาษาระดับทางการ" && WhaBTN1 == 4) {
                            Toast.makeText(LesSon1.this, "ถูกต้อง", Toast.LENGTH_SHORT).show();
                            btn4_group4.setText(selectedItem);
                        } else if (selectedItem == "ภาษาระดับพิธีการ" && WhaBTN1 == 5) {
                            Toast.makeText(LesSon1.this, "ถูกต้อง", Toast.LENGTH_SHORT).show();
                            btn5_group4.setText(selectedItem);
                        } else {
                            Toast.makeText(LesSon1.this, "คุณเลือกผิด", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    public void createDialog2() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(LesSon1.this);
        final EditText input = new EditText(this);
        builder.setTitle("ใส่ Link รูปภาพ");
        builder.setView(input);
        builder.setNeutralButton("How to", new AlertDialog.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent goTohowto = new Intent(LesSon1.this, Howto.class);
                startActivity(goTohowto);
            }
        });

        builder.setPositiveButton("OK", new AlertDialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String value = input.getText().toString();
                //new LoadImageTask(LesSon1.this).execute(value);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }
    public void popUpwebview(View view) {
        Intent intent = new Intent(LesSon1.this,WebActivity.class);
        startActivityForResult(intent,55);

    }


}
