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
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;


public class LesSon1 extends Activity implements LoadImageTask.Listener {
    ImageView icon1_group1, icon2_group1, icon3_group1, img1_group3,icon1_group2,icon2_group2;
    Button btn1_group1, btn2_group1, btn3_group1, btn1_group4, btn2_group4, btn3_group4, btn4_group4, btn5_group4,btn1_group3,btn1_group2,btn2_group2;
    EditText edt1_group2,edt2_group2;

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
                createDialog1();
            }
        });
        btn2_group4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog2();
            }
        });
        btn3_group4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog3();
            }
        });
        btn4_group4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog4();
            }
        });
        btn5_group4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog5();
            }
        });
        btn1_group3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialog6();
            }
        });
        btn1_group2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt1_group2.getText().toString().equals("จุดประสงค์ในการสื่อสาร")){
                    Toast.makeText(LesSon1.this,"คุณตอบถูก",Toast.LENGTH_LONG).show();
                    icon1_group2.setVisibility(View.VISIBLE);
                    btn1_group2.setVisibility(View.GONE);
                }else {
                    Toast.makeText(LesSon1.this,"คุณตอบผิด",Toast.LENGTH_LONG).show();
                }
            }
        });
        btn2_group2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edt2_group2.getText().toString().equals("ผู้รับสารเพื่อเลือกใช้ภาษา")){
                    Toast.makeText(LesSon1.this,"คุณตอบถูก",Toast.LENGTH_LONG).show();
                    icon2_group2.setVisibility(View.VISIBLE);
                    btn2_group2.setVisibility(View.GONE);
                }else {
                    Toast.makeText(LesSon1.this,"คุณตอบผิด",Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void PickImage(int whatButton) {
        Intent startCrop = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startCrop.putExtra("crop", "true");
        startCrop.putExtra("aspectX", 0);
        startCrop.putExtra("aspectY", 0);
        startCrop.putExtra("outputX", 0);
        startCrop.putExtra("outputY", 0);
        startCrop.putExtra("return-data", true);
        startActivityForResult(startCrop, whatButton);
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

            } else {

            }
        } else {
            //Do not thing when user cancel
        }

    }

    final String[] answer4 = new String[]{
            "ภาษาระดับกันเอง",
            "ภาษาระดับไม่เป็นทางการ",
            "ภาษาระดับกึ่งทางการ",
            "ภาษาระดับทางการ",
            "ภาษาระดับพิธีการ"
    };

    public void createDialog1() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(LesSon1.this);
        builder.setTitle("เลือกคำตอบที่ถูกต้อง");
        builder.setItems(
                answer4,
                new DialogInterface.OnClickListener() // Item click listener
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String selectedItem = Arrays.asList(answer4).get(i);
                        if (selectedItem != "ภาษาระดับกันเอง") {
                            Toast.makeText(LesSon1.this, "คุณเลือกผิด", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(LesSon1.this, "ถูกต้อง", Toast.LENGTH_SHORT).show();
                            btn1_group4.setText(selectedItem);
                        }
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void createDialog2() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(LesSon1.this);
        builder.setTitle("เลือกคำตอบที่ถูกต้อง");
        builder.setItems(
                answer4,
                new DialogInterface.OnClickListener() // Item click listener
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String selectedItem = Arrays.asList(answer4).get(i);
                        if (selectedItem != "ภาษาระดับไม่เป็นทางการ") {
                            Toast.makeText(LesSon1.this, "คุณเลือกผิด", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(LesSon1.this, "ถูกต้อง", Toast.LENGTH_SHORT).show();
                            btn2_group4.setText(selectedItem);
                        }
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void createDialog3() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(LesSon1.this);
        builder.setTitle("เลือกคำตอบที่ถูกต้อง");
        builder.setItems(
                answer4,
                new DialogInterface.OnClickListener() // Item click listener
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String selectedItem = Arrays.asList(answer4).get(i);
                        if (selectedItem != "ภาษาระดับกึ่งทางการ") {
                            Toast.makeText(LesSon1.this, "คุณเลือกผิด", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(LesSon1.this, "ถูกต้อง", Toast.LENGTH_SHORT).show();
                            btn3_group4.setText(selectedItem);
                        }
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void createDialog4() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(LesSon1.this);
        builder.setTitle("เลือกคำตอบที่ถูกต้อง");
        builder.setItems(
                answer4,
                new DialogInterface.OnClickListener() // Item click listener
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String selectedItem = Arrays.asList(answer4).get(i);
                        if (selectedItem != "ภาษาระดับทางการ") {
                            Toast.makeText(LesSon1.this, "คุณเลือกผิด", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(LesSon1.this, "ถูกต้อง", Toast.LENGTH_SHORT).show();
                            btn4_group4.setText(selectedItem);
                        }
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void createDialog5() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(LesSon1.this);
        builder.setTitle("เลือกคำตอบที่ถูกต้อง");
        builder.setItems(
                answer4,
                new DialogInterface.OnClickListener() // Item click listener
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String selectedItem = Arrays.asList(answer4).get(i);
                        if (selectedItem != "ภาษาระดับพิธีการ") {
                            Toast.makeText(LesSon1.this, "คุณเลือกผิด", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(LesSon1.this, "ถูกต้อง", Toast.LENGTH_SHORT).show();
                            btn5_group4.setText(selectedItem);
                        }
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void createDialog6() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(LesSon1.this);
        final EditText input = new EditText(this);
        builder.setTitle("ใส่ Link รูปภาพ");
        builder.setView(input);
        builder.setNeutralButton("How to",new AlertDialog.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent goTohowto = new Intent(LesSon1.this,Howto.class);
                startActivity(goTohowto);
            }
        });

        builder.setPositiveButton("OK", new AlertDialog.OnClickListener() {


            @Override
            public void onClick(DialogInterface dialog, int which) {
                String value = input.getText().toString();
                new LoadImageTask(LesSon1.this).execute(value);
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }



    @Override
    public void onImageLoaded(Bitmap bitmap) {
        img1_group3.setImageBitmap(bitmap);
        img1_group3.setVisibility(View.VISIBLE);
        btn1_group3.setVisibility(View.GONE);
    }

    @Override
    public void onError() {

    }

}
