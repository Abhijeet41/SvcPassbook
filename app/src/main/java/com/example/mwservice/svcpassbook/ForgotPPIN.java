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

public class ForgotPPIN extends AppCompatActivity {
    String strMobile;
    EditText edtMobile;
    Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_ppin);

        edtMobile = (EditText) findViewById(R.id.edtMobile);
        btnSubmit = (Button) findViewById(R.id.btnLogin);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strMobile = edtMobile.getText().toString().trim();

                if (strMobile.length() == 10){
                    final ProgressDialog dialog = new ProgressDialog(ForgotPPIN.this);
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
                            //editor.putString("PPIN", strPPIN);
                            editor.apply();

                            Intent intent = new Intent(getApplicationContext(), OTPActivity.class);
                            startActivity(intent);

                            // sendsms("Dear "+strFname+" "+strLname+" your Request for Registering Your Aadhaar Number To Our e-KYC app Has Been Accepted. You'll Notified for further proceedings.");
                        }
                    }, delayInMillis);
                }else {
                    Toast.makeText(ForgotPPIN.this, "Please check the Mobile Number Again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
