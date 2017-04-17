package com.ssru.gel1101.gel1101;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.github.chrisbanes.photoview.PhotoView;

public class LesSon1_1 extends Activity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lesson1_1);

        PhotoView photoView = (PhotoView) findViewById(R.id.photo_view);
        photoView.setImageResource(R.drawable.bggel1101);
    }
}
