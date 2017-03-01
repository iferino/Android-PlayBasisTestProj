package com.iferino.playbasistestproj.ScreenSecond;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.iferino.playbasistestproj.R;
public class WebViewFragment extends Fragment {
public WebViewFragment() {
	// Required empty public constructor
}


@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState)
{
	View view = inflater.inflate(R.layout.fragment_webview, container, false);
	WebView webView = (WebView) view.findViewById(R.id.webView);

	String url = getArguments().getString("url");
	webView.loadUrl(url);

	// Enable Javascript
	WebSettings webSettings = webView.getSettings();
	webSettings.setJavaScriptEnabled(true);

	// Force links and redirects to open in the WebView instead of in a browser
	webView.setWebViewClient(new WebViewClient());


	return view;
}
}
