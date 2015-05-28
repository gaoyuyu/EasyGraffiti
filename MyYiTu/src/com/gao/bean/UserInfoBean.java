package com.gao.bean;

public class UserInfoBean
{
	public String email;//用户邮箱
	public String password;//用户密码
	public boolean success=false;
	public String username;//昵称
	public String sex;//性别
	public String place;//长居地
	public String information;//工作信息
	public String mark;//个人标签
	public String introduction;//个人说明
	public String group;//参与群组
	public String photo;//用户头像
	public String userid;//用户的id
	public String userid2;//被评论者的id
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
	public boolean isSuccess()
	{
		return success;
	}
	public void setSuccess(boolean success)
	{
		this.success = success;
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
	public String getPhoto()
	{
		return photo;
	}
	public void setPhoto(String photo)
	{
		this.photo = photo;
	}
	public String getUserid()
	{
		return userid;
	}
	public void setUserid(String userid)
	{
		this.userid = userid;
	}
	public String getUserid2()
	{
		return userid2;
	}
	public void setUserid2(String userid2)
	{
		this.userid2 = userid2;
	}
	@Override
	public String toString()
	{
		return "UserInfoBean [email=" + email + ", password=" + password
				+ ", success=" + success + ", username=" + username + ", sex="
				+ sex + ", place=" + place + ", information=" + information
				+ ", mark=" + mark + ", introduction=" + introduction
				+ ", group=" + group + ", photo=" + photo + ", userid="
				+ userid + ", userid2=" + userid2 + "]";
	}
	
}
