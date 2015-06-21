package com.gao.fragment;

import com.gao.adapter.TabPageIndicatorApater;
import com.gao.multiappv3.R;
import com.viewpagerindicator.TabPageIndicator;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainFragment extends Fragment
{
	private View rootView;
	private FragmentManager fm;
	private TabPageIndicator indicator ;
	private static final String[] TITLE ={ "聊天", "发现", "通讯录" };
	public MainFragment(FragmentManager fm)
	{
		this.fm = fm;
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		if(null == rootView)
		{
			rootView = inflater.inflate(R.layout.mainfragment, null);
		}
		FragmentPagerAdapter adapter = new TabPageIndicatorApater(getChildFragmentManager(),TITLE);
		ViewPager pager = (ViewPager)rootView.findViewById(R.id.pager);
		pager.setAdapter(adapter);
		//实例化TabPageIndicator然后设置ViewPager与之关联
		indicator= (TabPageIndicator)rootView.findViewById(R.id.indicator);
		indicator.setViewPager(pager);
		return rootView;
		
	}
}
