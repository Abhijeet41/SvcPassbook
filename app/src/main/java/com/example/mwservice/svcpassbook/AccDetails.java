package com.example.mwservice.svcpassbook;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class AccDetails extends AppCompatActivity {
    ImageView imgEntries;
    TextView txtAccNumber;
    String strDate1,strDate2,strAccNumber,strDisAccNumber;
    RelativeLayout rlMain;
    LinearLayout llViewEntries;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acc_details);

        SharedPreferences preferences = getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        strDate1 = preferences.getString("Date1","empty");
        strDate2 = preferences.getString("Date2","empty");
        strAccNumber = preferences.getString("AccNumber","empty");

        rlMain = (RelativeLayout) findViewById(R.id.rlMain);
        txtAccNumber = (TextView) findViewById(R.id.textView);
        strDisAccNumber = strAccNumber.replace(strAccNumber.substring(0,6),"******");

        txtAccNumber.setText(strDisAccNumber);

        rlMain.getBackground().setAlpha(80);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.small_svc_logo_dash);
        getSupportActionBar().setTitle(" m-Passbook");
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        llViewEntries = (LinearLayout) findViewById(R.id.llViewEntries);

        imgEntries = (ImageView) findViewById(R.id.imgEntries);

        llViewEntries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AccDetails.this,EntriesActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(AccDetails.this,ViewPassbook.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.home:
                Intent intent = new Intent(AccDetails.this,DashboardActivity.class);
                startActivity(intent);
                break;
            case R.id.alert:
                Intent intent1 = new Intent(AccDetails.this,ROIAlertActivity.class);
                startActivity(intent1);
                return true;

            case R.id.action_logout:
                Intent intent2 = new Intent(AccDetails.this,LoginActivity.class);
                startActivity(intent2);
                break;

        }

        return super.onOptionsItemSelected(item);

    }
}
