package com.gao.adapter;


import com.gao.fragment.AboutFragment;
import com.gao.fragment.ShowPublishFragment;
import com.gao.fragment.ShowWorkFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
		if(1 == position)
		{
			ShowWorkFragment showWorkFragment =new ShowWorkFragment();
			return showWorkFragment;
		}
		if(0 == position)
		{
			ShowPublishFragment showPublishFragment = new ShowPublishFragment();
			return showPublishFragment;
		}
		return null;
		
		
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
