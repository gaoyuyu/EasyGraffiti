package com.gao.application;

import java.io.File;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.util.Log;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;
import com.nostra13.universalimageloader.utils.StorageUtils;

public class MyApplication extends Application
{
	@Override
	public void onCreate()
	{
		super.onCreate();
		//���û���·��
		File cacheDir = StorageUtils.getOwnCacheDirectory(getApplicationContext(), "myyitu/Cache");
		// This configuration tuning is custom. You can tune every option, you may tune some of them, 
		// or you can create default configuration by
		//  ImageLoaderConfiguration.createDefault(this);
		// method.
		ImageLoaderConfiguration config = new ImageLoaderConfiguration  
			    .Builder(getApplicationContext())  
			    .memoryCacheExtraOptions(480, 800) // max width, max height���������ÿ�������ļ�����󳤿�  
			     .threadPoolSize(2)//�̳߳��ڼ��ص��������Ƽ�1-5��ֹOOM    
			     .threadPriority(Thread.NORM_PRIORITY - 2)
			    .denyCacheImageMultipleSizesInMemory()  
//			    .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // You can pass your own memory cache implementation/�����ͨ���Լ����ڴ滺��ʵ��  
			    .memoryCache(new WeakMemoryCache())
			    .memoryCacheSize(2 * 1024 * 1024)    
			    .discCacheSize(50 * 1024 * 1024)    
//			    .discCacheFileNameGenerator(new Md5FileNameGenerator())//�������ʱ���URI������MD5 ����  
			    .tasksProcessingOrder(QueueProcessingType.LIFO)  
			    .discCacheFileCount(100) //������ļ�����  
			    .discCache(new UnlimitedDiscCache(cacheDir))//�Զ��建��·��  
			    .defaultDisplayImageOptions(DisplayImageOptions.createSimple())  
			    .imageDownloader(new BaseImageDownloader(getApplicationContext(), 5 * 1000, 30 * 1000)) // connectTimeout (5 s), readTimeout (30 s)��ʱʱ��  
			    .writeDebugLogs() // Remove for release app  
			    .build();//��ʼ����  
			    // Initialize ImageLoader with configuration.  
		
		//Initialize ImageLoader with configuration
		ImageLoader.getInstance().init(config);
		
		SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
//		if(!sharedPreferences.getString("userid", "").equals(""))
//		{
//			Log.i("PushService", "==========start in application=====================");
//			Intent service = new Intent("com.gao.service.PushService");
//			startService(service);
//		}
		
		

		
	}
}
