package com.iferino.playbasistestproj.ScreenFirst;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.iferino.playbasistestproj.R;

public class HomeScreenFragment extends Fragment {
@Override
public View onCreateView(LayoutInflater inflater,
                         @Nullable ViewGroup container,
                         @Nullable Bundle savedInstanceState) {

	View view = inflater.inflate(R.layout.fragment_homescreen, container, false);

	TextView text = (TextView)view.findViewById(R.id.text);
	//scroll textview
	text.setMovementMethod(new ScrollingMovementMethod());


	return view;
}
}
