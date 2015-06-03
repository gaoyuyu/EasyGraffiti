package com.gao.myyitu;

import java.util.HashMap;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.gao.application.ExitApplication;
import com.gao.data.GlobleData;
import com.gao.http.HttpUtils;
import com.gao.utils.ProgressDialogUtils;
import com.gao.view.CustomeProgressDialog;

public class FindPsdActivity extends SherlockFragmentActivity implements OnClickListener
{
	private long firstTime = 0;
	private EditText find_email;
	private Button find;
	private ActionBar actionBar;
	private CustomeProgressDialog customeProgressDialog;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ExitApplication.getInstanse().addActivity(this);
		setContentView(R.layout.find_psd);
		actionBar = getSupportActionBar();
		customeProgressDialog =CustomeProgressDialog.ctreateDialog(this);
		actionBar.setTitle("找回密码");
		actionBar.setDisplayHomeAsUpEnabled(true);
		find_email = (EditText)findViewById(R.id.find_email);
		find = (Button)findViewById(R.id.find);
		find.setOnClickListener(this);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// TODO Auto-generated method stub
		switch (item.getItemId())
		{
		case android.R.id.home:
			Intent upIntent = NavUtils.getParentActivityIntent(this);
			if (NavUtils.shouldUpRecreateTask(this, upIntent))
			{
				TaskStackBuilder.create(this)
						.addNextIntentWithParentStack(upIntent)
						.startActivities();
			} else
			{
				upIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				NavUtils.navigateUpTo(this, upIntent);
			}
			return true;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
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
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		switch (v.getId())
		{
		case R.id.find:
			if(find_email.getText().toString().equals(""))
			{
				Toast.makeText(FindPsdActivity.this, "请填写邮箱地址", Toast.LENGTH_SHORT).show();
			}
			else
			{
				new FindPsdTask().execute();
			}
			break;

		default:
			break;
		}
	}
	
	class FindPsdTask extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute()
		{
			// TODO Auto-generated method stub
			ProgressDialogUtils.startProgressDialog("请稍候...", customeProgressDialog);
			super.onPreExecute();
		}
		@Override
		protected String doInBackground(String... params)
		{
			// TODO Auto-generated method stub
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("email", find_email.getText().toString());
			String find_psd_content = HttpUtils.getServletContentByGet(GlobleData.FIND_PSD_URL, map, "UTF-8");
			return find_psd_content;
		}
		@Override
		protected void onPostExecute(String result)
		{
			// TODO Auto-generated method stub
			ProgressDialogUtils.stopProgressDialog(customeProgressDialog);
			if(!result.equals(""))
			{
				Toast.makeText(FindPsdActivity.this, "您的密码是："+result+"，别再忘了哦亲~", Toast.LENGTH_SHORT).show();
			}
			else
			{
				Toast.makeText(FindPsdActivity.this, "获取失败，请重试", Toast.LENGTH_SHORT).show();
			}
			super.onPostExecute(result);
		}
	}
}
