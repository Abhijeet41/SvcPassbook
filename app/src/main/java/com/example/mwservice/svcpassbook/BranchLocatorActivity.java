package com.example.mwservice.svcpassbook;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class BranchLocatorActivity extends AppCompatActivity {
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_locator);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.small_svc_logo_dash);
        getSupportActionBar().setTitle(" m-Passbook");
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

        webView = (WebView) findViewById(R.id.web);

        webView.setWebViewClient(new myWebClient());
        webView.getSettings().setBuiltInZoomControls(true);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setSupportMultipleWindows(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setLightTouchEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webView.loadUrl("https://www.google.co.in/search?rlz=1C1NHXL_enIN754IN757&ei=587FWsr-K4z0vAS17p2wCg&q=Svc+Bank&oq=Svc+Bank&gs_l=psy-ab.3..35i39k1l2j0l2j0i20i263k1j0l5.13664.18486.0.19013.12.11.1.0.0.0.273.1261.0j2j4.6.0....0...1c.1.64.psy-ab..5.7.1267...0i67k1j0i10i67k1j0i131k1j0i10k1j0i131i67k1.0.HWkGZ09F7pA");
    }

    public class myWebClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            super.onPageStarted(view, url, favicon);
            // findViewById(R.id.progressbar).setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            //findViewById(R.id.progressbar).setVisibility(View.GONE);
        }
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
                Intent intent = new Intent(BranchLocatorActivity.this,DashboardActivity.class);
                startActivity(intent);
                break;
            case R.id.alert:
                Intent intent1 = new Intent(BranchLocatorActivity.this,ROIAlertActivity.class);
                startActivity(intent1);
                return true;

            case R.id.action_logout:
                Intent intent2 = new Intent(BranchLocatorActivity.this,LoginActivity.class);
                startActivity(intent2);
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
