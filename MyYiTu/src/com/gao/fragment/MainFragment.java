package com.gao.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.app.SherlockFragment;
import com.gao.adapter.TabPageIndicatorApater;
import com.gao.myyitu.R;
import com.viewpagerindicator.TabPageIndicator;

public class MainFragment extends SherlockFragment
{
	private static final String[] TITLE ={ "涂墙","每周Top10" };
	private View rootView;
	private FragmentManager fm;
	private TabPageIndicator indicator ;
	
	public MainFragment()
	{
		
	}
	public MainFragment(FragmentManager fm)
	{
		this.fm = fm;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		if(null == rootView)
		{
			rootView = inflater.inflate(R.layout.main_fragment, null);
		}
		FragmentPagerAdapter adapter = new TabPageIndicatorApater(getChildFragmentManager(), TITLE);
		ViewPager pager = (ViewPager)rootView.findViewById(R.id.pager);
		pager.setAdapter(adapter);
		//实例化TabPageIndicator然后设置ViewPager与之关联
		indicator= (TabPageIndicator)rootView.findViewById(R.id.indicator);
		indicator.setViewPager(pager);
		return rootView;
	}
}
