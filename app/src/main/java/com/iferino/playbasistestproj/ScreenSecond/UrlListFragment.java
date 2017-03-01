package com.iferino.playbasistestproj.ScreenSecond;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;

import com.iferino.playbasistestproj.R;
import com.iferino.playbasistestproj.ScreenSecond.Tools.OnSwipeTouchListener;

public class UrlListFragment extends Fragment {

OnUrlSelectedListener callBack;

public interface OnUrlSelectedListener{
	public void onUrlSelected (String url);
}

@Override public void onAttach(Context context) {
	super.onAttach(context);
	try {
		callBack = (OnUrlSelectedListener) context;
	}catch (ClassCastException e){
		throw new ClassCastException(context.toString()+"must implement onImageSelectedListener");
	}
}

@Override
public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

	View view = inflater.inflate(R.layout.fragment_urllist, container, false);

	TableRow row1 = (TableRow)view.findViewById(R.id.row1);
	TableRow row2 = (TableRow)view.findViewById(R.id.row2);
	TableRow row3 = (TableRow)view.findViewById(R.id.row3);

	row1.setOnTouchListener(new OnSwipeTouchListener(getActivity()){
		@Override
		public void onSwipeLeft() {
			callBack.onUrlSelected("http://bitbucket.com");
		}
	});
	row2.setOnTouchListener(new OnSwipeTouchListener(getActivity()){
		@Override
		public void onSwipeLeft() {
			callBack.onUrlSelected("http://github.com");
		}
	});
	row3.setOnTouchListener(new OnSwipeTouchListener(getActivity()){
		@Override
		public void onSwipeLeft() {
			callBack.onUrlSelected("http://google.com");
		}
	});

	return view;
}

}
