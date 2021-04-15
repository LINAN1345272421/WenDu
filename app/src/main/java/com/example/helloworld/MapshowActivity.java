package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class MapshowActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_map);
        WebView webView = (WebView) findViewById(R.id.map_web);
        String url = "http://192.168.1.102:5000/tem";
        webView.loadUrl(url);
    }
}