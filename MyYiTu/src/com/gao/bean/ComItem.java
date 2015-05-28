package com.gao.bean;

public class ComItem
{
	/**
	 * 		 * 				comm_eEditor.putString("s_user_photo", s_user_photo);
				comm_eEditor.putString("s_worksid", s_worksid);
				comm_eEditor.putString("s_good", s_good);
				comm_eEditor.putString("s_time", s_time);
				comm_eEditor.putString("s_photo", s_photo);
				comm_eEditor.putString("s_shuoshuo", s_shuoshuo);
				comm_eEditor.putString("is_good", is_good);
				comm_eEditor.putString("s_username", s_username);
				comm_eEditor.putString("s_title", s_title);
				comm_eEditor.putString("s_userid", s_userid);
	 */
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
	public String getS_user_photo()
	{
		return s_user_photo;
	}
	public void setS_user_photo(String sUserPhoto)
	{
		s_user_photo = sUserPhoto;
	}
	public String getS_worksid()
	{
		return s_worksid;
	}
	public void setS_worksid(String sWorksid)
	{
		s_worksid = sWorksid;
	}
	public String getS_good()
	{
		return s_good;
	}
	public void setS_good(String sGood)
	{
		s_good = sGood;
	}
	public String getS_time()
	{
		return s_time;
	}
	public void setS_time(String sTime)
	{
		s_time = sTime;
	}
	public String getS_photo()
	{
		return s_photo;
	}
	public void setS_photo(String sPhoto)
	{
		s_photo = sPhoto;
	}
	public String getS_shuoshuo()
	{
		return s_shuoshuo;
	}
	public void setS_shuoshuo(String sShuoshuo)
	{
		s_shuoshuo = sShuoshuo;
	}
	public String getIs_good()
	{
		return is_good;
	}
	public void setIs_good(String isGood)
	{
		is_good = isGood;
	}
	public String getS_username()
	{
		return s_username;
	}
	public void setS_username(String sUsername)
	{
		s_username = sUsername;
	}
	public String getS_title()
	{
		return s_title;
	}
	public void setS_title(String sTitle)
	{
		s_title = sTitle;
	}
	public String getS_userid()
	{
		return s_userid;
	}
	public void setS_userid(String sUserid)
	{
		s_userid = sUserid;
	}
	@Override
	public String toString()
	{
		return "AD_ComItem [is_good=" + is_good + ", s_good=" + s_good
				+ ", s_photo=" + s_photo + ", s_shuoshuo=" + s_shuoshuo
				+ ", s_time=" + s_time + ", s_title=" + s_title
				+ ", s_user_photo=" + s_user_photo + ", s_userid=" + s_userid
				+ ", s_username=" + s_username + ", s_worksid=" + s_worksid
				+ "]";
	}
	
}
