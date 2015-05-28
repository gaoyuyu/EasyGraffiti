package com.gao.myyitu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.gao.application.ExitApplication;
import com.gao.data.GlobleData;

public class InternetActivity extends SherlockFragmentActivity implements OnClickListener
{
	private FragmentManager fm;
	private FragmentTransaction ft;
	private ActionBar actionBar;
	private static Boolean isExit = false; // 按两次返回键退出
	private static Boolean hasTask = false;
	private ImageView ic_wifi;
	private TextView internet_txt;
	private long firstTime = 0;
	@Override
	protected void onCreate(Bundle arg0)
	{
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		ExitApplication.getInstanse().addActivity(this);
		actionBar = getSupportActionBar();
		
		setContentView(R.layout.internet_error);
		ic_wifi = (ImageView)findViewById(R.id.ic_wifi);
		internet_txt = (TextView)findViewById(R.id.internet_txt);
		setListener();
	}
	private void setListener()
	{
		// TODO Auto-generated method stub
		ic_wifi.setOnClickListener(this);
		internet_txt.setOnClickListener(this);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			if ((System.currentTimeMillis() - firstTime) > 2000)
			{// 如果两次按键时间间隔大于2000毫秒，则不退出
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				firstTime = System.currentTimeMillis();// 更新mExitTime
			} else
			{
//				Intent intent = new Intent(Intent.ACTION_MAIN);
//				intent.addCategory(Intent.CATEGORY_HOME);
//				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//				startActivity(intent);
				ExitApplication.getInstanse().exit();
				android.os.Process.killProcess(android.os.Process.myPid());
			}

			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		switch (v.getId())
		{
		case R.id.ic_wifi:
			if (GlobleData.hasInternet(InternetActivity.this))
			{
				Intent intent = new Intent(InternetActivity.this,
						MainActivity.class);
				startActivity(intent);
			} else
			{
				Toast.makeText(InternetActivity.this, "亲，网络还是不给力哦~",
						Toast.LENGTH_SHORT).show();
			}
			break;
		case R.id.internet_txt:
			if (GlobleData.hasInternet(InternetActivity.this))
			{
				Intent intent = new Intent(InternetActivity.this,
						MainActivity.class);
				startActivity(intent);
			} else
			{
				Toast.makeText(InternetActivity.this, "亲，网络还是不给力哦~",
						Toast.LENGTH_SHORT).show();
			}
			break;
		default:
			break;
		}
	}
}
