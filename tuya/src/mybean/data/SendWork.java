package mybean.data;

public class SendWork
{
	public int s_worksid;
	public String s_shuoshuo;
	public String s_time;
	public String s_photo;
	public String s_good;
	public String s_username;
	public String s_user_photo;
	public int getS_worksid()
	{
		return s_worksid;
	}
	public void setS_worksid(int sWorksid)
	{
		s_worksid = sWorksid;
	}
	public String getS_shuoshuo()
	{
		return s_shuoshuo;
	}
	public void setS_shuoshuo(String sShuoshuo)
	{
		s_shuoshuo = sShuoshuo;
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
	public String getS_good()
	{
		return s_good;
	}
	public void setS_good(String sGood)
	{
		s_good = sGood;
	}
	public String getS_username()
	{
		return s_username;
	}
	public void setS_username(String sUsername)
	{
		s_username = sUsername;
	}
	public String getS_user_photo()
	{
		return s_user_photo;
	}
	public void setS_user_photo(String sUserPhoto)
	{
		s_user_photo = sUserPhoto;
	}
	@Override
	public String toString()
	{
		return "SendWork [s_good=" + s_good + ", s_photo=" + s_photo
				+ ", s_shuoshuo=" + s_shuoshuo + ", s_time=" + s_time
				+ ", s_user_photo=" + s_user_photo + ", s_username="
				+ s_username + ", s_worksid=" + s_worksid + "]";
	}
	
}
