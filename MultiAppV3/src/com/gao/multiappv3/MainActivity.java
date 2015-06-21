package com.gao.multiappv3;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Window;
import com.gao.fragment.ItemFragment;
import com.gao.fragment.MainFragment;
import com.gao.fragment.MeunFragment;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.viewpagerindicator.TabPageIndicator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.ViewConfiguration;

public class MainActivity extends SlidingFragmentActivity
{
	private TabPageIndicator  indicator;
	private static final String[] TITLE ={ "聊天", "发现", "通讯录" };
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setOverflowShowingAlways();
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		setSlidingActionBarEnabled(false);//设置sldingmeun滑动的时候actionbar不动
		initSlidingMenu();
		getSupportFragmentManager().beginTransaction().replace(R.id.rel, new MainFragment(getSupportFragmentManager())).commit();
		
		
		
		
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		case android.R.id.home:
			// toggle就是程序自动判断是打开还是关闭
			toggle();
			// getSlidingMenu().showMenu();// show menu
			// getSlidingMenu().showContent();//show content
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
//	public boolean onMenuOpened(int featureId, Menu menu)
//	{
//		if(featureId == Window.FEATURE_ACTION_BAR && menu!=null)
//		{
//			if(menu.getClass().getSimpleName().equals("MenuBuilder"))
//			{
//				try
//				{
//					Log.v("MainActivity","open");
//					Method m = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
//					m.setAccessible(true);
//					m.invoke(menu,true);
//				} catch (Exception e)
//				{
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		}
//		return true;
//	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getSupportMenuInflater().inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
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
	private void initSlidingMenu()
	{
		// TODO Auto-generated method stub
		SlidingMenu mSlidingMenu = getSlidingMenu();
		setBehindContentView(R.layout.content_frame);
		MeunFragment menuFragment = new MeunFragment(getSupportFragmentManager(),mSlidingMenu);
		
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction transaction = fm.beginTransaction();
		transaction.replace(R.id.content_frame, menuFragment);
		transaction.commit();
		// 设置slidingmenu的属性
		mSlidingMenu.setMode(SlidingMenu.LEFT);// 设置slidingmeni从哪侧出现
		mSlidingMenu.setTouchModeAbove(SlidingMenu.LEFT);// 只有在边上才可以打开
		mSlidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);// 偏移量
		mSlidingMenu.setFadeEnabled(true);
		mSlidingMenu.setFadeDegree(0.5f);
		mSlidingMenu.setMenu(R.layout.content_frame);
	}
	/**
	 * Viewpager 适配器
	 */
	class TabPageIndicatorApater extends FragmentPagerAdapter
	{

		public TabPageIndicatorApater(FragmentManager fm)
		{
			super(fm);
		}

		@Override
		public Fragment getItem(int position)
		{
			//新建一个Fragment来展示viewpager item的内容，并传递参数
			Fragment fragment = new ItemFragment();
			Bundle b = new Bundle();
			b.putString("value", TITLE[position]);
			fragment.setArguments(b);
			return fragment;
		}

		@Override
		public CharSequence getPageTitle(int position)
		{
			// TODO Auto-generated method stub
			return TITLE[position%TITLE.length];
		}
		
		@Override
		public int getCount()
		{
			// TODO Auto-generated method stub
			return TITLE.length;
		}
		
	}
}
