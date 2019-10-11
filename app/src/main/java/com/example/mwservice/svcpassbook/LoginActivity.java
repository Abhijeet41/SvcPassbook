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
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class LoginActivity extends AppCompatActivity {
    String strPPIN,strRecPPIN,strOTP,strMobile;
    EditText edtPPIN,edtMobile;
    TextView txtRegister,txtForgot;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        edtPPIN = (EditText) findViewById(R.id.edtPPIN);
        edtMobile = (EditText) findViewById(R.id.edtMobile);
        txtRegister = (TextView) findViewById(R.id.txtRegister);
        txtForgot = (TextView) findViewById(R.id.txtLogin);

        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

        txtForgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,ForgotPPIN.class);
                startActivity(intent);
            }
        });

        SharedPreferences preferences  = getApplicationContext().getSharedPreferences("pref", Context.MODE_PRIVATE);
        strOTP = preferences.getString("OTP","empty");
        strRecPPIN = preferences.getString("PIN","empty");


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strPPIN = edtPPIN.getText().toString().trim();
                strMobile = edtMobile.getText().toString().trim();


                if (strPPIN.equals("123456") && strMobile.equals("8355942271")){
                    final ProgressDialog dialog = new ProgressDialog(LoginActivity.this);
                    dialog.setTitle("Logging You In...");
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
                            editor.putString("PPIN", strPPIN);
                            editor.apply();

                            Intent intent = new Intent(getApplicationContext(), DashboardActivity.class);
                            startActivity(intent);
                        }
                    }, delayInMillis);
                }else {
                    Toast.makeText(LoginActivity.this, "You are entering wrong P-PIN", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
