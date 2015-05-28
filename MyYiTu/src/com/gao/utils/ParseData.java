package com.gao.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gao.bean.ComCountBean;
import com.gao.bean.ComItem;
import com.gao.bean.UserInfoBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ParseData
{

	public ParseData()
	{
		// TODO Auto-generated constructor stub
	}

	public static UserInfoBean getUserInfoData(String json)
	{
		Gson gson = new Gson();
		UserInfoBean userInfoBean = gson.fromJson(json, UserInfoBean.class);
		return userInfoBean;
	}
	public static ComCountBean getComCountData(String json)
	{
		Gson gson = new Gson();
		ComCountBean comCountBean = gson.fromJson(json, ComCountBean.class);
		return comCountBean;
	}
	public static ComItem getComItemData(String json)
	{
		Gson gson = new Gson();
		ComItem comItem = gson.fromJson(json, ComItem.class);
		return comItem;
	}

	public static List<Map<String, String>> getShowWorksList(String json)
	{
		List<Map<String, String>> list = null;
		Gson gson = new Gson();
		list = gson.fromJson(json, new TypeToken<List<Map<String, String>>>(){}.getType());
		return list;
	}

}
