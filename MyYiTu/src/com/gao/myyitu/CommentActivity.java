package com.gao.myyitu;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.gao.adapter.CommentListAdapter;
import com.gao.application.ExitApplication;
import com.gao.data.GlobleData;
import com.gao.http.HttpUtils;
import com.gao.imageloaderlistener.AnimateFirstDisplayListener;
import com.gao.utils.ParseData;
import com.gao.utils.ProgressDialogUtils;
import com.gao.view.CustomeProgressDialog;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
@SuppressWarnings("unused")
public class CommentActivity extends SherlockFragmentActivity implements OnClickListener
{
	private static final String TAG="CommentActivity";
	private ActionBar actionBar;
	private Intent intent;
	private String s_user_photo;
	private String s_worksid;
	private String s_good;
	private String s_time;
	private String s_photo;
	private String s_shuoshuo;
	private String is_good;
	private String s_username;
	private String s_title;
	private String s_userid;
	private ImageView publish_user_icon,publish_img;
	private TextView publish_username,publish_shuoshuo,item_action_share,item_action_love,time,item_action_comment,title;
	private ListView commentlist;
	private EditText comment_content;
	private Button comment_commit;
	private DisplayImageOptions user_icon_options;
	private DisplayImageOptions publish_img_options;
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	private CustomeProgressDialog customeProgressDialog;
	private List<Map<String, String>> comment_list;
	private CommentListAdapter commentListAdapter;
	private SharedPreferences sharedPreferences;
	private HashMap< String, String> add_comment_map;
	private long firstTime = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ExitApplication.getInstanse().addActivity(this);
		sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
		customeProgressDialog = CustomeProgressDialog.ctreateDialog(this);
		actionBar = getSupportActionBar();
		actionBar.setTitle("��������");
		actionBar.setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.activity_comment);
		setImageOptions();
		getIntentData();
		initView();
		setListener();
		new GetCommentTask().execute();
		
		
	}
	private void setListener()
	{
		// TODO Auto-generated method stub
		comment_commit.setOnClickListener(this);
	}
	private void setImageOptions()
	{
		user_icon_options = new DisplayImageOptions.Builder()  
		 .showImageOnLoading(R.drawable.user_icon_default_main) //����ͼƬ�������ڼ���ʾ��ͼƬ  
		 .showImageForEmptyUri(R.drawable.user_icon_default_main)//����ͼƬUriΪ�ջ��Ǵ����ʱ����ʾ��ͼƬ  
		.showImageOnFail(R.drawable.user_icon_default_main)  //����ͼƬ����/��������д���ʱ����ʾ��ͼƬ
		.cacheInMemory(true)//�������ص�ͼƬ�Ƿ񻺴����ڴ���  
		.cacheOnDisk(true)//�������ص�ͼƬ�Ƿ񻺴���SD����  
		.displayer(new FadeInBitmapDisplayer(0))//�Ƿ�ͼƬ���غú���Ķ���ʱ��
		.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
		.build();//������� 
		
		publish_img_options = new DisplayImageOptions.Builder()  
		.showImageOnLoading(R.drawable.bg_pic_loading) //����ͼƬ�������ڼ���ʾ��ͼƬ  
		.showImageForEmptyUri(R.drawable.bg_pic_loading)//����ͼƬUriΪ�ջ��Ǵ����ʱ����ʾ��ͼƬ  
		.showImageOnFail(R.drawable.bg_pic_loading)  //����ͼƬ����/��������д���ʱ����ʾ��ͼƬ
		.cacheInMemory(true)//�������ص�ͼƬ�Ƿ񻺴����ڴ���  
		.cacheOnDisk(true)//�������ص�ͼƬ�Ƿ񻺴���SD����  
		.displayer(new FadeInBitmapDisplayer(0))//�Ƿ�ͼƬ���غú���Ķ���ʱ��
		.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
		.build();//������� 
	}
	private void initView()
	{
		// TODO Auto-generated method stub
		
		publish_user_icon = (ImageView)findViewById(R.id.publish_user_icon);
		publish_img = (ImageView)findViewById(R.id.publish_img);
		publish_username = (TextView)findViewById(R.id.publish_username);
		publish_shuoshuo = (TextView)findViewById(R.id.publish_shuoshuo);
		item_action_share = (TextView)findViewById(R.id.item_action_share);
		item_action_love = (TextView)findViewById(R.id.item_action_love);
		item_action_comment = (TextView)findViewById(R.id.item_action_comment);
		time = (TextView)findViewById(R.id.time);
		title = (TextView)findViewById(R.id.title);
		commentlist = (ListView)findViewById(R.id.comment_list);
		comment_content = (EditText)findViewById(R.id.comment_content);
		comment_commit = (Button)findViewById(R.id.comment_commit);
		ImageLoader imageLoader = ImageLoader.getInstance();
		imageLoader.displayImage(GlobleData.PERSON_HEAD_IMG+s_user_photo, publish_user_icon,user_icon_options,animateFirstListener);
		imageLoader.displayImage(GlobleData.SHOW_WORKS_IMG+s_photo, publish_img,user_icon_options,animateFirstListener);
		publish_username.setText(s_username);
		publish_shuoshuo.setText(s_shuoshuo);
		title.setText(s_title);
		item_action_love.setText(s_good);
		if(is_good.equals("1"))
		{
			item_action_love.setTextColor(getResources().getColor(R.color.red));
		}
		time.setText(s_time);
		
		
		
	}
	private void getIntentData()
	{
		intent = this.getIntent();
		s_user_photo = intent.getStringExtra("s_user_photo");
		s_worksid = intent.getStringExtra("s_worksid");
		s_good = intent.getStringExtra("s_good");
		s_time = intent.getStringExtra("s_time");
		s_photo = intent.getStringExtra("s_photo");
		s_shuoshuo = intent.getStringExtra("s_shuoshuo");
		is_good = intent.getStringExtra("is_good");
		s_username = intent.getStringExtra("s_username");
		s_title = intent.getStringExtra("s_title");
		s_userid = intent.getStringExtra("s_userid");//�����۵����ߵ�id
		Log.i(TAG, s_user_photo);
		Log.i(TAG, s_worksid);
		Log.i(TAG, s_good);
		Log.i(TAG, s_time);
		Log.i(TAG, s_photo);
		Log.i(TAG, s_shuoshuo);
		Log.i(TAG, is_good);
		Log.i(TAG, s_username);
		Log.i(TAG, s_title);
		Log.i(TAG, s_userid);
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
	class GetCommentTask extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute()
		{
			// TODO Auto-generated method stub
			ProgressDialogUtils.startProgressDialog("����������...", customeProgressDialog);
			super.onPreExecute();
		}
		@Override
		protected String doInBackground(String... params)
		{
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("s_worksid",s_worksid);
			String com_list_content = HttpUtils.getServletContentByGet(GlobleData.GET_COMMENTLIST_URL, map, "UTF-8");
			return com_list_content;
		}
		@Override
		protected void onPostExecute(String result)
		{
			// TODO Auto-generated method stub
			ProgressDialogUtils.stopProgressDialog(customeProgressDialog);
			comment_list = ParseData.getShowWorksList(result);
			Log.i(TAG, "comment_list------>"+comment_list);
			commentListAdapter = new CommentListAdapter(CommentActivity.this,comment_list);
			commentlist.setAdapter(commentListAdapter);
			Log.i(TAG, "���۵�����--->"+comment_list.size());
			setListViewHeightBasedOnChildren(commentlist);
			super.onPostExecute(result);
		}
	}
	
	
	class AddComment extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute()
		{
			// TODO Auto-generated method stub
			ProgressDialogUtils.startProgressDialog("�ύ��...", customeProgressDialog);
			super.onPreExecute();
		}
		@Override
		protected String doInBackground(String... params)
		{
			// TODO Auto-generated method stub
			add_comment_map = new HashMap<String, String>();
			add_comment_map.put("s_worksid", s_worksid);
			add_comment_map.put("work_comment", comment_content.getText().toString());
			add_comment_map.put("c_username",sharedPreferences.getString("username", ""));
			add_comment_map.put("c_userid",sharedPreferences.getString("userid", ""));
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
			add_comment_map.put("c_time", df.format(new Date()));
			add_comment_map.put("c_comed_userid", intent.getStringExtra("s_userid"));
			String addcomm_content = HttpUtils.getServletContentByGet(GlobleData.ADD_COMMENT_URL, add_comment_map, "UTF-8");
			return addcomm_content;
		}
		@Override
		protected void onPostExecute(String result)
		{
			// TODO Auto-generated method stub
			ProgressDialogUtils.stopProgressDialog(customeProgressDialog);
			String mark = result.substring(0,1);
			if(mark.equals("1"))
			{
				Toast.makeText(CommentActivity.this, "���۳ɹ�", Toast.LENGTH_SHORT).show();
				comment_content.setText("");
				new GetCommentTask().execute();
			}
			else
			{
				Toast.makeText(CommentActivity.this, "����ʧ�ܣ�������~", Toast.LENGTH_SHORT).show();
			}
			super.onPostExecute(result);
		}
	}
	
	
	/*** 
     * ��̬����listview�ĸ߶� 
     *  item �ܲ��ֱ�����linearLayout
     * @param listView 
     */  
	public void setListViewHeightBasedOnChildren(ListView listView)
	{
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null)
		{
			return;
		}
		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++)
		{
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1))
				+ 15;
		listView.setLayoutParams(params);
	}
	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		switch (v.getId())
		{
		case R.id.comment_commit:
			if(sharedPreferences.getString("userid", "").equals("")||sharedPreferences.getString("userid", "")==null)
			{
				Toast.makeText(CommentActivity.this, "δ��¼�����ȵ�¼��������", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(CommentActivity.this,LogActivty.class);
				startActivity(intent);
			}
			else
			{
				if(comment_content.getText().toString().equals(""))
				{
					Toast.makeText(CommentActivity.this, "��ûд����Ŷ����~", Toast.LENGTH_SHORT).show();
				}
				else
				{
					new AddComment().execute();
//					comment_content.setText("");
//					Intent service = new Intent(CommentActivity.this,PushService.class);
//					startService(service);
				}
			}

			
			break;

		default:
			break;
		}
		
	}
}

