package com.gao.fragment;

import java.util.ArrayList;

import com.gao.multiappv3.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ItemFragment extends Fragment
{
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.fragment_item, container,false);
		ListView listView = (ListView)view.findViewById(R.id.listView);
		//获取Activity传递过来的参数
		Bundle b = getArguments();
		String title = b.getString("value");
		System.out.println("title-ItemFragment->"+title);
		ArrayList<String> list = new ArrayList<String>();
		for(int i=0;i<5;i++)
		{
			list.add(title+i);
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),R.layout.item , list);
		listView.setAdapter(adapter);
		return view;
		
	}
}
