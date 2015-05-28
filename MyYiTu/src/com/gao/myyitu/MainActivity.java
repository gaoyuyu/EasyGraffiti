package com.gao.myyitu;

import java.lang.reflect.Field;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.ViewConfiguration;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.gao.application.ExitApplication;
import com.gao.fragment.MainFragment;
import com.gao.fragment.SlidingMeunFragment;
import com.gao.utils.ScreenUtil;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

public class MainActivity extends SlidingFragmentActivity
{
	private ActionBar actionBar;
	private FragmentManager fm;
	private FragmentTransaction ft;
	private long firstTime = 0;
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ExitApplication.getInstanse().addActivity(this);

		setContentView(R.layout.activity_main);
		DisplayMetrics metric = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metric);
		ScreenUtil.setScreenHeight(metric.heightPixels);
		ScreenUtil.setScreenWidth(metric.widthPixels);
		setOverflowShowingAlways();
		actionBar = getSupportActionBar();
		fm = getSupportFragmentManager();
		actionBar.setTitle("易涂");
		setSlidingActionBarEnabled(false);//设置sldingmeun滑动的时候actionbar不动
		initSlidingMenu();
		
		
		//加载MainFragemnt（带viewpager）
		MainFragment mainFragment  = new MainFragment(fm);
		ft = fm.beginTransaction();
		ft.replace(R.id.main_lin, mainFragment);
		ft.commit();
	}
	private void initSlidingMenu()
	{
		// TODO Auto-generated method stub
		@SuppressWarnings("unused")
		SlidingMenu slidingMenu = getSlidingMenu();
		setBehindContentView(R.layout.content_frame);
		
		SlidingMeunFragment slidingMeunFragment = new SlidingMeunFragment(fm, slidingMenu);
		ft = fm.beginTransaction();
		ft.replace(R.id.content_frame, slidingMeunFragment);
		ft.commit();
		//设置SlidingMenu的属性
		slidingMenu.setMode(SlidingMenu.LEFT);// 设置slidingmeni从哪侧出现
		slidingMenu.setTouchModeAbove(SlidingMenu.LEFT);// 只有在边上才可以打开
		slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);// 偏移量
		slidingMenu.setFadeEnabled(true);
		slidingMenu.setFadeDegree(0.5f);
		slidingMenu.setMenu(R.layout.content_frame);
		
	}
	
	private void setOverflowShowingAlways()
	{
		try
		{
			ViewConfiguration config = ViewConfiguration.get(this);
			Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
			menuKeyField.setAccessible(true);
			menuKeyField.setBoolean(config,false);
		} catch (IllegalArgumentException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchFieldException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
//						Intent intent = new Intent(Intent.ACTION_MAIN);
//						intent.addCategory(Intent.CATEGORY_HOME);
//						intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//						startActivity(intent);
						ExitApplication.getInstanse().exit();
						android.os.Process.killProcess(android.os.Process.myPid());
					}

					return true;
				}
				return super.onKeyDown(keyCode, event);
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// TODO Auto-generated method stub
		getSupportMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// TODO Auto-generated method stub
		if(R.id.action_publish == item.getItemId())
		{
			Intent intent  = new Intent();
			intent.setClass(MainActivity.this, PublishActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
