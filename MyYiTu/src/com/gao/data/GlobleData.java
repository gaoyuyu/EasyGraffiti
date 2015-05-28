package com.gao.data;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class GlobleData
{
	public static final String MAIN_ADDRESS="http://10.54.4.188:8080";
	public  static final String LOGIN_URL=MAIN_ADDRESS+"/scrawl/AndroidLoginServlet";
	public static final String REG_URL=MAIN_ADDRESS+"/scrawl/AndroidRegisterServlet";
	public static final String PERSON_HEAD_IMG=MAIN_ADDRESS+"/images/";
	public static final String SHOW_WORKS_IMG = MAIN_ADDRESS+"/scrawl/publish/";
	public static final String SHOW_WORKS_URL = MAIN_ADDRESS+"/scrawl/AndroidServletShowWorks";
	public static final String MARK_SIGN_URL=MAIN_ADDRESS+"/scrawl/AndroidSetMarkServlet";
	public static final String SET_USERNAME_URL=MAIN_ADDRESS+"/scrawl/AndroidSetUsernameServlet";
	public static final String SET_SEX_URL = MAIN_ADDRESS+"/scrawl/AndroidSetSexServlet";
	public static final String UPLOAD_PIC_URL=MAIN_ADDRESS+"/scrawl/AndroidUploadServlet";
	public static final String PUBLISH_UPLOAD_PIC_URL=MAIN_ADDRESS+"/scrawl/AndroidPublishPicServlet";
	public static final String SET_PHOTO_URL=MAIN_ADDRESS+"/scrawl/AndroidSetPhotoServlet";
	public static final String PUBLISH_URL=MAIN_ADDRESS+"/scrawl/AndroidSendWorkServlet";
	public static final String GET_PUBLISH_URL=MAIN_ADDRESS+"/scrawl/AndroidGetSendWorkServlet";
	public static final String ADD_GOOD_URL=MAIN_ADDRESS+"/scrawl/AndroidAddGoodServlet";
	public static final String ADD_USERIsGOOD_URL=MAIN_ADDRESS+"/scrawl/AndroidAddUserGoodServlet";
	public static final String GET_PUBLISH_4LOGOUTURL=MAIN_ADDRESS+"/scrawl/AndroidGetSendWork4LogoutServlet";
	public static final String GET_COMMENTLIST_URL=MAIN_ADDRESS+"/scrawl/AndroidGetWorkCommentServlet";
	public static final String ADD_COMMENT_URL=MAIN_ADDRESS+"/scrawl/AndroidAddUserCommentServlet";
	public static final String GET_PERSONWORK_URL=MAIN_ADDRESS+"/scrawl/AndroidGetPersonWorkServlet";
	public static final String FIND_PSD_URL=MAIN_ADDRESS+"/scrawl/AndroidFindPsdServlet";
	public static final String DEL_WORK_URL=MAIN_ADDRESS+"/scrawl/AndroidDeleteWorkServlet";
	public static final String PUSH_URL=MAIN_ADDRESS+"/scrawl/AndroidPushServlet";
	public static final String SET_COMMITEM_URL=MAIN_ADDRESS+"/scrawl/AndroidSetComItem";
	public static final String GET_COMMITEM_URL=MAIN_ADDRESS+"/scrawl/AndroidGetComItem";
	
	
	
	public static boolean hasInternet(Activity a)
	{
		ConnectivityManager manager = (ConnectivityManager) a
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = manager.getActiveNetworkInfo();
		if (info == null || !info.isConnected())
		{
			return false;
		}
		if (info.isRoaming())
		{
			return true;
		}
		return true;
	}
}
