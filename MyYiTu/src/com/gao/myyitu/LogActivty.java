package com.gao.myyitu;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.gao.application.ExitApplication;
import com.gao.bean.UserInfoBean;
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

public class LogActivty extends SherlockFragmentActivity implements OnClickListener, TextWatcher
{
	private static final String TAG="LoginActivty";
	private FragmentManager fm;
	private FragmentTransaction ft;
	private ActionBar actionBar;
	private Button login,new_reg,find_psd;
	private ImageView login_head_img;
	private EditText user_email,user_password;
	private String input_user_email,input_user_password;
	private CustomeProgressDialog customeProgressDialog;
	private DisplayImageOptions options;
	private SharedPreferences sharedPreferences;
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	private long firstTime = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ExitApplication.getInstanse().addActivity(this);
		setContentView(R.layout.login);
		actionBar = getSupportActionBar();
		actionBar.setTitle("登录");
		actionBar.setDisplayHomeAsUpEnabled(true);
		options = new DisplayImageOptions.Builder().displayer(new RoundedBitmapDisplayer(20,20)).showImageOnLoading(R.drawable.default_login).showImageOnFail(R.drawable.default_login).showImageForEmptyUri(R.drawable.default_login).cacheInMemory(true).cacheOnDisc(true).displayer(new FadeInBitmapDisplayer(100)).build();
		
		customeProgressDialog =CustomeProgressDialog.ctreateDialog(this);
		sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_APPEND);
		actionBar.setDisplayHomeAsUpEnabled(false);
		initView();
		if(!(sharedPreferences.getString("email", "").equals("")&&sharedPreferences.getString("password", "").equals("")))
		{
			user_email.setText(sharedPreferences.getString("email", ""));
			user_password.setText(sharedPreferences.getString("password", ""));
			String img_url =GlobleData.PERSON_HEAD_IMG+sharedPreferences.getString("photo", ""); 
			Log.i(TAG, "img_url--->"+img_url);
			ImageLoader imageLoader = ImageLoader.getInstance();
			imageLoader.displayImage(GlobleData.PERSON_HEAD_IMG+sharedPreferences.getString("photo", ""), login_head_img,options,animateFirstListener);
		}
		setListener();
	}
	private void initView()
	{
		login = (Button)findViewById(R.id.login);
		new_reg = (Button)findViewById(R.id.new_reg);
		login_head_img = (ImageView)findViewById(R.id.login_head_img);
		user_email = (EditText)findViewById(R.id.user_email);
		user_password = (EditText)findViewById(R.id.user_password);
		find_psd = (Button)findViewById(R.id.find_psd);
	}
	private void getInputData()
	{
		input_user_email = user_email.getText().toString();
		input_user_password = user_password.getText().toString();
		Log.i(TAG, "input_user_email-->"+input_user_email);
		Log.i(TAG, "input_user_password-->"+input_user_password);
	}
	private void setListener()
	{
		// TODO Auto-generated method stub
		new_reg.setOnClickListener(this);
		login.setOnClickListener(this);
		user_email.addTextChangedListener(this);
		find_psd.setOnClickListener(this);
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
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		switch (v.getId())
		{
		case R.id.new_reg:
			Intent intent = new Intent();
			intent.setClass(LogActivty.this, RegActivity.class);
			startActivity(intent);
			break;
		case R.id.login:
			getInputData();
			if(input_user_email.equals("")||input_user_password.equals(""))
			{
				Toast.makeText(LogActivty.this, "请填写空白处内容", Toast.LENGTH_SHORT).show();
			}
			else
			{
				new LoginTask().execute();
			}
		case R.id.find_psd:
			Intent intent_findpsd = new Intent();
			intent_findpsd.setClass(LogActivty.this, FindPsdActivity.class);
			startActivity(intent_findpsd);
			break;
		default:
			break;
		}
	}
	
	class LoginTask extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute()
		{
			ProgressDialogUtils.startProgressDialog("登录中，请稍候...", customeProgressDialog);
			super.onPreExecute();
		}
		@Override
		protected String doInBackground(String... params)
		{
			HashMap<String, String> map = new HashMap<String, String>();
			getInputData();
			map.put("email", input_user_email);
			map.put("password", input_user_password);
			String login_content = HttpUtils.getServletContentByGet(GlobleData.LOGIN_URL, map, "UTF-8");
			return login_content;
		}
		@Override
		protected void onPostExecute(String result)
		{
			// TODO Auto-generated method stub
			ProgressDialogUtils.stopProgressDialog(customeProgressDialog);
			Log.i(TAG, "login_content-->"+result);
//			String mark = result.substring(0,1);
			Log.i(TAG, "result-->"+result.length());
			if(result.length() != 4)
			{
				UserInfoBean userInfoBean = ParseData.getUserInfoData(result);
				Log.i(TAG, "login_content-->"+userInfoBean.toString());
//				sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_APPEND);
				SharedPreferences.Editor seditor =  sharedPreferences.edit();
				seditor.putString("email", userInfoBean.getEmail());
				seditor.putString("mark", userInfoBean.getMark());
				seditor.putString("password", userInfoBean.getPassword());
				seditor.putString("photo", userInfoBean.getPhoto());
				seditor.putString("sex", userInfoBean.getSex());
				seditor.putString("userid", userInfoBean.getUserid());
				seditor.putString("username", userInfoBean.getUsername());
				seditor.commit();
				Intent intent = new Intent();
				intent.setClass(LogActivty.this, MainActivity.class);
//				Intent service = new Intent("com.gao.service.PushService");
//				startService(service);
				startActivity(intent);
			}
			else
			{
				
				Intent intent = new Intent(LogActivty.this,LogActivty.class);
				startActivity(intent);
				Toast.makeText(LogActivty.this, "登录失败",Toast.LENGTH_SHORT).show();
				
			}
			
			
			super.onPostExecute(result);
		}
	}

	@Override
	public void afterTextChanged(Editable arg0)
	{
		// TODO Auto-generated method stub
		if(user_email.getText().toString().equals(sharedPreferences.getString("email", "")))
		{
			ImageLoader imageLoader = ImageLoader.getInstance();
			imageLoader.displayImage(GlobleData.PERSON_HEAD_IMG+sharedPreferences.getString("photo", ""), login_head_img,options,animateFirstListener);
		}
		
	}
	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after)
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count)
	{
		// TODO Auto-generated method stub
		if(0 == s.length())
		{
			login_head_img.setImageDrawable(getResources().getDrawable(R.drawable.default_login));
			user_password.setText("");
		}
	}
}
