package com.gao.myyitu;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Context;
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

public class SetSignActivity extends SherlockFragmentActivity implements OnClickListener
{
	private static final String TAG="SetSignActivity";
	private EditText sign_comment_content;
	private Button sign_comment_commit;
	private String userid;
	private String mark;
	private SharedPreferences sharedPreferences;
	private CustomeProgressDialog customeProgressDialog = null;
	private ActionBar actionBar;
	private long firstTime =0;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ExitApplication.getInstanse().addActivity(this);
		setContentView(R.layout.setsign);
		actionBar = getSupportActionBar();
		sign_comment_commit = (Button)findViewById(R.id.sign_comment_commit);
		sign_comment_content = (EditText)findViewById(R.id.sign_comment_content);
		actionBar.setTitle("设置个人签名");
		actionBar.setDisplayHomeAsUpEnabled(true);
		customeProgressDialog = CustomeProgressDialog.ctreateDialog(this);
		sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		
		sign_comment_commit = (Button)findViewById(R.id.sign_comment_commit);
		sign_comment_content = (EditText)findViewById(R.id.sign_comment_content);
		sign_comment_commit.setOnClickListener(this);
	}
	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		switch (v.getId())
		{
		case R.id.sign_comment_commit:
			new SetMarkTask().execute();
			break;

		default:
			break;
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
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// TODO Auto-generated method stub
		switch (item.getItemId())
		{
		case android.R.id.home:
			Intent intent = new Intent();
			intent.setClass(SetSignActivity.this,SettingActivity.class);
			startActivity(intent);
			return true;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	class SetMarkTask extends AsyncTask<String, String, String>
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
			HashMap<String, String> map = new HashMap<String, String>();
			userid = sharedPreferences.getString("userid", "");
			Log.i(TAG, "userid--->"+userid);
			//http://10.163.7.91:8080/scrawl/AndroidSetMarkServlet?userid=13&mark=%E6%88%91%E6%98%AF%E8%A6%81%E6%88%90%E4%B8%BA%E6%B5%B7%E6%B3%BD%E7%8E%8B%E7%9A%84man
			map.put("userid",userid);
			map.put("mark", sign_comment_content.getText().toString());
			String mark_content = HttpUtils.getServletContentByGet(GlobleData.MARK_SIGN_URL, map, "UTF-8");
			return mark_content;
		}
		@Override
		protected void onPostExecute(String result)
		{
			// TODO Auto-generated method stub
			ProgressDialogUtils.stopProgressDialog(customeProgressDialog);
			super.onPostExecute(result);
			String mark = result.substring(0,1);
			if(mark.equals("1"))
			{
				Log.i(TAG, "修改成功");
				SharedPreferences.Editor editor  = sharedPreferences.edit();
				editor.putString("mark", sign_comment_content.getText().toString());
				editor.commit();
				Intent intent  = new Intent();
				intent.setClass(SetSignActivity.this, SettingActivity.class);
				startActivity(intent);
			}
			else
			{
				Toast.makeText(SetSignActivity.this, "操作失败", Toast.LENGTH_SHORT).show();
			}
		}
	}
}
