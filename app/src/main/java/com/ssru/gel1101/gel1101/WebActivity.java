package com.ssru.gel1101.gel1101;

import android.app.Activity;
import android.hardware.display.DisplayManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;


public class WebActivity extends Activity {
    Button okbtn, backbtn;
    WebView myWebView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_layout);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int layout_width = dm.widthPixels;
        int layout_height = dm.heightPixels;
        getWindow().setLayout((int) (layout_width), (int) (layout_height));

        myWebView = (WebView) findViewById(R.id.webview);
        myWebView.getSettings().setJavaScriptEnabled(true);
        String newUA= "Mozilla/5.0 (masking-agent; rv:14.0) Gecko/20100101 Firefox/14.0.1";
        myWebView.getSettings().setUserAgentString(newUA);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("https://www.google.co.th/");

        okbtn = (Button) findViewById(R.id.okbtn);
        backbtn = (Button) findViewById(R.id.backbtn);

        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weburl = myWebView.getUrl();
                Log.d("weburl", String.valueOf(weburl));
                Data.imageforshow = String.valueOf(weburl);
                finish();
            }
        });
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
