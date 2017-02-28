package com.iferino.playbasistestproj.ScreenSecond;

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
@Override
public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {

	View view = inflater.inflate(R.layout.fragment_urllist, container, false);

	TableRow row1 = (TableRow)view.findViewById(R.id.row1);
	TableRow row2 = (TableRow)view.findViewById(R.id.row2);
	TableRow row3 = (TableRow)view.findViewById(R.id.row3);

	final Intent intent = new Intent(getActivity(), WebViewFragment.class);
	row1.setOnTouchListener(new OnSwipeTouchListener(getActivity()){
		@Override
		public void onSwipeLeft() {
			intent.putExtra("url", "http://bitbucket.com");
			startActivity(intent);
		}
	});
	row2.setOnTouchListener(new OnSwipeTouchListener(getActivity()){
		@Override
		public void onSwipeLeft() {
			intent.putExtra("url","http://github.com");
			startActivity(intent);
		}
	});
	row3.setOnTouchListener(new OnSwipeTouchListener(getActivity()){
		@Override
		public void onSwipeLeft() {
			intent.putExtra("url","http://google.com");
			startActivity(intent);
		}
	});



	return view;
}

}
