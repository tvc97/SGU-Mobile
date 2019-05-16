package com.pqt.tacopronto;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.telephony.SmsManager;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RadioGroup groupSize;
    private RadioGroup groupTortilla;
    private List<String> arrFillings = new ArrayList<String>();
    private List<String> arrBeverages = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fabSend);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                groupSize = (RadioGroup) findViewById(R.id.groupSize);
                groupTortilla = (RadioGroup) findViewById(R.id.groupTortilla);

                int selectedSize = groupSize.getCheckedRadioButtonId();
                int selectedTortilla = groupTortilla.getCheckedRadioButtonId();

                RadioButton radioSize = findViewById(selectedSize);
                RadioButton radioTortilla = findViewById(selectedTortilla);

                if(radioSize == null) {
                    Snackbar.make(view, "Please select Size", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else if(radioTortilla == null) {
                    Snackbar.make(view, "Please select Tortilla", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
                else {
                    LinearLayout Fillings = (LinearLayout) findViewById(R.id.linenearFillings1);

                    arrFillings.addAll(getCheckedInLinearLayout(Fillings));

                    Fillings = (LinearLayout) findViewById(R.id.linenearFillings2);
                    arrFillings.addAll(getCheckedInLinearLayout(Fillings));

                    LinearLayout Baverage = (LinearLayout) findViewById(R.id.linearLayoutBeverages1);
                    arrBeverages.addAll(getCheckedInLinearLayout(Baverage));

                    Baverage = (LinearLayout) findViewById(R.id.linearLayoutBeverages2);
                    arrBeverages.addAll(getCheckedInLinearLayout(Baverage));

                    StringBuilder sbFillings = new StringBuilder();
                    for(String item : arrFillings) {
                        sbFillings.append(item + ", ");
                    }

                    StringBuilder sbBaverage = new StringBuilder();
                    for(String item : arrBeverages) {
                        sbBaverage.append(item + ", ");
                    }

                    String result = "I want to order Taco " + radioSize.getText() + ", Tortilla: " + radioTortilla.getText() + ", Fillings: " + sbFillings.toString() + ". And Beverage: " + sbBaverage.toString();

                    Snackbar.make(view, result, Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();


                    String phoneNumber = "5556";

                    Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:" + phoneNumber));
                    intent.putExtra("sms_body", result);

                    startActivity(intent);
                }
            }
        });
    }

    public List<String> getCheckedInLinearLayout(LinearLayout layout) {
        List<String> result = new ArrayList<String>();
        for(int i = 0; i < layout.getChildCount(); i++) {
            View v = layout.getChildAt(i);
            if(v instanceof CheckBox) {
                if(((CheckBox) v).isChecked()) {
                    result.add(((CheckBox) v).getText().toString());
                }
            }
        }
        return result;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
