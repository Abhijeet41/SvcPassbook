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

public class RegisterActivity extends AppCompatActivity {
    String strMobile,strRegisterRequest,strICCID;
    TextView txtLogin;
    Button btnNext;
    ProgressDialog progressDialog;
    EditText edtMobile;
    TextView txtHead;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txtLogin = (TextView) findViewById(R.id.txtLogin);
        btnNext = (Button) findViewById(R.id.btnNext);
        edtMobile = (EditText) findViewById(R.id.edtMobile);

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strMobile = edtMobile.getText().toString().trim();
                
                if (strMobile.length() == 10){
                    final ProgressDialog dialog = new ProgressDialog(RegisterActivity.this);
                    dialog.setTitle("Registering You...");
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
                            editor.putString("Mobile",strMobile);
                            editor.apply();

                            Intent intent = new Intent(getApplicationContext(),OTPActivity.class);
                            startActivity(intent);

                            // sendsms("Dear "+strFname+" "+strLname+" your Request for Registering Your Aadhaar Number To Our e-KYC app Has Been Accepted. You'll Notified for further proceedings.");
                        }
                    }, delayInMillis);

                }else {
                    Toast.makeText(RegisterActivity.this, "Please enter the 10 digit mobile number correctly", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
