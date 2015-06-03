package com.gao.fragment;

import java.util.HashMap;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;
import com.gao.data.GlobleData;
import com.gao.http.HttpUtils;
import com.gao.imageloaderlistener.AnimateFirstDisplayListener;
import com.gao.myyitu.LogActivty;
import com.gao.myyitu.MainActivity;
import com.gao.myyitu.R;
import com.gao.myyitu.SetPictureActivity;
import com.gao.myyitu.SetSignActivity;
import com.gao.myyitu.SetUsernameActivity;
import com.gao.myyitu.SettingActivity;
import com.gao.utils.ProgressDialogUtils;
import com.gao.view.CustomeProgressDialog;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class SettingFragment extends SherlockFragment implements OnClickListener, OnCheckedChangeListener
{
	private static final String TAG="SettingFragment";
	private static final String FEMALE="Å®";
	private static final String MALE="ÄÐ";
	private SharedPreferences sharedPreferences;
	private View rootView;
	private RelativeLayout user_icon,user_nick,user_sign;
	private ImageView user_icon_image;
	private TextView user_nick_text,user_sign_text;//1Î´µÇÂ¼Îª¡°µã»÷µÇÂ¼¡±2Î´µÇÂ¼Îª¡°µã»÷ÌîÐ´¡±
	private CheckBox sex_choice_switch;
	private Button user_logout;
	private boolean login_falg;
	private DisplayImageOptions options;
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	private CustomeProgressDialog customeProgressDialog = null;
	private FragmentManager fm;
	private FragmentTransaction ft;
	public SettingFragment()
	{
		super();
	}
	public SettingFragment(FragmentManager fm)
	{
		this.fm = fm;
		options = new DisplayImageOptions.Builder().displayer(new RoundedBitmapDisplayer(20)).showImageOnLoading(R.drawable.user_icon_default_main).showImageOnFail(R.drawable.user_icon_default_main).showImageForEmptyUri(R.drawable.user_icon_default_main).cacheInMemory(true).cacheOnDisc(true).displayer(new FadeInBitmapDisplayer(100)).build();
	}
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		if(null == rootView)
		{
			rootView = inflater.inflate(R.layout.setting_fragment, null);
		}
		initView(rootView);
		sharedPreferences = getActivity().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		login_falg = (sharedPreferences.getString("userid", "").equals("")||sharedPreferences.getString("userid", "") == null);
		Log.i(TAG, "login_falg--->"+login_falg);
		if(!login_falg)
		{
			ImageLoader imageLoader = ImageLoader.getInstance();
			imageLoader.displayImage(GlobleData.PERSON_HEAD_IMG+sharedPreferences.getString("photo", ""), user_icon_image,animateFirstListener);
			user_nick_text.setText(sharedPreferences.getString("username", ""));
			user_sign_text.setText(sharedPreferences.getString("mark", ""));
			
		}
		else
		{
			Log.i(TAG, "ÇëµÇÂ¼");
			user_logout.setText("ÇëµÇÂ¼");
		}
		if(sharedPreferences.getString("sex", "").equals(FEMALE))
		{
			sex_choice_switch.setChecked(true);
		}
		else if(sharedPreferences.getString("sex", "").equals(MALE))
		{
			sex_choice_switch.setChecked(false);
		}
		setListener();
		return rootView;
	}
	private void initView(View rootView)
	{
		customeProgressDialog = CustomeProgressDialog.ctreateDialog(getSherlockActivity());
		user_icon_image = (ImageView)rootView.findViewById(R.id.user_icon_image);
		user_nick_text = (TextView)rootView.findViewById(R.id.user_nick_text);
		user_sign_text = (TextView)rootView.findViewById(R.id.user_sign_text);
		sex_choice_switch = (CheckBox)rootView.findViewById(R.id.sex_choice_switch);
		user_logout = (Button)rootView.findViewById(R.id.user_logout);
		user_icon = (RelativeLayout)rootView.findViewById(R.id.user_icon);
		user_nick = (RelativeLayout)rootView.findViewById(R.id.user_nick);
		user_sign = (RelativeLayout)rootView.findViewById(R.id.user_sign);
	}
	private void setListener()
	{
		user_logout.setOnClickListener(this);
		user_icon.setOnClickListener(this);
		user_sign.setOnClickListener(this);
		user_nick.setOnClickListener(this);
		sex_choice_switch.setOnCheckedChangeListener(this);
	}
	
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
		case R.id.user_logout:
			if(!login_falg)
			{
				sharedPreferences.edit().remove("userid").commit();
				Intent intent = new Intent();
				intent.setClass(getSherlockActivity(), MainActivity.class);
//				Intent service = new Intent("com.gao.service.PushService");
//				getSherlockActivity().stopService(service);
				startActivity(intent);
			}
			else
			{
				Intent intent = new Intent();
				intent.setClass(getSherlockActivity(),LogActivty.class);
				startActivity(intent);
			}
			break;
		case R.id.user_sign:
			if(!login_falg)
			{
				Intent intent = new Intent();
				intent.setClass(getSherlockActivity(),SetSignActivity.class);
				Log.i(TAG, "-----------------SetSignActivity-------------");
				startActivity(intent);
			}
			else
			{
				Intent intent = new Intent();
				intent.setClass(getSherlockActivity(),LogActivty.class);
				startActivity(intent);
			}
			break;
		case R.id.user_icon:
			if(!login_falg)
			{
				Intent intent = new Intent();
				intent.setClass(getSherlockActivity(),SetPictureActivity.class);
				startActivity(intent);
			}
			else
			{
				Intent intent = new Intent();
				intent.setClass(getSherlockActivity(),LogActivty.class);
				startActivity(intent);
			}
			break;
		case R.id.user_nick:
			if(!login_falg)
			{
				Intent intent = new Intent();
				intent.setClass(getSherlockActivity(),SetUsernameActivity.class);
				Log.i(TAG, "-----------------SetUsernameActivity-------------");
				startActivity(intent);
			}
			else
			{
				Intent intent = new Intent();
				intent.setClass(getSherlockActivity(),LogActivty.class);
				startActivity(intent);
			}
			break;
		default:
			break;
		}
	}
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
	{
		// TODO Auto-generated method stub
		Log.i(TAG, "isChecked--->"+isChecked);
		if(isChecked)
		{
			//Å®
//			new SetSexTask
			new SetSexTask().execute(FEMALE);
			SharedPreferences.Editor editor = sharedPreferences.edit();
			editor.putString("sex", FEMALE);
			editor.commit();
		}
		else
		{
			new SetSexTask().execute(MALE);
			SharedPreferences.Editor editor = sharedPreferences.edit();
			editor.putString("sex", MALE);
			editor.commit();
		}
		
	}
	class SetSexTask extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute()
		{
			ProgressDialogUtils.startProgressDialog("ÇëÉÔºò...", customeProgressDialog);
			super.onPreExecute();
		}
		@Override
		protected String doInBackground(String... params)
		{
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("userid", sharedPreferences.getString("userid", ""));
			map.put("sex", params[0]);
			String sex_content = HttpUtils.getServletContentByGet(GlobleData.SET_SEX_URL, map, "UTF-8");
			return sex_content;
		}
		@Override
		protected void onPostExecute(String result)
		{
			ProgressDialogUtils.stopProgressDialog(customeProgressDialog);
			String mark = result.substring(0,1);
			if(mark.equals("1"))
			{
				Toast.makeText(getSherlockActivity(), "²Ù×÷³É¹¦", Toast.LENGTH_SHORT).show();

			}
			else
			{
				Toast.makeText(getSherlockActivity(), "²Ù×÷Ê§°Ü", Toast.LENGTH_SHORT).show();
			}
			super.onPostExecute(result);
		}
	}
	
	@Override
	public void onResume()
	{
		// TODO Auto-generated method stub
		super.onResume();
		Log.i(TAG, "setting onresume ImageLoader reload");
		if(!login_falg)
		{
			ImageLoader imageLoader = ImageLoader.getInstance();
			imageLoader.displayImage(GlobleData.PERSON_HEAD_IMG+sharedPreferences.getString("photo", ""), user_icon_image,animateFirstListener);
		}
		else
		{
		}
		
	}
}
