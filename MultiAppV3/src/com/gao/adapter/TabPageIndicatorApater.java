package com.gao.adapter;

import com.gao.fragment.ItemFragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class TabPageIndicatorApater extends FragmentPagerAdapter
{
	private String[] TITLE;
	public TabPageIndicatorApater(FragmentManager fm,String[] TITLE)
	{
		super(fm);
		this.TITLE = TITLE;
	}

	@Override
	public Fragment getItem(int position)
	{
		//新建一个Fragment来展示viewpager item的内容，并传递参数
		Fragment fragment = new ItemFragment();
		Bundle b = new Bundle();
		b.putString("value", TITLE[position]);
		fragment.setArguments(b);
		System.out.println("-------TabPageIndicatorApater-----------"+TITLE[position]);
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
