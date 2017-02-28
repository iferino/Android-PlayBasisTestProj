package com.iferino.playbasistestproj.ScreenThird;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iferino.playbasistestproj.R;
import com.squareup.picasso.Picasso;

public class PhotoActivity extends AppCompatActivity {

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_photo);

	LinearLayout backIcon = (LinearLayout)findViewById(R.id.back_image);
	backIcon.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {
			finish();
		}
	});
	TextView tv= (TextView) findViewById(R.id.toolbar_title);
	tv.setText("Photo");


	ImageView imageView = (ImageView)findViewById(R.id.imageView);
	String url = getIntent().getExtras().getString("url");
	Picasso.with(getApplicationContext()).load(url).placeholder(R.drawable.ic_launcher)
	       .into(imageView);
	TextView textView = (TextView)findViewById(R.id.textView);
	textView.setText(getIntent().getExtras().getString("text"));




}


}
