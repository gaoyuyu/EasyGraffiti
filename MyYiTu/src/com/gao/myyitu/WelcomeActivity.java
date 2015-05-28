package com.gao.myyitu;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.ImageView;

import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.gao.application.ExitApplication;
import com.gao.data.GlobleData;

public class WelcomeActivity extends SherlockFragmentActivity
{
	private static final long DELAY_TIME = 2000L;
	private ImageView welcome_img;
	private  SharedPreferences sharedPreferences;
	private FragmentManager fm;
	private FragmentTransaction ft;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		ExitApplication.getInstanse().addActivity(this);
		welcome_img = (ImageView)findViewById(R.id.welcome);
		AlphaAnimation mAlphaAnimation = new AlphaAnimation(0.2f, 1.0f);
        mAlphaAnimation.setDuration(1 * 1000);
        mAlphaAnimation.setAnimationListener(new AnimationListener()
		{
			
			@Override
			public void onAnimationStart(Animation animation)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation)
			{
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation)
			{
				// TODO Auto-generated method stub
//				sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
//				String email = sharedPreferences.getString("email", "");
//				if(email == null || email.equals(""))
//				{
//					redirectActivity(LoginActivty.class);
//				}
//				else
//				{
//					redirectActivity(MainActivity.class);
//				}
				if(GlobleData.hasInternet(WelcomeActivity.this))
				{
					redirectActivity(MainActivity.class);
				}
				else
				{
					redirectActivity(InternetActivity.class);
				}
				
			}
		});
        welcome_img.setAnimation(mAlphaAnimation);
	}

	private void redirectActivity(final Class<?> TagetActivity)
	{
		new Handler().postDelayed(new Runnable()
		{
			@Override
			public void run()
			{
				startActivity(new Intent(WelcomeActivity.this,
						TagetActivity));
				finish();
			}
		}, DELAY_TIME);
	}


}
