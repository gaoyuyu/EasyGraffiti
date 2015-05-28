package com.gao.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;
import com.gao.data.GlobleData;
import com.gao.imageloaderlistener.AnimateFirstDisplayListener;
import com.gao.myyitu.AboutActivity;
import com.gao.myyitu.FeedbackActivity;
import com.gao.myyitu.LogActivty;
import com.gao.myyitu.MainActivity;
import com.gao.myyitu.PersonActivity;
import com.gao.myyitu.R;
import com.gao.myyitu.SettingActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class SlidingMeunFragment extends SherlockFragment implements OnItemClickListener, OnClickListener
{
	private MainActivity mainActivity;
	private View rootView;
	private FragmentManager fm;
	private FragmentTransaction ft;
	private SlidingMenu slidingMenu;
	private MenuAdapter adapter;
	private DisplayImageOptions options;
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	public String TITLES[] = new String[]{ "主页","个人","设置", "关于", "反馈" };
	public int IMAGES[] = new int[]{ R.drawable.ic_navi_home,R.drawable.ic_navi_user,R.drawable.ic_navi_settings, R.drawable.ic_navi_about,R.drawable.ic_navi_feedback };
	private ListView menu_list;
	private ImageView user_icon;
	private SharedPreferences sharedPreferences;
	@SuppressWarnings("deprecation")
	
	public SlidingMeunFragment(FragmentManager fm, SlidingMenu slidingMenu)
	{
		options = new DisplayImageOptions.Builder().displayer(new RoundedBitmapDisplayer(20)).showImageOnLoading(R.drawable.user_icon_default).showImageOnFail(R.drawable.user_icon_default).showImageForEmptyUri(R.drawable.user_icon_default).cacheInMemory(true).cacheOnDisc(true).displayer(new FadeInBitmapDisplayer(100)).build();
		this.fm = fm;
		this.slidingMenu = slidingMenu;
	}
	public SlidingMeunFragment()
	{
		super();
	}
	@Override
	public void onAttach(Activity activity)
	{
		mainActivity = (MainActivity)mainActivity;
		super.onAttach(activity);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		if(null == rootView)
		{  
			rootView = inflater.inflate(R.layout.sldingmeun_fragment, null);
		}
		menu_list = (ListView)rootView.findViewById(R.id.menu_list);
		user_icon = (ImageView)rootView.findViewById(R.id.user_icon);
		sharedPreferences = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		if(!(sharedPreferences.getString("userid", "").equals("") || sharedPreferences.getString("userid", "") == null))//先看看是否 登录
		{
			if(!(sharedPreferences.getString("photo", "").equals("") || sharedPreferences.getString("photo", "") == null))
			{
				ImageLoader imageLoader = ImageLoader.getInstance();
				imageLoader.displayImage(GlobleData.PERSON_HEAD_IMG+sharedPreferences.getString("photo", ""), user_icon,animateFirstListener);
			}
		}
		adapter = new MenuAdapter(TITLES, IMAGES, getActivity());
		menu_list.setAdapter(adapter);
		menu_list.setOnItemClickListener(this);
		user_icon.setOnClickListener(this);
		return rootView;
	}
	public class MenuAdapter extends BaseAdapter
	{
		public String[] TITLES;
		public int[] IMAGES;
		public LayoutInflater inflater;
		public Context context;
		private ViewHolder viewHolder;
		public int selectedposition;
		class ViewHolder
		{
			TextView text;
			ImageView img;
			RelativeLayout rel;
		}
		public MenuAdapter(String[] TITLES, int[] IMAGES, Context context)
		{
			this.TITLES = TITLES;
			this.IMAGES = IMAGES;
			this.context = context;
			this.inflater = LayoutInflater.from(context);
		}
		public int getSelectedposition()
		{
			return selectedposition;
		}


		public void setSelectedposition(int selectedposition)
		{
			this.selectedposition = selectedposition;
		}
		@Override
		public int getCount()
		{
			// TODO Auto-generated method stub
			return TITLES.length;
		}

		@Override
		public Object getItem(int position)
		{
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position)
		{
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent)
		{
			// TODO Auto-generated method stub
			if(convertView == null)
			{
				convertView = inflater.inflate(R.layout.sldingmenu_item, null);
				viewHolder = new ViewHolder();
				viewHolder.rel = (RelativeLayout)convertView.findViewById(R.id.navi_tab);
				viewHolder.img=(ImageView)convertView.findViewById(R.id.navi_icon);
				viewHolder.text = (TextView)convertView.findViewById(R.id.navi_title);
				convertView.setTag(viewHolder);
			}
			else
			{
				viewHolder = (ViewHolder) convertView.getTag();
			}
			viewHolder.text.setText(TITLES[position]);
			viewHolder.img.setImageDrawable(context.getResources().getDrawable(IMAGES[position]));
			if(position == selectedposition)
			{
				viewHolder.rel.setActivated(true);
				viewHolder.rel.setBackground(context.getResources().getDrawable(R.drawable.left_menu_list_selector));
			}
			return convertView;
		}
		
	}


	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int position, long id)
	{
		// TODO Auto-generated method stub
		if(TITLES[position].equals("主页"))
		{
			MainFragment mainFragment = new MainFragment(fm);
			ft = fm.beginTransaction();
			ft.replace(R.id.main_lin, mainFragment);
			ft.commit();
			slidingMenu.showContent();
		}
		if(TITLES[position].equals("个人"))
		{
			if(sharedPreferences.getString("userid", "").equals(""))
			{
				Intent intent = new Intent();
				intent.setClass(getActivity(), LogActivty.class);
				startActivity(intent);
			}
			else
			{
				Intent intent = new Intent();
				intent.putExtra("statekey", "personal");
				intent.setClass(getActivity(), PersonActivity.class);
				startActivity(intent);
				
			}
			slidingMenu.showContent();
		}
		if(TITLES[position].equals("关于"))
		{
			Intent intent = new Intent();
			intent.setClass(getActivity(), AboutActivity.class);
			startActivity(intent);
			slidingMenu.showContent();
		}
		if(TITLES[position].equals("反馈"))
		{
			Intent intent = new Intent();
			intent.setClass(getActivity(), FeedbackActivity.class);
			startActivity(intent);
			slidingMenu.showContent();
		}
		if(TITLES[position].equals("设置"))
		{
			Intent intent = new Intent();
			intent.setClass(getActivity(), SettingActivity.class);
			startActivity(intent);
			slidingMenu.showContent();
		}

	}
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.user_icon:
			if(!(sharedPreferences.getString("userid", "").equals("") ||sharedPreferences.getString("userid", "") == null))
			{
				
			}
			else
			{
				Intent intent = new Intent();
				intent.setClass(getActivity(), LogActivty.class);
				startActivity(intent);
				slidingMenu.showContent();
			}
			break;

		default:
			break;
		}
	}
}
