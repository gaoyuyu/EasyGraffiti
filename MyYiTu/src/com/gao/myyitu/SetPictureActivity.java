package com.gao.myyitu;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.DefaultHttpClient;

import com.gao.application.ExitApplication;
import com.gao.data.GlobleData;
import com.gao.http.HttpPostUtils;
import com.gao.http.HttpUtils;
import com.gao.utils.ProgressDialogUtils;
import com.gao.view.CustomeProgressDialog;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.utils.DiskCacheUtils;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

public class SetPictureActivity extends Activity implements OnClickListener
{
	private static final String TAG="SetPictureActivity";
	public static final int NONE = 0;
	public static final int PHOTOHRAPH = 1;// 拍照
	public static final int PHOTOZOOM = 2; // 缩放
	public static final int PHOTORESOULT = 3;// 结果
	private byte[] image_data;// 存储图片的数据
	public static final String IMAGE_UNSPECIFIED = "image/*";
	private TextView album_pic,camera_pic;
	private SharedPreferences sharedPreferences;
//	private String pic_name;
	private CustomeProgressDialog customeProgressDialog=null;
	private long firstTime = 0;
	private final String IMAGE_TYPE = "image/*";
	private String upload_icon_pic_name;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		ExitApplication.getInstanse().addActivity(this);
		setContentView(R.layout.set_pic);
		customeProgressDialog = CustomeProgressDialog.ctreateDialog(this);
		album_pic = (TextView)findViewById(R.id.album_pic);
		camera_pic = (TextView)findViewById(R.id.camera_pic);
		camera_pic.setOnClickListener(this);
		album_pic.setOnClickListener(this);
		sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
	}
	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		switch (v.getId())
		{
		case R.id.album_pic:
			Intent album_intent = new Intent(Intent.ACTION_GET_CONTENT);
			album_intent.setType(IMAGE_TYPE);
			startActivityForResult(album_intent, PHOTOZOOM);
			break;
		case R.id.camera_pic:
			Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(camera_intent, PHOTORESOULT);
			
			break;
		default:
			break;
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (resultCode == NONE)
		{
			return;
		}
			
		// 拍照
//		if (requestCode == PHOTOHRAPH &&resultCode == RESULT_OK)
//		{
//			Bundle bundle = data.getExtras();
//			Bitmap bitmap = (Bitmap) bundle.get("data");
//			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//			bitmap.compress(Bitmap.CompressFormat.PNG, 50, outputStream);
//			image_data = outputStream.toByteArray();
//			Random random = new Random();
//			String upload_icon_pic_name = "head_icon_"+Math.round(Math.random()*10000)+".jpg";
//			HttpPostUtils.sendFormByPost(GlobleData.UPLOAD_PIC_URL, image_data, upload_icon_pic_name);
//			SharedPreferences.Editor editor = sharedPreferences.edit();
//			editor.putString("photo", upload_icon_pic_name);
//			editor.commit();
//			new SetPhotoTask().execute(upload_icon_pic_name);
//			Intent camera_result = new Intent();
//			camera_result.setClass(SetPictureActivity.this, SettingActivity.class);
//			startActivity(camera_result);
//		}

		if (data == null||data.equals(""))
		{
			return;
		}

			// 读取相册缩放图片
			if (requestCode == PHOTOZOOM)
			{
				startPhotoZoom(data.getData());
			}
			// 处理结果
			if (requestCode == PHOTORESOULT)
			{
				Log.i(TAG, "-----------------------1-----------------------");
				Bundle extras = data.getExtras();
				if (extras != null)
				{
					Log.i(TAG, "-----------------------2-----------------------");
					Bitmap photo = extras.getParcelable("data");
					ByteArrayOutputStream stream = new ByteArrayOutputStream();
					photo.compress(Bitmap.CompressFormat.PNG, 50, stream);// (0 -
																			// 100)压缩文件
					
					//在这里上传操作并返回设置界面。。。。
					image_data = stream.toByteArray();
					Random random = new Random();
					upload_icon_pic_name = "head_icon_"+Math.round(Math.random()*10000)+".jpg";
//					HttpPostUtils.sendFormByPost(GlobleData.UPLOAD_PIC_URL, image_data, pic_name);
//					HttpPostUtils.sendFormByPost(GlobleData.UPLOAD_PIC_URL, image_data, upload_icon_pic_name);
					new UpLoadPicTask().execute();
				}

			}
		super.onActivityResult(requestCode, resultCode, data);
	}
	public class UpLoadPicTask extends AsyncTask<String, String, String>
	{
		@Override
		protected String doInBackground(String... params)
		{
			// TODO Auto-generated method stub
			String msg = null;
			try
			{
				HttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost(GlobleData.UPLOAD_PIC_URL);
				MultipartEntity entity = new MultipartEntity();
				entity.addPart("myfile", new ByteArrayBody(image_data,
						upload_icon_pic_name));
				httpPost.setEntity(entity);
				HttpResponse response= httpClient.execute(httpPost);
				InputStream in= response.getEntity().getContent();
				msg = HttpUtils.stream2String(in,"UTF-8");
				Log.i(TAG, "msg------->"+msg+"---leng---"+msg.length());
			} catch (Exception e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

			return msg;
		}
		@Override
		protected void onPostExecute(String result)
		{
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			if(result.equals("1"))
			{
				Toast.makeText(SetPictureActivity.this, "上传完成", Toast.LENGTH_SHORT).show();
				SharedPreferences.Editor editor = sharedPreferences.edit();
				editor.putString("photo", upload_icon_pic_name);
				editor.commit();
				new SetPhotoTask().execute(upload_icon_pic_name);
			}
			else
			{
				Toast.makeText(SetPictureActivity.this, "上传失败", Toast.LENGTH_SHORT).show();
			}
		}
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK)
		{
			if ((System.currentTimeMillis() - firstTime) > 2000)
			{// 如果两次按键时间间隔大于2000毫秒，则不退出
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				firstTime = System.currentTimeMillis();// 更新mExitTime
			} else
			{
//				Intent intent = new Intent(Intent.ACTION_MAIN);
//				intent.addCategory(Intent.CATEGORY_HOME);
//				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//				startActivity(intent);
				ExitApplication.getInstanse().exit();
				android.os.Process.killProcess(android.os.Process.myPid());
			}

			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	public void startPhotoZoom(Uri uri)
	{
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, IMAGE_UNSPECIFIED);
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", 160);
		intent.putExtra("outputY", 160);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, PHOTORESOULT);
	}
	class SetPhotoTask extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute()
		{
			// TODO Auto-generated method stub
			super.onPreExecute();
		}
		@Override
		protected String doInBackground(String... params)
		{
			// TODO Auto-generated method stub
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("userid", sharedPreferences.getString("userid", ""));
			map.put("photo", params[0]);
			String photo_content = HttpUtils.getServletContentByGet(GlobleData.SET_PHOTO_URL, map, "utf-8");
			return photo_content;
		}
		@Override
		protected void onPostExecute(String result)
		{
			String mark = result.substring(0,1);
			if(mark.equals("1"))
			{
				Toast.makeText(SetPictureActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
//				Intent album_result = new Intent();
//				album_result.setClass(SetPictureActivity.this, SettingActivity.class);
//				startActivity(album_result);
				finish();
				
			}
			else
			{
				Toast.makeText(SetPictureActivity.this, "设置失败", Toast.LENGTH_SHORT).show();
			}
			super.onPostExecute(result);
		}
	}
}
