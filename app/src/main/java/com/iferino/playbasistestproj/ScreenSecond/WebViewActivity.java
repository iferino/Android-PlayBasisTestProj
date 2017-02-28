package com.iferino.playbasistestproj.ScreenSecond;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.iferino.playbasistestproj.R;

public class WebViewActivity extends AppCompatActivity {

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_webview);
	Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
	toolbar.setTitle("Back");
	toolbar.setNavigationIcon(R.drawable.back);
	toolbar.setNavigationOnClickListener(new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			finish();
		}
	});


	WebView webView = (WebView) findViewById(R.id.webView);

	String url = getIntent().getStringExtra("url");
	webView.loadUrl(url);

	// Enable Javascript
	WebSettings webSettings = webView.getSettings();
	webSettings.setJavaScriptEnabled(true);

	// Force links and redirects to open in the WebView instead of in a browser
	webView.setWebViewClient(new WebViewClient());


}

}
