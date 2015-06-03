package com.gao.adapter;

import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.gao.data.GlobleData;
import com.gao.imageloaderlistener.AnimateFirstDisplayListener;
import com.gao.myyitu.R;
import com.gao.utils.ScreenUtil;
import com.gao.view.DragImageView;
import com.gao.view.DragImageView.MODE;
import com.gao.view.DragImageView.OnClickCallback;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

public class ShowWorkAdapter extends BaseAdapter
{
	private Context context;
	private LayoutInflater inflater;
	private ViewHolder viewHolder;
	private List<Map<String,String>> showworks_list;
	private DisplayImageOptions login_head_options;
	private DisplayImageOptions showwork_options;
	private int state_height;
	
	private ImageLoadingListener animateFirstListener = new AnimateFirstDisplayListener();
	class ViewHolder
	{
		ImageView show_user_icon,showwork;
		TextView username,send_time,title,show_good;
	}
	
	public void setRefreshData(List<Map<String,String>> showworks_list)
	{
		this.showworks_list = showworks_list;
	}
	
	public ShowWorkAdapter(Context context,
			List<Map<String, String>> showworks_list)
	{
		this.context = context;
		this.showworks_list = showworks_list;
		this.inflater = LayoutInflater.from(context);
		/**
		 * �����û�ͷ���������
		 */
		login_head_options = new DisplayImageOptions.Builder()  
		 .showImageOnLoading(R.drawable.user_icon_default_main) //����ͼƬ�������ڼ���ʾ��ͼƬ  
		 .showImageForEmptyUri(R.drawable.user_icon_default_main)//����ͼƬUriΪ�ջ��Ǵ����ʱ����ʾ��ͼƬ  
		.showImageOnFail(R.drawable.user_icon_default_main)  //����ͼƬ����/��������д���ʱ����ʾ��ͼƬ
		.cacheInMemory(true)//�������ص�ͼƬ�Ƿ񻺴����ڴ���  
		.cacheOnDisk(true)//�������ص�ͼƬ�Ƿ񻺴���SD����  
		.displayer(new FadeInBitmapDisplayer(100))//�Ƿ�ͼƬ���غú���Ķ���ʱ��
		.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2)
		.build();//�������  
		/**
		 * ����ͿѻͼƬ��������
		 */
		showwork_options = new DisplayImageOptions.Builder()  
		.showImageOnLoading(R.drawable.bg_pic_loading) //����ͼƬ�������ڼ���ʾ��ͼƬ  
		.showImageForEmptyUri(R.drawable.bg_pic_loading)//����ͼƬUriΪ�ջ��Ǵ����ʱ����ʾ��ͼƬ  
		.showImageOnFail(R.drawable.bg_pic_loading)  //����ͼƬ����/��������д���ʱ����ʾ��ͼƬ
		.bitmapConfig(Bitmap.Config.RGB_565)//����ͼƬ�Ľ�������//
		.cacheInMemory(true)//�������ص�ͼƬ�Ƿ񻺴����ڴ���  
		.cacheOnDisc(true)//�������ص�ͼƬ�Ƿ񻺴���SD����  
		.displayer(new FadeInBitmapDisplayer(100))//�Ƿ�ͼƬ���غú���Ķ���ʱ��
		.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
		.build();//�������  
	}

	@Override
	public int getCount()
	{
		// TODO Auto-generated method stub
		return showworks_list.size();
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
		if(null == convertView)
		{
			convertView = inflater.inflate(R.layout.showwork_item, null);
			viewHolder = new ViewHolder();
			viewHolder.username = (TextView)convertView.findViewById(R.id.username);
			viewHolder.send_time = (TextView)convertView.findViewById(R.id.send_time);
			viewHolder.show_user_icon = (ImageView)convertView.findViewById(R.id.show_user_icon);
			viewHolder.showwork = (ImageView)convertView.findViewById(R.id.showwork);
			viewHolder.title = (TextView)convertView.findViewById(R.id.title);
			viewHolder.show_good = (TextView)convertView.findViewById(R.id.show_good);
			convertView.setTag(viewHolder);
		}
		else
		{
			viewHolder = (ViewHolder)convertView.getTag();
		}
		/**
		 * 
		 *         "s_mark": "try sth",
        "s_userid": "30",
        "s_user_photo": "head_icon_1811.jpg",
        "s_worksid": 58,
        "s_good": 556,
        "s_time": "2014-10-18 19:03:57",
        "s_photo": "publish_5986.jpg",
        "s_shuoshuo": "���鿥�¾���",
        "s_title": "��֮��",
        "s_username": "��Ŀ������"
		 */
		viewHolder.send_time.setText(showworks_list.get(position).get("s_time"));
		viewHolder.username.setText(showworks_list.get(position).get("s_username"));
		viewHolder.title.setText(showworks_list.get(position).get("s_title"));
		viewHolder.show_good.setText(showworks_list.get(position).get("s_good"));
		ImageLoader imageLoader = ImageLoader.getInstance();
		imageLoader.displayImage(GlobleData.SHOW_WORKS_IMG+showworks_list.get(position).get("s_photo"), viewHolder.showwork,showwork_options,animateFirstListener);
		imageLoader.displayImage(GlobleData.PERSON_HEAD_IMG+showworks_list.get(position).get("s_user_photo"), viewHolder.show_user_icon,login_head_options,animateFirstListener);
		final PopupWindow popwindow = initPopwindow(position);
		viewHolder.showwork.setOnClickListener(new OnClickListener()
		{
			
			@Override
			public void onClick(View v)
			{
				// TODO Auto-generated method stub
				popwindow.showAtLocation(v, Gravity.NO_GRAVITY, 0, 0);
			}
		});
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
				/** �����㡢��㴥�� **/
				switch (event.getAction() & MotionEvent.ACTION_MASK)
				{
				case MotionEvent.ACTION_DOWN:
					onlyClick = true;
					drag.onTouchDown(event);
					break;
				// ��㴥��
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
				// ����ɿ�
				case MotionEvent.ACTION_POINTER_UP:
					drag.mode = MODE.NONE;
					/** ִ�����Ż�ԭ **/
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
		imageLoader.displayImage(GlobleData.SHOW_WORKS_IMG+showworks_list.get(position).get("s_photo"), drag,showwork_options,animateFirstListener);
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
					// ��ȡ״�����߶�
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
	
}
