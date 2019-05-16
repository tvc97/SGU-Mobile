package com.pqt.cookingebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

public class HighResImageActivity extends AppCompatActivity {
    private String highResUrl;

    public HighResImageActivity() {
        this.highResUrl = null;
    }

    public HighResImageActivity(String highResUrl) {
        this.highResUrl = highResUrl;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.high_res_image);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String message = extras.getString(CustomListAdapter.MESSAGE_HIGH_RES_URL);

        ImageView image = (ImageView) findViewById(R.id.imageHighRes);
        Log.e("image", message);
        if(message != null) {
            new DownloadImageTask(image).execute(message);
        }

    }
}
