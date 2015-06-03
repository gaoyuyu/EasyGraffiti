package com.gao.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.gao.myyitu.R;

public class CustomPickPicDialog extends Dialog
{
	private Context context;
	private View customView;
	private LayoutInflater inflater;
	private TextView album_pic,camera_pic;
	public View getCustomView()
	{
		customView = inflater.inflate(R.layout.set_pic, null);
		return customView;
	}
	public void setCustomView(View customView)
	{
		this.customView = customView;
	}
	public CustomPickPicDialog(Context context)
	{
		super(context);
		// TODO Auto-generated constructor stub
	}
	public CustomPickPicDialog(Context context, int theme)
	{
		super(context,theme);
		// TODO Auto-generated constructor stub
		this.context = context;
		this.inflater = LayoutInflater.from(context);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.set_pic);
       View view = inflater.inflate(R.layout.set_pic, null);
       
        
	}
	public void setAlbumPicListener(android.view.View.OnClickListener onClickListener)
	{
		View view = inflater.inflate(R.layout.set_pic, null);
		album_pic = (TextView)view.findViewById(R.id.album_pic);
		album_pic.setOnClickListener(onClickListener);
	}
	public void setCameraPicListener(android.view.View.OnClickListener onClickListener)
	{
		View view = inflater.inflate(R.layout.set_pic, null);
		camera_pic = (TextView)view.findViewById(R.id.camera_pic);
		camera_pic.setOnClickListener(onClickListener);
		
	}
	
}
