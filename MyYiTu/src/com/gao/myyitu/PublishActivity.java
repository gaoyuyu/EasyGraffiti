package com.gao.myyitu;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NavUtils;
import android.support.v4.app.TaskStackBuilder;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;
import com.gao.application.ExitApplication;
import com.gao.data.GlobleData;
import com.gao.http.HttpPostUtils;
import com.gao.http.HttpUtils;
import com.gao.utils.ProgressDialogUtils;
import com.gao.view.CustomPickPicDialog;
import com.gao.view.CustomeProgressDialog;

public class PublishActivity extends SherlockFragmentActivity implements OnClickListener
{
	private ActionBar actionbar;
	private FragmentManager fm;
	private FragmentTransaction ft;
	private EditText publish_content,publish_title;
	private ImageView plus_pic;
	private Button publish;
	public static final int NONE = 0;
	public static final int PHOTOHRAPH = 1;// 拍照
	public static final int PHOTOZOOM = 2; // 缩放
	public static final int PHOTORESOULT = 3;// 结果
	private final String IMAGE_TYPE = "image/*";
	private final int IMAGE_CODE = 0;   //这里的IMAGE_CODE是自己任意定义的
	private byte[] image_data;// 存储图片的数据
	public static final String IMAGE_UNSPECIFIED = "image/*";
	private AlertDialog alertDialog;
	private CustomPickPicDialog  customPickPicDialog;
	private CustomeProgressDialog customeProgressDialog=null;
	private String upload_publish_pic_name="";
	private SharedPreferences sharedPreferences;
	private Uri photoUri;
	private String photoPath;
	private Bitmap bitMap;
	private static final String TAG="PublishActivity";
	private long firstTime=0;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ExitApplication.getInstanse().addActivity(this);
		setContentView(R.layout.publish_fragment);
		customeProgressDialog = CustomeProgressDialog.ctreateDialog(this);
		actionbar = getSupportActionBar();
		actionbar.setTitle("发粪涂墙");
		actionbar.setDisplayHomeAsUpEnabled(true);
		initView();
		setListener();
	}
	private void setListener()
	{
		// TODO Auto-generated method stub
		plus_pic.setOnClickListener(this);
		publish.setOnClickListener(this);
	}
	private void initView()
	{
		// TODO Auto-generated method stub
		publish_content = (EditText)findViewById(R.id.publish_content);
		publish_title = (EditText)findViewById(R.id.publish_title);
		plus_pic = (ImageView)findViewById(R.id.plus_pic);
		publish = (Button)findViewById(R.id.publish);
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
				ExitApplication.getInstanse().exit();
				android.os.Process.killProcess(android.os.Process.myPid());
			}

			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
		case android.R.id.home:
			Intent intent = new Intent(PublishActivity.this,MainActivity.class);
			startActivity(intent);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	public void onClick(View v)
	{
		// TODO Auto-generated method stub
		switch (v.getId())
		{
		case R.id.plus_pic:
			alertDialog = new AlertDialog.Builder(PublishActivity.this,R.style.ActivityDialog).create();
			alertDialog.setCanceledOnTouchOutside(false);
			View view = LayoutInflater.from(PublishActivity.this).inflate(R.layout.set_pic, null);
			alertDialog.show();
			alertDialog.setContentView(view);
			alertDialog.getWindow().setGravity(Gravity.CENTER);
			TextView album_pic = (TextView)view.findViewById(R.id.album_pic);
			TextView camera_pic = (TextView)view.findViewById(R.id.camera_pic);
			album_pic.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					// TODO Auto-generated method stub
					alertDialog.dismiss();
					Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
					getAlbum.setType(IMAGE_TYPE);
					startActivityForResult(getAlbum, IMAGE_CODE);
					
				}
			});
			camera_pic.setOnClickListener(new OnClickListener()
			{
				
				@Override
				public void onClick(View v)
				{
					// TODO Auto-generated method stub
					alertDialog.dismiss();
					Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(
							Environment.getExternalStorageDirectory(), "temp.jpg")));
					System.out.println("=============" + Environment.getExternalStorageDirectory());
					startActivityForResult(intent, PHOTOHRAPH);
				}
			});
	        break;
		case R.id.publish:
			if((publish_content.getText().toString().equals(""))||(publish_title.getText().toString().equals("")))
			{
				Toast.makeText(PublishActivity.this, "哎呀，是不是漏了点神马呀~",Toast.LENGTH_SHORT).show();
			}
			else
			{
				new PublishTask().execute();
			}
			break;
		default:
			break;
		}
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		if (resultCode == NONE)
			return;
		// 拍照
		if (requestCode == PHOTOHRAPH)
		{
			// 设置文件保存路径这里放在跟目录下
			File picture = new File(Environment.getExternalStorageDirectory()
					+ "/temp.jpg");
			System.out.println("------------------------" + picture.getPath());
			startPhotoZoom(Uri.fromFile(picture));
		}
		ContentResolver resolver = getContentResolver();
		if(requestCode == IMAGE_CODE)
		{
			Uri originalUri = data.getData(); 
			String[] proj = {MediaStore.Images.Media.DATA};
			//android多媒体数据库的封装接口，具体的看Android文档
            Cursor cursor = managedQuery(originalUri, proj, null, null, null); 
            //获得用户选择的图片的索引值
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            //将光标移至开头 ，这个很重要，不小心很容易引起越界
            cursor.moveToFirst();
            //最后根据索引值获取图片路径
            String path = cursor.getString(column_index);
            Bitmap photo = getBitmapFromFile(new File(path), 792, 917);
				photo = rotateBitmapByDegree(photo, getBitmapDegree(path));
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				photo.compress(Bitmap.CompressFormat.JPEG, 75, stream);// (0 -
																		// 100)压缩文件
				plus_pic.setImageBitmap(photo);
				image_data = stream.toByteArray();
				upload_publish_pic_name = "publish_"+ Math.round(Math.random() * 10000) + ".jpg";
				HttpPostUtils.sendFormByPost(GlobleData.PUBLISH_UPLOAD_PIC_URL, image_data,upload_publish_pic_name);
				 
		}
		if (data == null)
			return;
		// 读取相册缩放图片
		if (requestCode == PHOTOZOOM)
		{
			startPhotoZoom(data.getData());
		}
		// 处理结果
		if (requestCode == PHOTORESOULT)
		{
			Bundle extras = data.getExtras();
			if (extras != null)
			{
				Bitmap photo = getBitmapFromFile(new File(Environment.getExternalStorageDirectory()
					+ "/temp.jpg"), 792, 917);
				photo = rotateBitmapByDegree(photo, getBitmapDegree(Environment.getExternalStorageDirectory()+ "/temp.jpg"));
				ByteArrayOutputStream stream = new ByteArrayOutputStream();
				photo.compress(Bitmap.CompressFormat.JPEG, 75, stream);// (0 -
																		// 100)压缩文件
				plus_pic.setImageBitmap(photo);
				image_data = stream.toByteArray();
				upload_publish_pic_name = "publish_"+ Math.round(Math.random() * 10000) + ".jpg";
				HttpPostUtils.sendFormByPost(GlobleData.PUBLISH_UPLOAD_PIC_URL, image_data,upload_publish_pic_name);
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
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
		intent.putExtra("outputX", 64);
		intent.putExtra("outputY", 64);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, PHOTORESOULT);
	}
	/**
	 * 读取图片的旋转的角度
	 * 
	 * @param path
	 *            图片绝对路径
	 * @return 图片的旋转角度
	 */
	private int getBitmapDegree(String path)
	{
		int degree = 0;
		try
		{
			// 从指定路径下读取图片，并获取其EXIF信息
			ExifInterface exifInterface = new ExifInterface(path);
			// 获取图片的旋转信息
			int orientation = exifInterface.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);
			switch (orientation)
			{
			case ExifInterface.ORIENTATION_ROTATE_90:
				degree = 90;
				break;
			case ExifInterface.ORIENTATION_ROTATE_180:
				degree = 180;
				break;
			case ExifInterface.ORIENTATION_ROTATE_270:
				degree = 270;
				break;
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		return degree;
	}
	/**
	 * 将图片按照某个角度进行旋转
	 * 
	 * @param bm
	 *            需要旋转的图片
	 * @param degree
	 *            旋转角度
	 * @return 旋转后的图片
	 */
	public static Bitmap rotateBitmapByDegree(Bitmap bm, int degree)
	{
		Bitmap returnBm = null;

		// 根据旋转角度，生成旋转矩阵
		Matrix matrix = new Matrix();
		matrix.postRotate(degree);
		try
		{
			// 将原始图片按照旋转矩阵进行旋转，并得到新的图片
			returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(),
					bm.getHeight(), matrix, true);
		} catch (OutOfMemoryError e)
		{
		}
		if (returnBm == null)
		{
			returnBm = bm;
		}
		if (bm != returnBm)
		{
			bm.recycle();
		}
		return returnBm;
	}
	/**
	 * 获取指定图片文件的Bitmap
	 * @param dst
	 * @param width
	 * @param height
	 * @return
	 */
	public  Bitmap getBitmapFromFile(File dst, int width, int height)
	{
		if (null != dst && dst.exists())
		{
			BitmapFactory.Options opts = null;
			if (width > 0 && height > 0)
			{
				opts = new BitmapFactory.Options();
				opts.inJustDecodeBounds = true;
				BitmapFactory.decodeFile(dst.getPath(), opts);
				// 计算图片缩放比例
				final int minSideLength = Math.min(width, height);
				opts.inSampleSize = computeSampleSize(opts, minSideLength,
						width * height);
				opts.inJustDecodeBounds = false;
				opts.inInputShareable = true;
				opts.inPurgeable = true;
			}
			try
			{
				return BitmapFactory.decodeFile(dst.getPath(), opts);
			} catch (OutOfMemoryError e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}
	public static int computeSampleSize(BitmapFactory.Options options,
			int minSideLength, int maxNumOfPixels)
	{
		int initialSize = computeInitialSampleSize(options, minSideLength,
				maxNumOfPixels);

		int roundedSize;
		if (initialSize <= 8)
		{
			roundedSize = 1;
			while (roundedSize < initialSize)
			{
				roundedSize <<= 1;
			}
		} else
		{
			roundedSize = (initialSize + 7) / 8 * 8;
		}
		return roundedSize;
	}
	private static int computeInitialSampleSize(BitmapFactory.Options options,
			int minSideLength, int maxNumOfPixels)
	{
		double w = options.outWidth;
		double h = options.outHeight;

		int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math
				.sqrt(w * h / maxNumOfPixels));
		int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(
				Math.floor(w / minSideLength), Math.floor(h / minSideLength));

		if (upperBound < lowerBound)
		{
			// return the larger one when there is no overlapping zone.
			return lowerBound;
		}

		if ((maxNumOfPixels == -1) && (minSideLength == -1))
		{
			return 1;
		} else if (minSideLength == -1)
		{
			return lowerBound;
		} else
		{
			return upperBound;
		}
	}
	class PublishTask extends AsyncTask<String, String, String>
	{
		@Override
		protected void onPreExecute()
		{
			// TODO Auto-generated method stub
			ProgressDialogUtils.startProgressDialog("请稍候...", customeProgressDialog);
			super.onPreExecute();
		}
		@Override
		protected String doInBackground(String... params)
		{
			// TODO Auto-generated method stub
			HashMap<String, String> map = new HashMap<String, String>();
			//s_shuoshuo,s_time,s_photo,s_good,s_username,s_user_photo
			map.put("s_shuoshuo", publish_content.getText().toString());
			SimpleDateFormat formatter =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			map.put("s_time", formatter.format(new Date()));
			map.put("s_good", "0");
			if(upload_publish_pic_name.equals("")||upload_publish_pic_name == null)
			{
				upload_publish_pic_name="";
			}
			map.put("s_photo", upload_publish_pic_name);
			sharedPreferences = getSharedPreferences("UserInfo", Context.MODE_PRIVATE);
			map.put("s_username", sharedPreferences.getString("username", ""));
			map.put("s_user_photo", sharedPreferences.getString("photo", ""));
			map.put("s_userid", sharedPreferences.getString("userid", ""));
			map.put("s_title", publish_title.getText().toString());
			map.put("s_mark", sharedPreferences.getString("mark", ""));
			String publish_content = HttpUtils.getServletContentByGet(GlobleData.PUBLISH_URL, map, "UTF-8");
			return publish_content;
		}
		@Override
		protected void onPostExecute(String result)
		{
			// TODO Auto-generated method stub
			ProgressDialogUtils.stopProgressDialog(customeProgressDialog);
			String mark = result.substring(0, 1);
			if(mark.equals("1"))
			{
				Toast.makeText(PublishActivity.this, "操作成功", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent();
				intent.setClass(PublishActivity.this, MainActivity.class);
				startActivity(intent);
			}
			else
			{
				Toast.makeText(PublishActivity.this, "操作失败", Toast.LENGTH_SHORT).show();
			}
			super.onPostExecute(result);
		}
	}
}
