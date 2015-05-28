package com.gao.application;

import java.util.LinkedList;
import java.util.List;

import android.app.Activity;
import android.app.Application;

/**
 * ExitApplication�ࣨ�洢ÿһ��Activity����ʵ�ֹر�����Activity������
 * @author ʹ�õ���ģʽ����һ��Activity������󣬸ö�������һ��Activity����������ʵ���Լ�����ʹ��LinkedList�ȣ�
 *         ר�Ÿ���洢�¿�����ÿһ��Activity������������⣬���ڲ���.
 */
public class ExitApplication extends Application
{
	private List<Activity> activityList = new LinkedList<Activity>();
	private static ExitApplication instance;

	private ExitApplication()
	{

	}

	// ����ģʽ�л�ȡΨһ��MyApplicationʵ��
	public static ExitApplication getInstanse()
	{
		if (instance == null)
		{
			instance = new ExitApplication();
		}
		return instance;
	}

	// ���Activity��������
	public void addActivity(Activity activity)
	{
		activityList.add(activity);
	}

	// ��������Activity��finish
	public void exit()
	{
		for (Activity activity : activityList)
		{
			activity.finish();
		}
//		System.exit(0);
	}
}
