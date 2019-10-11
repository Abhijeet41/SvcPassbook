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

public class SetPINActivity extends AppCompatActivity {
    String strMobile,strOTP,strPIN,strCnfrmPIN;
    EditText edtPIN,edtCnfrmPIN;
    Button btnActivate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_pin);

        btnActivate = (Button) findViewById(R.id.btnActivate);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            strMobile = extras.getString("Mobile");
        }

        edtPIN = (EditText) findViewById(R.id.edtPIN);
        edtCnfrmPIN = (EditText) findViewById(R.id.edtConfirmPIN);

        btnActivate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strPIN = edtPIN.getText().toString().trim();
                strCnfrmPIN = edtCnfrmPIN.getText().toString().trim();

                if (strPIN.length() == 6 && strCnfrmPIN.length() == 6) {

                    if (strPIN.equals(strCnfrmPIN)){
                        final ProgressDialog dialog = new ProgressDialog(SetPINActivity.this);
                        dialog.setTitle("Completing PIN Set...");
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
                                editor.putString("PIN",strPIN);
                                editor.apply();

                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            }
                        }, delayInMillis);
                    }else {
                        Toast.makeText(SetPINActivity.this, "Your PINs do not match", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(SetPINActivity.this, "Please enter the m-PIN correctly", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
