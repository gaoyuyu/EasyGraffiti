package com.gao.view;



import com.gao.myyitu.R;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomeProgressDialog extends Dialog
{

	private Context mContent;
	private static CustomeProgressDialog customeProgressDialog = null;

	public CustomeProgressDialog(Context context)
	{
		super(context);
		mContent = context;
	}

	public CustomeProgressDialog(Context context, int theme)
	{
		super(context, theme);
	}

	public static CustomeProgressDialog ctreateDialog(Context context)
	{
		customeProgressDialog = new CustomeProgressDialog(context,
				R.style.CustomProgressDialog);
		customeProgressDialog.setContentView(R.layout.loading_dialog);
		ImageView img = (ImageView)customeProgressDialog.findViewById(R.id.img);
		// º”‘ÿ∂Øª≠
		Animation animation = AnimationUtils.loadAnimation(context, R.anim.loading_animation);
		img.startAnimation(animation);
		customeProgressDialog.getWindow().getAttributes().gravity = Gravity.CENTER;
		return customeProgressDialog;

	}
	
	public CustomeProgressDialog setTitle(String title)
	{
		return customeProgressDialog;
	}
	public CustomeProgressDialog setMessage(String message)
	{
		TextView tip_textview = (TextView)customeProgressDialog.findViewById(R.id.tipTextView);
		if(tip_textview != null)
		{
			tip_textview.setText(message);
		}
		return customeProgressDialog;
	}

}
