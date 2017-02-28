package com.iferino.playbasistestproj.ScreenThird;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iferino.playbasistestproj.R;
import com.squareup.picasso.Picasso;

public class PhotoFragment extends Fragment {

public PhotoFragment() {
	// Required empty public constructor
}


@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState)
{
	View view = inflater.inflate(R.layout.fragment_photo, container, false);

	ImageView imageView = (ImageView)view.findViewById(R.id.imageView);
	String url = getArguments().getString("url");
	Picasso.with(getContext()).load(url).placeholder(R.drawable.ic_launcher)
	       .into(imageView);
	TextView textView = (TextView)view.findViewById(R.id.textView);
	textView.setText(getArguments().getString("text"));

	return view;
}


}
