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
    Button okbtn, canclebtn;
    WebView myWebView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_layout);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int layout_width = dm.widthPixels;
        int layout_height = dm.heightPixels;
        getWindow().setLayout((int) (layout_width*.9), (int) (layout_height*.85));

        // ตั้งค่า Browser
        myWebView = (WebView) findViewById(R.id.webview);
        myWebView.getSettings().setJavaScriptEnabled(true);
        myWebView.getSettings().setBuiltInZoomControls(true);
        myWebView.getSettings().setDisplayZoomControls(false);
        String newUA= "Mozilla/5.0 (masking-agent; rv:14.0) Gecko/20100101 Firefox/14.0.1";
        myWebView.getSettings().setUserAgentString(newUA);
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.loadUrl("https://www.google.co.th/imghp?hl=th&tab=wi&ei=s5MJWab-C8bL0ATV1oeYDw&ved=0EKouCBIoAQ");

        //Define button
        okbtn = (Button) findViewById(R.id.okbtn);
        canclebtn = (Button) findViewById(R.id.canclebtn);

        //ปุ่ม OK
        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String weburl = myWebView.getUrl();
                Log.d("weburl", String.valueOf(weburl));
                Data.imageforshow = String.valueOf(weburl);
                finish();
            }
        });
        //ปุ่ม Back
        canclebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
