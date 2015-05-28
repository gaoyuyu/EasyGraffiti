package mybean.data;

public class Userinfo
{
	String email="";//用户邮箱
	String password="";//用户密码
	boolean success=false;
	String username="";//昵称
	String sex="";//性别
	String place="";//长居地
	String information="";//工作信息
	String mark="";//个人标签
	String introduction="";//个人说明
	String group="";//参与群组
	String photo="";//用户头像
	String userid="";//用户的id
	String userid2="";//被评论者的id
	public String getUserid2()
	{
		return userid2;
	}
	public void setUserid2(String userid2)
	{
		this.userid2 = userid2;
	}
	public String getUserid()
	{
		return userid;
	}
	public void setUserid(String userid)
	{
		this.userid = userid;
	}
	public String getPhoto()
	{
		return photo;
	}
	public void setPhoto(String photo)
	{
		this.photo = photo;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username = username;
	}
	public String getSex()
	{
		return sex;
	}
	public void setSex(String sex)
	{
		this.sex = sex;
	}
	public String getPlace()
	{
		return place;
	}
	public void setPlace(String place)
	{
		this.place = place;
	}
	public String getInformation()
	{
		return information;
	}
	public void setInformation(String information)
	{
		this.information = information;
	}
	public String getMark()
	{
		return mark;
	}
	public void setMark(String mark)
	{
		this.mark = mark;
	}
	public String getIntroduction()
	{
		return introduction;
	}
	public void setIntroduction(String introduction)
	{
		this.introduction = introduction;
	}
	public String getGroup()
	{
		return group;
	}
	public void setGroup(String group)
	{
		this.group = group;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public String getPassword()
	{
		return password;
	}
	public void setPassword(String password)
	{
		this.password = password;
	}
	public boolean getSuccess()
	{
		return success;
	}
	public void setSuccess(boolean success)
	{
		this.success = success;
	}

}
