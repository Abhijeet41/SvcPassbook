package com.example.mwservice.svcpassbook;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ViewPassbook extends AppCompatActivity {
    Spinner spnAccountNo;
    ImageView imgView1, imgView2;
    LinearLayout llDate1,llDate2;
    TextView txtDate1,txtDate2;
    RelativeLayout rlMain;
    long currDifference1,currDifference2;
    int intdiffDate;
    String strAccountNo,strDateDifference,strDateTime;
    Button btnViewPassbook;
    int mYear,mMonth,mDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_passbook);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.small_svc_logo_dash);
        getSupportActionBar().setTitle(" m-Passbook");
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        rlMain = (RelativeLayout) findViewById(R.id.rlMain);

        rlMain.getBackground().setAlpha(80);

        llDate1 = (LinearLayout) findViewById(R.id.llDate1);
        llDate2 = (LinearLayout) findViewById(R.id.llDate2);
        spnAccountNo = (Spinner) findViewById(R.id.spinner);
        imgView1 = (ImageView) findViewById(R.id.imageView1);
        imgView2 = (ImageView) findViewById(R.id.imageView2);
        txtDate1 = (TextView) findViewById(R.id.date1);
        txtDate2 = (TextView) findViewById(R.id.date2);
        btnViewPassbook = (Button) findViewById(R.id.ViewPassport);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(ViewPassbook.this, R.array.Select_Account, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spnAccountNo.setAdapter(adapter);

        spnAccountNo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (position == 0){
                    strAccountNo = "";
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                }
                else if (position == 1)
                {
                    strAccountNo = "54874651122";
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                }else if (position == 2)
                {
                    strAccountNo = "54878474545";
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                }
                else if (position == 3)
                {
                    strAccountNo = "54876622554";
                    ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        llDate1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {

                final Calendar mcurrentDate = Calendar.getInstance();
                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                String month = String.valueOf(mMonth);

                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker=new DatePickerDialog(ViewPassbook.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        selectedmonth = selectedmonth+1;
                        txtDate1.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                    }
                },mYear, mMonth, mDay);
                //strDate1 = txtDate1.getText().toString().trim();
                //Toast.makeText(ViewPassbook.this, strDate1, Toast.LENGTH_SHORT).show();
                mDatePicker.setTitle("Select date");
                mDatePicker.show();

            }
        });

        llDate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar mcurrentDate = Calendar.getInstance();
                mYear = mcurrentDate.get(Calendar.YEAR);
                mMonth = mcurrentDate.get(Calendar.MONTH);
                String month = String.valueOf(mMonth);

                mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog mDatePicker=new DatePickerDialog(ViewPassbook.this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        selectedmonth = selectedmonth+1;
                        txtDate2.setText("" + selectedday + "/" + selectedmonth + "/" + selectedyear);
                    }
                },mYear, mMonth, mDay);
                //strDate2 = txtDate2.getText().toString().trim();
                //Toast.makeText(ViewPassbook.this, strDate2, Toast.LENGTH_SHORT).show();
                mDatePicker.setTitle("Select date");
                mDatePicker.show();
            }
        });

        btnViewPassbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    Date date1,date2,currDate;
                    SimpleDateFormat dates = new SimpleDateFormat("dd/MM/yyyy");
                    strDateTime = dates.format(new Date()).toString();
                    date1 = dates.parse(txtDate1.getText().toString().trim());
                    date2 = dates.parse(txtDate2.getText().toString().trim());
                    currDate = dates.parse(strDateTime);

                    long difference = date2.getTime() - date1.getTime();
                    long differenceDates = difference / (24 * 60 * 60 * 1000);

                    currDifference1 = (currDate.getTime() - date1.getTime())/(24 * 60 * 60 * 1000);

                    currDifference2 = (currDate.getTime() - date2.getTime())/(24 * 60 * 60 * 1000);

                    strDateDifference = Long.toString(differenceDates);
                    intdiffDate = Integer.parseInt(strDateDifference);
                    //Toast.makeText(ViewPassbook.this, String.valueOf(currDifference1), Toast.LENGTH_SHORT).show();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (currDifference1 >= 0 && currDifference2 >= 0 && intdiffDate > 0) {
                    if (!strAccountNo.equalsIgnoreCase("")) {
                        SharedPreferences preferences = getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("Date1", txtDate1.getText().toString().trim());
                        editor.putString("Date2", txtDate2.getText().toString().trim());
                        editor.putString("AccNumber", strAccountNo);
                        editor.apply();
                        Intent intent = new Intent(ViewPassbook.this, AccDetails.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(ViewPassbook.this, "Please Select Any Account", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(ViewPassbook.this, "Please put the From and To Dates Correctly", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(ViewPassbook.this,DashboardActivity.class);
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
                Intent intent = new Intent(ViewPassbook.this,DashboardActivity.class);
                startActivity(intent);
                break;
            case R.id.alert:
                Intent intent1 = new Intent(ViewPassbook.this,ROIAlertActivity.class);
                startActivity(intent1);
                return true;

            case R.id.action_logout:
                Intent intent2 = new Intent(ViewPassbook.this,LoginActivity.class);
                startActivity(intent2);
                break;

        }

        return super.onOptionsItemSelected(item);

    }
}
