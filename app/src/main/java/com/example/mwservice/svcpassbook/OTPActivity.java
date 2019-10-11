package com.example.mwservice.svcpassbook;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class OTPActivity extends AppCompatActivity {
    String strOTP,strMobile,strOTPRequest,strICCID;
    ProgressDialog progressDialog;
    EditText edtOTP;
    Button btnNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            strMobile = extras.getString("Mobile");
        }

        edtOTP = (EditText) findViewById(R.id.edtOTP);
        btnNext = (Button) findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strOTP = edtOTP.getText().toString().trim();

                if (strOTP.equals("1234")) {

                    final ProgressDialog dialog = new ProgressDialog(OTPActivity.this);
                    dialog.setTitle("Processing...");
                    dialog.setMessage("Please wait.");
                    dialog.setIndeterminate(true);
                    dialog.setCancelable(false);
                    dialog.show();

                    long delayInMillis = 3000;
                    Timer timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            dialog.dismiss();

                            SharedPreferences preferences = getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("OTP", strOTP);
                            editor.apply();

                            Intent intent = new Intent(getApplicationContext(), SetPINActivity.class);
                            startActivity(intent);

                            // sendsms("Dear "+strFname+" "+strLname+" your Request for Registering Your Aadhaar Number To Our e-KYC app Has Been Accepted. You'll Notified for further proceedings.");
                        }
                    }, delayInMillis);
                }
                else {
                    Toast.makeText(OTPActivity.this, "Please enter correct OTP", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
