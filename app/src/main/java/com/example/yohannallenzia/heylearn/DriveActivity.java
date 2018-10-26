package com.example.yohannallenzia.heylearn;

import android.app.DownloadManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.DownloadListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class DriveActivity extends AppCompatActivity {

    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_drive );

        //Logo
        ActionBar actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.logo_white);

        webView = (WebView) findViewById( R.id.webView );

        //Redirect to google slides folder
        webView.loadUrl( "https://drive.google.com/drive/folders/1HcM6X8UsGdzkSxSMH8ZpLS-h6dhYAp-k?usp=sharing" );
        webView.setWebViewClient( new client() );

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled( true );
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically( true );
        webView.clearCache( true );
        webView.clearHistory();

        webView.setDownloadListener( new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String s1, String s2, String s3, long l) {
                DownloadManager.Request request = new DownloadManager.Request( Uri.parse(url) );
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);

                DownloadManager dm = (DownloadManager) getSystemService( DOWNLOAD_SERVICE );
                dm.enqueue( request );
            }
        } );
    }

    private class client extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted( view, url, favicon );
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl( url );
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished( view, url );
        }
    }
}
