package com.gao.utils;

import android.content.Context;

import com.gao.view.CustomeProgressDialog;

public class ProgressDialogUtils
{
	/**
	 * 自定义的ProgressDialog 相当于show()
	 * 
	 * @param message
	 */
	@SuppressWarnings("unused")
	public static void startProgressDialog(String message,CustomeProgressDialog customeProgressDialog)
	{
//		if (customeProgressDialog == null)
//		{
//			customeProgressDialog = CustomeProgressDialog.ctreateDialog(context);
			customeProgressDialog.setMessage(message);
//		}
			customeProgressDialog.show();
	}

	/**
	 * 相当于cancel()
	 */
	public static void stopProgressDialog(CustomeProgressDialog customeProgressDialog)
	{
//		if (customeProgressDialog != null)
//		{
			customeProgressDialog.dismiss();
//			customeProgressDialog = null;
//		}
	}
}
