package com.gao.myyitu;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.gao.adapter.ShowPublishAdapter;
import com.gao.application.ExitApplication;
import com.gao.data.GlobleData;
import com.gao.http.HttpUtils;
import com.gao.imageloaderlistener.AnimateFirstDisplayListener;
import com.gao.utils.ParseData;
import com.gao.utils.ProgressDialogUtils;
import com.gao.view.CustomeProgressDialog;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class PersonActivity extends SherlockFragmentActivity implements OnClickListener, OnItemClickListener
{
	private ActionBar actionBar;
	private ImageView personal_icon,go_settings;
	private TextView personal_name,personl_signature;
	private ListView personal_publish_list;
	private SharedPreferences sharedPreferences;
	private DisplayImageOptions options;
	private ImageLoader imageLoader;
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	private CustomeProgressDialog customeProgressDialog;
	private List<Map<String,String>> peronal_list;
	private LinkedList<Map<String,String>> personal_linklist;
	private static final String TAG="PersonActivity";
	private ShowPublishAdapter showPublishAdapter;
	private Intent intent;
	private long firstTime=0;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ExitApplication.getInstanse().addActivity(this);
		actionBar = getSupportActionBar();
		actionBar.setTitle("个人中心");
		actionBar.setDisplayHomeAsUpEnabled(true);
		imageLoader = ImageLoader.getInstance();
		customeProgressDialog = CustomeProgressDialog.ctreateDialog(this);		
		sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		intent = this.getIntent();
		options = new DisplayImageOptions.Builder().displayer(new RoundedBitmapDisplayer(20)).showImageOnLoading(R.drawable.user_icon_default).showImageOnFail(R.drawable.user_icon_default).showImageForEmptyUri(R.drawable.user_icon_default).cacheInMemory(true).cacheOnDisc(true).displayer(new FadeInBitmapDisplayer(0)).build();
		setContentView(R.layout.person);
		initView();
		setPersonalData();
		setListener();
		if(intent.getStringExtra("statekey").equals("personal"))
		{
			new GetPersonWork().execute(sharedPreferences.getString("userid", ""));
		}
		else 	if(intent.getStringExtra("statekey").equals("other"))
		{
			new GetPersonWork().execute(intent.getStringExtra("s_userid"));
		}
		
		
	}
	
	
	private void setListener()
	{
		// TODO Auto-generated method stub
		go_settings.setOnClickListener(this);
		personal_publish_list.setOnItemClickListener(this);
		
	}


	private void setPersonalData()
	{
		// TODO Auto-generated method stub
        if(intent.getStringExtra("statekey").equals("other"))
		{
			/**
			 * Intent intent = new Intent();
				intent.putExtra("statekey", "other");
				intent.putExtra("s_userid", publish_list.get(position).get("s_userid"));
				intent.putExtra("s_user_photo", publish_list.get(position).get("s_user_photo"));
				intent.putExtra("s_username", publish_list.get(position).get("s_username"));
				intent.putExtra("s_mark", publish_list.get(position).get("s_mark"));
				intent.setClass(context, PersonActivity.class);
			 */
			Log.i(TAG, "--------------mark---2----------"+intent.getStringExtra("statekey"));
			imageLoader.displayImage(GlobleData.PERSON_HEAD_IMG+intent.getStringExtra("s_user_photo"), personal_icon,options,animateFirstListener);
			personal_name.setText(intent.getStringExtra("s_username"));
			personl_signature.setText(intent.getStringExtra("s_mark"));
			if(!sharedPreferences.getString("userid", "").equals(intent.getStringExtra("s_userid")))
			{
				go_settings.setVisibility(View.INVISIBLE);
			}
		}
        else if(intent.getStringExtra("statekey").equals("personal"))
        {
			Log.i(TAG, "--------------mark---1----------"+intent.getStringExtra("statekey"));
			imageLoader.displayImage(GlobleData.PERSON_HEAD_IMG+sharedPreferences.getString("photo", ""), personal_icon,options,animateFirstListener);
			personal_name.setText(sharedPreferences.getString("username", ""));
			personl_signature.setText(sharedPreferences.getString("mark", ""));
        }

	}

	
	private void initView()
	{
		personal_icon = (ImageView)findViewById(R.id.personal_icon);
		go_settings = (ImageView)findViewById(R.id.go_settings);
		personal_name = (TextView)findViewById(R.id.personl_name);
		personl_signature =(TextView)findViewById(R.id.personl_signature);
		personal_publish_list = (ListView)findViewById(R.id.pull_refresh_list_personal);
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
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		case android.R.id.home:
//			Intent upIntent = NavUtils.getParentActivityIntent(this);
//			if (NavUtils.shouldUpRecreateTask(this, upIntent))
//			{
//				TaskStackBuilder.create(this)
//						.addNextIntentWithParentStack(upIntent)
//						.startActivities();
//			} else
//			{
//				upIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//				NavUtils.navigateUpTo(this, upIntent);
//			}
			Intent intent  = new Intent(PersonActivity.this,MainActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	class DelSinglePersonWork extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute()
		{
			// TODO Auto-generated method stub
			ProgressDialogUtils.startProgressDialog("加载中....", customeProgressDialog);
			super.onPreExecute();
		}
		@Override
		protected String doInBackground(String... params)
		{
			// TODO Auto-generated method stub
			HashMap< String, String> map = new HashMap<String, String>();
			map.put("s_worksid", params[0]);
			String person_content = HttpUtils.getServletContentByGet(GlobleData.DEL_WORK_URL, map, "UTF-8");
			return person_content;
		}
		@Override
		protected void onPostExecute(String result)
		{
			// TODO Auto-generated method stub
			ProgressDialogUtils.stopProgressDialog(customeProgressDialog);
			String mark = result.substring(0, 1);
			if(mark.equals("1"))
			{
				Toast.makeText(PersonActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
			}
			super.onPostExecute(result);
		}
	}
	class GetPersonWork extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute()
		{
			// TODO Auto-generated method stub
			ProgressDialogUtils.startProgressDialog("加载中....", customeProgressDialog);
			super.onPreExecute();
		}
		@Override
		protected String doInBackground(String... params)
		{
			// TODO Auto-generated method stub
			HashMap< String, String> map = new HashMap<String, String>();
			map.put("userid", params[0]);
			String person_content = HttpUtils.getServletContentByGet(GlobleData.GET_PERSONWORK_URL, map, "UTF-8");
			return person_content;
		}
		@Override
		protected void onPostExecute(String result)
		{
			// TODO Auto-generated method stub
			ProgressDialogUtils.stopProgressDialog(customeProgressDialog);
			peronal_list = ParseData.getShowWorksList(result);
			Log.i(TAG, "peronal_list---------->"+peronal_list);
			personal_linklist = new LinkedList<Map<String,String>>();
			for(int i=0;i<peronal_list.size();i++)
			{
				personal_linklist.addFirst(peronal_list.get(i));
			}
			showPublishAdapter = new ShowPublishAdapter(PersonActivity.this, personal_linklist,true);
			personal_publish_list.setAdapter(showPublishAdapter);
			showPublishAdapter.setDelVisible(true);
			
			super.onPostExecute(result);
		}
	}
	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		switch (v.getId())
		{
		case R.id.go_settings:
			Intent intent = new Intent();
			intent.setClass(PersonActivity.this, SettingActivity.class);
			startActivity(intent);
			break;
		default:
			break;
		}
		
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, final int position, long id)
	{
		// TODO Auto-generated method stub
		final String s_worksid = (String) ((TextView)view.findViewById(R.id.publish_username)).getTag();
		Log.i(TAG, s_worksid);
		final String title = ((TextView)view.findViewById(R.id.title)).getText().toString();
		String s_userid = (String) ((TextView)view.findViewById(R.id.title)).getTag();
		ImageView delete_label = (ImageView)view.findViewById(R.id.delete_label);
		if(sharedPreferences.getString("userid", "").equals(s_userid))
		{
			delete_label.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					// TODO Auto-generated method stub
					new AlertDialog.Builder(PersonActivity.this).setTitle(title).setMessage("确定要删除这条动态吗？").setPositiveButton("确定", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							// TODO Auto-generated method stub
							dialog.dismiss();
							new DelSinglePersonWork().execute(s_worksid);
							personal_linklist.remove(position);
							showPublishAdapter.notifyDataSetChanged();
						}
					}).setNegativeButton("取消", new DialogInterface.OnClickListener()
					{
						@Override
						public void onClick(DialogInterface dialog, int which)
						{
							// TODO Auto-generated method stub
							dialog.dismiss();
						}
					}).create().show();
				}
			});

		}
		
	}
	
}
