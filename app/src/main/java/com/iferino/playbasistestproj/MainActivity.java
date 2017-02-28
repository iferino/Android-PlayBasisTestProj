package com.iferino.playbasistestproj;


import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.iferino.playbasistestproj.ScreenSecond.UrlListFragment;
import com.iferino.playbasistestproj.ScreenThird.GalleryFragment;


public class MainActivity extends AppCompatActivity {

private DrawerLayout                      drawerLayout;
private NavigationView                    navigationView;
private FragmentManager                   fragmentManager;
private android.support.v7.widget.Toolbar toolbar;
private float lastTranslate = 0.0f;

@Override
protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_main);
	drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
	navigationView = (NavigationView) findViewById(R.id.drawer);

	//drawer toggle and toolbar
	toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
	ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.toolbar_title,
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
	drawerLayout.setDrawerListener(drawerToggle);
	drawerToggle.syncState();

	//set no shadow
	drawerLayout.setScrimColor(Color.TRANSPARENT);

	//drawermenu clickListener
	navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
		@Override public boolean onNavigationItemSelected(MenuItem menuItem) {
			drawerLayout.closeDrawers();
			FragmentTransaction transaction = fragmentManager.beginTransaction();
			TextView title = (TextView)findViewById(R.id.titleToolbar);
			switch (menuItem.getItemId()){
				case R.id.menu_title:
					title.setText(R.string.menu_title);
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

	//call base fragment
	fragmentManager = getSupportFragmentManager();
	FragmentTransaction transaction = fragmentManager.beginTransaction();
	transaction.replace(R.id.container, new HomeScreenFragment()).commit();

}

@Override
public void onConfigurationChanged(Configuration newConfig) {
	super.onConfigurationChanged(newConfig);

}

}
