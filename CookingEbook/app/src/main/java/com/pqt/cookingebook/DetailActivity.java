package com.pqt.cookingebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();
        String url = extras.getString(MainActivity.MESSAGE_URL_DETAIL);

        WebView webView = (WebView) findViewById(R.id.webview);
        webView.loadUrl(url);
    }
}
