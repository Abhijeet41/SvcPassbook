package com.example.mwservice.svcpassbook;

import android.content.Intent;
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

import java.text.SimpleDateFormat;
import java.util.Date;

public class DashboardActivity extends AppCompatActivity {
    ImageView imgbtnSaving,imgbtnCurrent,imgbtnFixed,imgbtnRecurring,imgbtnTermLoan,imgbtnOverDraft;
    LinearLayout llOne;
    TextView txtDate;
    String strTimeStamp;
    RelativeLayout rlMain;
    LinearLayout llLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        llOne = (LinearLayout) findViewById(R.id.llOne);
        rlMain = (RelativeLayout) findViewById(R.id.rlMain);
        rlMain.getBackground().setAlpha(80);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa");  /*hh:mm aa, dd MMM yyyy*/
        strTimeStamp = dateFormat.format(new Date()).toString();

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.small_svc_logo_dash);
        getSupportActionBar().setTitle(" m-Passbook");
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        txtDate = (TextView) findViewById(R.id.txtDate1);
        imgbtnSaving = (ImageView) findViewById(R.id.imgBtnSaving);
        imgbtnCurrent = (ImageView) findViewById(R.id.imgBtnCurrent);
        imgbtnFixed = (ImageView) findViewById(R.id.imgBtnFixed);
        imgbtnRecurring = (ImageView) findViewById(R.id.imgBtnRecurring);
        imgbtnTermLoan = (ImageView) findViewById(R.id.imgBtnTerm);
        imgbtnOverDraft = (ImageView) findViewById(R.id.imgBtnOverDraft);

        llLocation = (LinearLayout) findViewById(R.id.llBrnchLocate);

        txtDate.setText(strTimeStamp);

        llLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this,BranchLocatorActivity.class);
                startActivity(intent);
            }
        });


        llOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ViewPassbook.class);
                startActivity(intent);
            }
        });
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

                break;
            case R.id.alert:
                Intent intent = new Intent(DashboardActivity.this,ROIAlertActivity.class);
                startActivity(intent);
                return true;

            case R.id.action_logout:
                Intent intent1 = new Intent(DashboardActivity.this,LoginActivity.class);
                startActivity(intent1);
                break;

        }

        return super.onOptionsItemSelected(item);

    }
}
