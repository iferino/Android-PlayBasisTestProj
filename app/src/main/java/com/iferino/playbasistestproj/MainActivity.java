package com.iferino.playbasistestproj;


import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.iferino.playbasistestproj.ScreenFirst.HomeScreenFragment;
import com.iferino.playbasistestproj.ScreenSecond.UrlListFragment;
import com.iferino.playbasistestproj.ScreenSecond.WebViewFragment;
import com.iferino.playbasistestproj.ScreenThird.GalleryFragment;
import com.iferino.playbasistestproj.ScreenThird.PhotoFragment;


public class MainActivity extends AppCompatActivity implements UrlListFragment.OnUrlSelectedListener, GalleryFragment
		                                                               .OnImageSelectedListener{

private DrawerLayout                      drawerLayout;
private ActionBarDrawerToggle drawerToggle;
private NavigationView                    navigationView;
private FragmentManager                   fragmentManager;
private android.support.v7.widget.Toolbar toolbar;
private TextView title;
private LinearLayout backIcon;
private float lastTranslate = 0.0f;

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
	navigationView = (NavigationView) findViewById(R.id.drawer);
	title = (TextView)findViewById(R.id.titleToolbar);
	backIcon = (LinearLayout)findViewById(R.id.back_image);

	//toolbar
	toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
	//set toggle icon
	drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.toolbar_title,
	                                                               R.string.toolbar_title) {
		//static hamburger icon
		@Override
		public void onDrawerOpened(View drawerView) {
			super.onDrawerOpened(drawerView);
			super.onDrawerSlide(drawerView, 0);
		}

		//shift framelayout when open drawer menu
		@Override
		public void onDrawerSlide(View drawerView, float slideOffset) {
			FrameLayout frame = (FrameLayout) findViewById(R.id.container);
			super.onDrawerSlide(drawerView, 0);
			float moveFactor = (navigationView.getWidth() * slideOffset);

			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
			{
				toolbar.setTranslationX(moveFactor);
				frame.setTranslationX(moveFactor);
			}
			else
			{
				TranslateAnimation anim = new TranslateAnimation(lastTranslate, moveFactor, 0.0f, 0.0f);
				anim.setDuration(0);
				anim.setFillAfter(true);
				toolbar.startAnimation(anim);
				frame.startAnimation(anim);

				lastTranslate = moveFactor;
			}
		}
	};
	drawerLayout.addDrawerListener(drawerToggle);
	drawerToggle.syncState();

	//set no shadow
	drawerLayout.setScrimColor(Color.TRANSPARENT);

	//call base fragment
	fragmentManager = getSupportFragmentManager();
	FragmentTransaction transaction = fragmentManager.beginTransaction();
	title.setText(R.string.toolbar_title);
	transaction.replace(R.id.container, new HomeScreenFragment()).commit();

	//drawermenu clickListener
	navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
		@Override public boolean onNavigationItemSelected(MenuItem menuItem) {
			drawerLayout.closeDrawers();
			FragmentTransaction transaction = fragmentManager.beginTransaction();
			switch (menuItem.getItemId()){
				case R.id.menu_title:
					title.setText(R.string.toolbar_title);
					transaction.replace(R.id.container, new HomeScreenFragment()).commit();
					break;
				case R.id.menu1:
					title.setText(R.string.toolbar_menu1);
					transaction.replace(R.id.container, new UrlListFragment()).commit();
					break;
				case R.id.menu2:
					title.setText(R.string.toolbar_menu2);
					transaction.replace(R.id.container, new GalleryFragment()).commit();
					break;

			}
			return false;
		}
	});
}

@Override public void onUrlSelected(String url) {
	WebViewFragment webFragment = new WebViewFragment();
	Bundle args = new Bundle();
	args.putString("url",url);
	webFragment.setArguments(args);
	setToolbarBack();
	FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
	transaction.replace(R.id.container,webFragment);
	transaction.addToBackStack(null);
	transaction.commit();
}

//select image in gallery-call fragment
@Override public void onImageSelected(String url, String text) {
	PhotoFragment photoFragment = new PhotoFragment();
	Bundle args = new Bundle();
	args.putString("url",url);
	args.putString("text", text);
	photoFragment.setArguments(args);

	setToolbarTitleBack();
	FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
	transaction.replace(R.id.container,photoFragment);
	transaction.addToBackStack(null);
	transaction.commit();
}
void setToolbarBack(){
	//disable drawer
	drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
	drawerToggle.setDrawerIndicatorEnabled(false);
	//config toolbar
	backIcon.setVisibility(View.VISIBLE);
	backIcon.setOnClickListener(new View.OnClickListener() {
		@Override public void onClick(View v) {
			setToolbarDrawer();
			getSupportFragmentManager().popBackStack();
		}
	});
	toolbar.setBackgroundColor(Color.WHITE);
}
void setToolbarTitleBack(){
	title.setText("Photo");
	title.setTextColor(Color.BLACK);
	setToolbarBack();
}
void setToolbarDrawer(){
	//enable drawer
	drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
	drawerToggle.setDrawerIndicatorEnabled(true);
	//config toolbar
	backIcon.setVisibility(View.INVISIBLE);
	title.setTextColor(Color.WHITE);
	toolbar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(), R.color.toolbar_bg));
	title.setText(R.string.toolbar_menu2);
}


@Override
public void onConfigurationChanged(Configuration newConfig) {
	super.onConfigurationChanged(newConfig);

}

}
