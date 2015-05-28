package com.gao.myyitu;

import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.gao.application.ExitApplication;
import com.gao.fragment.AboutFragment;
import com.gao.fragment.SettingFragment;

public class AboutActivity extends SherlockFragmentActivity
{
	private FragmentManager fm;
	private FragmentTransaction ft;
	private ActionBar actionBar;
	private long firstTime = 0;
	@Override
	protected void onCreate(Bundle arg0)
	{
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		ExitApplication.getInstanse().addActivity(this);
		actionBar = getSupportActionBar();
		actionBar.setTitle("关于");
		actionBar.setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_main);
		fm = getSupportFragmentManager();
		ft = fm.beginTransaction();
		ft.replace(R.id.main_lin, new AboutFragment());
		ft.commit();
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
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// TODO Auto-generated method stub
		switch (item.getItemId())
		{
		case android.R.id.home:
			Intent intent  = new Intent(AboutActivity.this,MainActivity.class);
			startActivity(intent);
			return true;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
