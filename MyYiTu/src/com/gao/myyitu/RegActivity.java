package com.gao.myyitu;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
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
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.gao.application.ExitApplication;
import com.gao.data.GlobleData;
import com.gao.http.HttpUtils;
import com.gao.utils.ProgressDialogUtils;
import com.gao.view.CustomeProgressDialog;

public class RegActivity extends SherlockFragmentActivity implements TextWatcher, OnClickListener
{
	private static final String TAG="RegActivity";
	private ActionBar actionBar;
	private EditText reg_email,reg_username,reg_password,reg_repassword;
	private Button reg;
	private CustomeProgressDialog customeProgressDialog=null;
	private String reginput_email,reginput_username,reginput_password,reginput_repassword;
	private long firstTime = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ExitApplication.getInstanse().addActivity(this);
		setContentView(R.layout.register);
		customeProgressDialog=CustomeProgressDialog.ctreateDialog(this);
		actionBar = getSupportActionBar();
		actionBar.setTitle("ע��");
		actionBar.setDisplayHomeAsUpEnabled(true);
		initView();
		setListener();
	}
	private void setListener()
	{
		reg_repassword.addTextChangedListener(this);
		reg.setOnClickListener(this);
	}
	private void initView()
	{
		reg_email = (EditText)findViewById(R.id.reg_email);
		reg_username = (EditText)findViewById(R.id.reg_username);
		reg_password = (EditText)findViewById(R.id.reg_password);
		reg_repassword = (EditText)findViewById(R.id.reg_repassword);
		reg = (Button)findViewById(R.id.reg);
	}
	private void getInputData()
	{
		// TODO Auto-generated method stub
		reginput_email = reg_email.getText().toString();
		reginput_username = reg_username.getText().toString();
		reginput_password = reg_password.getText().toString();
		reginput_repassword = reg_repassword.getText().toString();
		Log.i(TAG, "-------reginput_email--------"+reginput_email);
		Log.i(TAG, "-------reginput_username--------"+reginput_username);
		Log.i(TAG, "-------reginput_password--------"+reginput_password);
		Log.i(TAG, "-------reginput_repassword--------"+reginput_repassword);
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			if ((System.currentTimeMillis() - firstTime) > 2000)
			{// ������ΰ���ʱ��������2000���룬���˳�
				Toast.makeText(this, "�ٰ�һ���˳�����", Toast.LENGTH_SHORT).show();
				firstTime = System.currentTimeMillis();// ����mExitTime
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
	
	class RegTask extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute()
		{
			// TODO Auto-generated method stub
			ProgressDialogUtils.startProgressDialog("���Ժ�...", customeProgressDialog);
			super.onPreExecute();
		}
		@Override
		protected String doInBackground(String... params)
		{
			HashMap<String, String> map = new HashMap<String, String>();
			getInputData();
			map.put("email", reginput_email);
			map.put("username", reginput_username);
			map.put("password", reginput_password);
			map.put("password2", reginput_repassword);
			map.put("sex", "��");
			String reg_content = HttpUtils.getServletContentByGet(GlobleData.REG_URL, map, "UTF-8");
			return reg_content;
		}

		@Override
		protected void onPostExecute(String result)
		{
			// TODO Auto-generated method stub
			ProgressDialogUtils.stopProgressDialog(customeProgressDialog);
			String mark = result.substring(0, 1);
			if(mark.equals("1"))
			{
				Toast.makeText(RegActivity.this, "ע��ɹ�,���¼", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent();
				intent.setClass(RegActivity.this,LogActivty.class);
				startActivity(intent);
			}
			else
			{
				Toast.makeText(RegActivity.this, "ע��ʧ��", Toast.LENGTH_SHORT).show();
			}
			super.onPostExecute(result);
		}
	}

	@Override
	public void afterTextChanged(Editable arg0)
	{
		// TODO Auto-generated method stub
		String psd = reg_password.getText().toString();
		String repsd = reg_repassword.getText().toString();
		if(!psd.equals(repsd))
		{
//			Toast.makeText(RegActivity.this, "�����������벻һ��", Toast.LENGTH_SHORT).show();
			reg.setPressed(true);
			reg.setEnabled(false);
			reg.setClickable(false);
		}
		else
		{
			reg.setPressed(false);
			reg.setEnabled(true);
			reg.setClickable(true);
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

	}
	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		switch (v.getId())
		{
		case R.id.reg:

			getInputData();
			if(reginput_email.equals("")||reginput_username.equals("")||reginput_password.equals("")||reginput_repassword.equals(""))
			{
				Toast.makeText(RegActivity.this, "����д�հ״�����", Toast.LENGTH_SHORT).show();
			}
			else
			{
				new RegTask().execute();
			}
			break;

		default:
			break;
		}
	}
}
