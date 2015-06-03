package com.gao.adapter;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;

import com.gao.data.GlobleData;
import com.gao.http.HttpUtils;
import com.gao.imageloaderlistener.AnimateFirstDisplayListener;
import com.gao.myyitu.CommentActivity;
import com.gao.myyitu.LogActivty;
import com.gao.myyitu.PersonActivity;
import com.gao.myyitu.R;
import com.gao.utils.ProgressDialogUtils;
import com.gao.utils.ScreenUtil;
import com.gao.view.CustomeProgressDialog;
import com.gao.view.DragImageView;
import com.gao.view.DragImageView.MODE;
import com.gao.view.DragImageView.OnClickCallback;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class ShowPublishAdapter extends BaseAdapter
{
	private static final String TAG="ShowPublishAdapter";
	private Context context;
	private LayoutInflater inflater;
	private List<Map<String,String>> publish_list;
	private boolean del_flag;//删除item的标志
//	private LinkedList<Map<String,String>> publish_list;
	private DisplayImageOptions user_icon_options;
	private DisplayImageOptions publish_img_options;
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	private SharedPreferences sharedPreferences;
	private int state_height;
	private CustomeProgressDialog customeProgressDialog;
	private boolean isDelVisible = false;
	
	public void setDelVisible(boolean isDelVisible)
	{
		this.isDelVisible = isDelVisible;
	}
	private Handler delHandler = new Handler()
	{

		@Override
		public void handleMessage(Message msg)
		{
			// TODO Auto-generated method stub
			Integer position = (Integer)msg.obj;
			new DelSinglePersonWork().execute(String.valueOf(position));
			super.handleMessage(msg);
		}
		
	};
	private class ViewHolder 
	{
		ImageView publish_user_icon,publish_img,delete_label;
		TextView publish_username,publish_shuoshuo,time,title;
		TextView item_action_comment,item_action_share,item_action_love;
		TextView left_label,right_label;
	}
	public void setRefreshData(List<Map<String,String>> publish_list)
	{
		this.publish_list = publish_list;
	}
	public ShowPublishAdapter(Context context,
			List<Map<String,String>> publish_list,boolean del_flag)
	{
		this.context = context;
		this.publish_list = publish_list;
		this.inflater = LayoutInflater.from(this.context);
		this.del_flag = del_flag;
		this.customeProgressDialog = CustomeProgressDialog.ctreateDialog(context);
		/**
		 * 设置用户头像加载配置
		 */
		user_icon_options = new DisplayImageOptions.Builder()  
		 .showImageOnLoading(R.drawable.user_icon_default_main) //设置图片在下载期间显示的图片  
		 .showImageForEmptyUri(R.drawable.user_icon_default_main)//设置图片Uri为空或是错误的时候显示的图片  
		.showImageOnFail(R.drawable.user_icon_default_main)  //设置图片加载/解码过程中错误时候显示的图片
		.cacheInMemory(true)//设置下载的图片是否缓存在内存中  
		.cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中  
		.displayer(new FadeInBitmapDisplayer(0))//是否图片加载好后渐入的动画时间
		.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
		.build();//构建完成 
		/**
		 * 设置涂鸦图片加载配置
		 */
		publish_img_options = new DisplayImageOptions.Builder()  
		.showImageOnLoading(R.drawable.bg_pic_loading) //设置图片在下载期间显示的图片  
		.showImageForEmptyUri(R.drawable.bg_pic_loading)//设置图片Uri为空或是错误的时候显示的图片  
		.showImageOnFail(R.drawable.bg_pic_loading)  //设置图片加载/解码过程中错误时候显示的图片
		.bitmapConfig(Bitmap.Config.RGB_565)//设置图片的解码类型//
		.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
		.cacheInMemory(true)//设置下载的图片是否缓存在内存中  
		.cacheOnDisk(true)//设置下载的图片是否缓存在SD卡中  
		.displayer(new FadeInBitmapDisplayer(0))//是否图片加载好后渐入的动画时间
		.build();//构建完成 
		//---------------------------------华丽丽的分割线  上------------------------------------------
		sharedPreferences = context.getSharedPreferences("UserInfo", Context.MODE_APPEND);
//		String[] get_params = new String[]{sharedPreferences.getString("userid", "")};
//		UserIsGoodService userIsGoodService = new UserIsGoodDao(context);
//		query_list = userIsGoodService.viewWorksid(get_params);
		//---------------------------------华丽丽的分割线  下------------------------------------------
		
	}

	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return publish_list.size();
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

	@SuppressWarnings("unused")
	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		// TODO Auto-generated method stub
		View.OnClickListener addgood_listener = null;
		View.OnClickListener comment_listener = null;
		View.OnClickListener viewDetail_listener = null;
		View.OnClickListener share_listener = null;
		View.OnLongClickListener del_longClickListener=null;
		ViewHolder viewHolder;

		if(null == convertView)
		{
			convertView = inflater.inflate(R.layout.show_publish, null);
			viewHolder = new ViewHolder();
			viewHolder.delete_label = (ImageView)convertView.findViewById(R.id.delete_label);
			viewHolder.publish_user_icon = (ImageView)convertView.findViewById(R.id.publish_user_icon);
			viewHolder.publish_username = (TextView)convertView.findViewById(R.id.publish_username);
			viewHolder.publish_shuoshuo = (TextView)convertView.findViewById(R.id.publish_shuoshuo);
			viewHolder.publish_img = (ImageView)convertView.findViewById(R.id.publish_img);
			viewHolder.title = (TextView)convertView.findViewById(R.id.title);
			viewHolder.time = (TextView)convertView.findViewById(R.id.time);
			viewHolder.item_action_comment = (TextView)convertView.findViewById(R.id.item_action_comment);
			viewHolder.item_action_love = (TextView)convertView.findViewById(R.id.item_action_love);
			viewHolder.item_action_share = (TextView)convertView.findViewById(R.id.item_action_share);
			
			viewHolder.left_label = (TextView)convertView.findViewById(R.id.left_label);
			viewHolder.right_label = (TextView)convertView.findViewById(R.id.right_label);
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder = (ViewHolder)convertView.getTag();
		}
		/**
		 *      "s_user_photo": "13.jpg",
        "s_worksid": 1,
        "s_good": "2",
        "s_time": "2014/5/6 星期二 1:12:00",
        "s_photo": "10.jpg",
        "s_shuoshuo": "今天天气真好",
        "s_username": "夜之渡鸦"
		 */
		viewHolder.publish_username.setText(publish_list.get(position).get("s_username"));
		viewHolder.time.setText(publish_list.get(position).get("s_time"));
		viewHolder.publish_shuoshuo.setText(publish_list.get(position).get("s_shuoshuo"));
		viewHolder.item_action_love.setText(publish_list.get(position).get("s_good"));
		viewHolder.publish_username.setTag(publish_list.get(position).get("s_worksid"));
		viewHolder.title.setText(publish_list.get(position).get("s_title"));
		viewHolder.title.setTag(publish_list.get(position).get("s_userid"));
		if(!sharedPreferences.getString("userid", "").equals(""))
		{
			if(publish_list.get(position).get("is_good").equals("1"))
			{
				viewHolder.item_action_love.setTextColor(context.getResources().getColor(R.color.red));
			}
			else
			{
				viewHolder.item_action_love.setTextColor(Color.parseColor("#000000"));
			}
		}

		viewHolder.item_action_love.setTag(position);
		ImageLoader imageLoader = ImageLoader.getInstance();
//		if(publish_list.get(position).get("s_photo").equals("")||publish_list.get(position).get("s_photo") == null)
//		{
//			viewHolder.publish_img.setVisibility(View.GONE);
//			viewHolder.left_label.setVisibility(View.GONE);
//			viewHolder.right_label.setVisibility(View.GONE);
//			
//		}
//		else
//		{
			imageLoader.displayImage(GlobleData.SHOW_WORKS_IMG+publish_list.get(position).get("s_photo"), viewHolder.publish_img,publish_img_options);
//		}
		
		imageLoader.displayImage(GlobleData.PERSON_HEAD_IMG+publish_list.get(position).get("s_user_photo"), viewHolder.publish_user_icon,user_icon_options);
		addgood_listener = new Add_GoodListener(position, viewHolder.publish_username,viewHolder.item_action_love);
		comment_listener = new CommentListener(position);
		viewDetail_listener = new ViewOtherDetailListener(position);
		share_listener = new ShareListener(position);
		del_longClickListener = new Del_LongClickListener(position);
		viewHolder.item_action_love.setOnClickListener(addgood_listener);
		viewHolder.item_action_comment.setOnClickListener(comment_listener);
		final PopupWindow popwindow = initPopwindow(position);
		viewHolder.publish_img.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				popwindow.showAtLocation(v, Gravity.NO_GRAVITY, 0, 0);
			}
		});
		if(isDelVisible)
		{
			viewHolder.delete_label.setVisibility(View.VISIBLE);
		}
		else
		{
			viewHolder.delete_label.setVisibility(View.GONE);
			viewHolder.publish_user_icon.setOnClickListener(viewDetail_listener);
		}
		
		viewHolder.item_action_share.setOnClickListener(share_listener);
		if(del_flag&&(sharedPreferences.getString("userid", "").equals(publish_list.get(position).get("s_userid"))))
		{
//			viewHolder.publish_user_icon.setOnLongClickListener(del_longClickListener);
//			viewHolder.publish_img.setOnLongClickListener(del_longClickListener);
			
		}
		return convertView;
	}
	private PopupWindow initPopwindow(int position)
	{
		LayoutInflater mInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		final View view = mInflater
				.inflate(R.layout.popwindow_show_image, null);
		final DragImageView drag = (DragImageView) view
				.findViewById(R.id.div_main);
		final PopupWindow popwindow = new PopupWindow(view,
				ScreenUtil.getScreenWidth(), ScreenUtil.getScreenHeight());
		popwindow.setFocusable(true);
		popwindow.setBackgroundDrawable(new BitmapDrawable());
		popwindow.setTouchable(true);
		popwindow.setOutsideTouchable(true);
		popwindow.setAnimationStyle(R.style.PopDownMenu);
		view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT));
		view.setOnTouchListener(new OnTouchListener()
		{
			private boolean onlyClick;

			@Override
			public boolean onTouch(View v, MotionEvent event)
			{
				/** 处理单点、多点触摸 **/
				switch (event.getAction() & MotionEvent.ACTION_MASK)
				{
				case MotionEvent.ACTION_DOWN:
					onlyClick = true;
					drag.onTouchDown(event);
					break;
				// 多点触摸
				case MotionEvent.ACTION_POINTER_DOWN:
					onlyClick = false;
					drag.onPointerDown(event);
					break;

				case MotionEvent.ACTION_MOVE:
					onlyClick = false;
					drag.onTouchMove(event);
					break;
				case MotionEvent.ACTION_UP:
					drag.mode = MODE.NONE;
					if (onlyClick)
					{
						popwindow.dismiss();
					}
					break;
				// 多点松开
				case MotionEvent.ACTION_POINTER_UP:
					drag.mode = MODE.NONE;
					/** 执行缩放还原 **/
					if (drag.isScaleAnim)
					{
						drag.doScaleAnim();
					}
					break;
				}
				return true;
			}
		});
		drag.setOnKeyListener(new OnKeyListener()
		{

			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event)
			{
				if (keyCode == KeyEvent.KEYCODE_BACK)
				{
					if (popwindow != null && popwindow.isShowing())
					{
						popwindow.dismiss();
					}
				}
				return false;
			}
		});
		ImageLoader imageLoader = ImageLoader.getInstance();
		imageLoader.displayImage(GlobleData.SHOW_WORKS_IMG+publish_list.get(position).get("s_photo"), drag,publish_img_options);
		drag.setmActivity((Activity) context);
		drag.setOnClickCallBack(new OnClickCallback()
		{

			@Override
			public void callback()
			{
				popwindow.dismiss();
			}
		});
		ViewTreeObserver viewTreeObserver = drag.getViewTreeObserver();
		viewTreeObserver.addOnGlobalLayoutListener(new OnGlobalLayoutListener()
		{

			@Override
			public void onGlobalLayout()
			{
				if (state_height == 0)
				{
					// 获取状况栏高度
					Rect frame = new Rect();
					((Activity) context).getWindow().getDecorView()
							.getWindowVisibleDisplayFrame(frame);
					state_height = frame.top;
					drag.setScreen_H(ScreenUtil.getScreenHeight() - state_height);
					drag.setScreen_W(ScreenUtil.getScreenWidth());
				}

			}
		});
		return popwindow;
	}
	class Add_GoodListener implements View.OnClickListener
	{
		int position;
		TextView publish_username;
		TextView item_action_love;
		
		
		public Add_GoodListener(int position, TextView publish_username,TextView item_action_love)
		{
			this.position = position;
			this.publish_username = publish_username;
			this.item_action_love = item_action_love;
		}

		@Override 
		public void onClick(View v)
		{
			if(sharedPreferences.getString("userid", "").equals("")||sharedPreferences.getString("userid","") == null)
			{
				Toast.makeText(context, "未登录", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent();
				intent.setClass(context,LogActivty.class);
				context.startActivity(intent);
			}
			else
			{
				if(publish_list.get(position).get("is_good").equals("1"))
				{
					Toast.makeText(context, "你已赞过~",Toast.LENGTH_SHORT).show();
				}
				else
				{
					Integer good = Integer.valueOf(item_action_love.getText().toString())+1;//已经加一
					String s_worksid = (String)publish_username.getTag();
					String[] params = new String[]{String.valueOf(good),s_worksid};
					item_action_love.setText(String.valueOf(good));
					publish_list.get(position).put("s_good", String.valueOf(good));
					publish_list.get(position).put("is_good", "1");
					notifyDataSetChanged();
					String[] addgood_params = new String[]{String.valueOf(good),s_worksid};
					String[] user_isgood_params = new String[]{"1",s_worksid,sharedPreferences.getString("userid", "")};
					new AddGoodTask().execute(addgood_params);
					new AddUserIsGoodTask().execute(user_isgood_params);

				}
			}


		}
	}

	class CommentListener implements View.OnClickListener
	{
		int position;
		public CommentListener(int position)
		{
			super();
			this.position = position;
		}
		@Override
		public void onClick(View v)
		{
			if(sharedPreferences.getString("userid", "").equals(""))
			{
				Toast.makeText(context, "未登录", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent();
				intent.setClass(context,LogActivty.class);
				context.startActivity(intent);
			}
			else
			{
				/**
				 * "s_user_photo": "14.jpg", "s_worksid": 2, "s_good": "62",
				 * "s_time": "2014/5/7 星期三 5:45:00", "s_photo": "11.jpg",
				 * "s_shuoshuo": "随手一挥，大功告成", "is_good": "", "s_username": "执壶人"
				 */
				String s_user_photo =publish_list.get(position).get("s_user_photo");
				String s_worksid = publish_list.get(position).get("s_worksid");
				String s_good = publish_list.get(position).get("s_good");
				String s_time = publish_list.get(position).get("s_time");
				String s_photo = publish_list.get(position).get("s_photo");
				String s_shuoshuo = publish_list.get(position).get("s_shuoshuo");
				String is_good = publish_list.get(position).get("is_good");
				String s_username = publish_list.get(position).get("s_username");
				String s_title = publish_list.get(position).get("s_title");
				String s_userid = publish_list.get(position).get("s_userid");
//				String[] params  = new String[]{s_user_photo,s_worksid,s_good,s_time,s_photo,s_shuoshuo,is_good,s_username,s_title,s_userid};
//				new SetComItem().execute(params);
				
//				Intent service = new Intent("com.gao.service.PushService");
//				context.startService(service);
//				if(isServiceRunning(context))
//				{
//					Log.i("PushService", "isServiceRunning(context)    true");
//					context.stopService(service);
//					context.startService(service);
//				}
//				else
//				{
//					Log.i("PushService", "isServiceRunning(context)    false");
//					context.startService(service);
//				}
				Intent  intent = new Intent(context, CommentActivity.class);
				intent.putExtra("s_user_photo", s_user_photo);
				intent.putExtra("s_worksid", s_worksid);
				intent.putExtra("s_good", s_good);
				intent.putExtra("s_time", s_time);
				intent.putExtra("s_photo", s_photo);
				intent.putExtra("s_shuoshuo", s_shuoshuo);
				intent.putExtra("is_good", is_good);
				intent.putExtra("s_username", s_username);
				intent.putExtra("s_title", s_title);
				intent.putExtra("s_userid", s_userid);
				context.startActivity(intent);
			}
		}
		
	}
	class SetComItem extends AsyncTask<String, String, String>
	{
		@Override
		protected String doInBackground(String... params)
		{
			HashMap< String, String> map = new HashMap<String, String>();
			map.put("s_user_photo", params[0]);
			map.put("s_worksid", params[1]);
			map.put("s_good", params[2]);
			map.put("s_time", params[3]);
			map.put("s_photo", params[4]);
			map.put("s_shuoshuo", params[5]);
			map.put("is_good", params[6]);
			map.put("s_username", params[7]);
			map.put("s_title", params[8]);
			map.put("s_userid", params[9]);
			HttpUtils.getServletContentByGet(GlobleData.SET_COMMITEM_URL, map, "UTF-8");
			return "";
		}
		@Override
		protected void onPostExecute(String result)
		{
			// TODO Auto-generated method stub
			Toast.makeText(context," Session seted ", Toast.LENGTH_SHORT).show();
			super.onPostExecute(result);
		}
	}
	

	

	class ViewOtherDetailListener implements View.OnClickListener
	{
		int position;

		public ViewOtherDetailListener(int position)
		{
			this.position = position;
		}

		@Override
		public void onClick(View v)
		{
			// TODO Auto-generated method stub
			if(sharedPreferences.getString("userid", "").equals(""))
			{
				Toast.makeText(context, "未登录", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent();
				intent.setClass(context,LogActivty.class);
				context.startActivity(intent);
			}
			else
			{
				Map<String, String> map= publish_list.get(position);
				for (String key : map.keySet())
				{
					Log.i(TAG, "key= " + key + " and value= "
							+ map.get(key));
					Intent intent = new Intent();
					intent.putExtra("statekey", "other");
					intent.putExtra("s_userid", publish_list.get(position).get("s_userid"));
					intent.putExtra("s_user_photo", publish_list.get(position).get("s_user_photo"));
					intent.putExtra("s_username", publish_list.get(position).get("s_username"));
					intent.putExtra("s_mark", publish_list.get(position).get("s_mark"));
					intent.setClass(context, PersonActivity.class);
					context.startActivity(intent);
			}

			}
		}
		
	}
	
	class ShareListener implements View.OnClickListener
	{
		int position;
		
		public ShareListener(int position)
		{
			this.position = position;
		}
		@Override
		public void onClick(View v)
		{
			// TODO Auto-generated method stub
			if(sharedPreferences.getString("userid", "").equals(""))
			{
				Toast.makeText(context, "未登录", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent();
				intent.setClass(context,LogActivty.class);
				context.startActivity(intent);
			}
			else
			{
				showShare(position);
			}
			
		}
		
	}
	class Del_LongClickListener implements View.OnLongClickListener
	{
		int position;
		public Del_LongClickListener(int position)
		{
			this.position = position;
		}
		@Override
		public boolean onLongClick(View v)
		{
			// TODO Auto-generated method stub
			new AlertDialog.Builder(context).setTitle(publish_list.get(position).get("s_title")).setMessage("确定要删除这条动态吗？")
			.setPositiveButton("确定", new DialogInterface.OnClickListener()
			{
				@Override
				public void onClick(DialogInterface dialog, int which)
				{
					// TODO Auto-generated method stub
					dialog.dismiss();
					Log.i(TAG, "position----before-->"+position);
					Message msg = new Message();
					msg.obj = position;
					delHandler.sendMessage(msg);
					publish_list.remove(position);
					setRefreshData(publish_list);
					notifyDataSetChanged();
					Log.i(TAG, "position----after-->"+position);
					
					
					
					
	
					
					
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
			return false;
		}
	}
	class AddGoodTask extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute()
		{
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
		@Override
		protected String doInBackground(String... params)
		{
			// TODO Auto-generated method stub
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("s_good", params[0]);
			map.put("s_worksid", params[1]);
			String addgood_content = HttpUtils.getServletContentByGet(GlobleData.ADD_GOOD_URL, map, "UTF-8");
			return addgood_content;
		}
		@Override
		protected void onPostExecute(String result)
		{
			// TODO Auto-generated method stub
			String mark  =result.substring(0,1);
			if(mark.equals("1"))
			{
				Toast.makeText(context, "点赞成功", Toast.LENGTH_SHORT).show();
			}
			else
			{
				Toast.makeText(context, "点赞失败", Toast.LENGTH_SHORT).show();
			}
			super.onPostExecute(result);
		}
	}
	class AddUserIsGoodTask extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute()
		{
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
		@Override
		protected String doInBackground(String... params)
		{
			// TODO Auto-generated method stub
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("is_good", params[0]);
			map.put("s_worksid", params[1]);
			map.put("userid", params[2]);
			String addgood_content = HttpUtils.getServletContentByGet(GlobleData.ADD_USERIsGOOD_URL, map, "UTF-8");
			return addgood_content;
		}
		@Override
		protected void onPostExecute(String result)
		{
			// TODO Auto-generated method stub
			String mark  =result.substring(0,1);
			if(mark.equals("1"))
			{
//				Toast.makeText(context, "user_is good 插入成功", Toast.LENGTH_SHORT).show();
			}
			else
			{
//				Toast.makeText(context, "user_is good 插入失败", Toast.LENGTH_SHORT).show();
			}
			super.onPostExecute(result);
		}
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
			Log.i(TAG, "doinBack------->"+Integer.valueOf(params[0]));
			map.put("s_worksid", publish_list.get(Integer.valueOf(params[0])).get("s_worksid"));
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
				Toast.makeText(context, "删除成功", Toast.LENGTH_SHORT).show();
			}
			super.onPostExecute(result);
		}
	}
	private void showShare(int position)
	{
		ShareSDK.initSDK(context);
		OnekeyShare oks = new OnekeyShare();
		// 关闭sso授权
		oks.disableSSOWhenAuthorize();

		// 分享时Notification的图标和文字
		oks.setNotification(R.drawable.ic_launcher,
				context.getString(R.string.app_name));
		// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
		oks.setTitle("我正在使用【易涂】");
		// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
		oks.setTitleUrl("http://10.163.7.91:8080/scrawl");
		// text是分享文本，所有平台都需要这个字段
		oks.setText("我正在使用【易涂】，故推荐您使用");
		// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//		if(!publish_list.get(position).get("s_photo").equals(""))
//		{
			oks.setImageUrl(GlobleData.SHOW_WORKS_IMG+publish_list.get(position).get("s_photo"));
//		}
		
		// url仅在微信（包括好友和朋友圈）中使用
//		oks.setUrl("http://10.163.7.91:8080/scrawl/publish/10.jpg");
		// comment是我对这条分享的评论，仅在人人网和QQ空间使用
		oks.setComment("【易涂】 一款涂鸦爱好者的app");
		// site是分享此内容的网站名称，仅在QQ空间使用
		oks.setSite(context.getString(R.string.app_name));
		// siteUrl是分享此内容的网站地址，仅在QQ空间使用
		oks.setSiteUrl("http://10.163.7.91:8080/scrawl");
		
		// 启动分享GUI
		oks.show(context);
	}
}
