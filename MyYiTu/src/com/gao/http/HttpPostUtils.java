package com.gao.http;

import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class HttpPostUtils
{
	private static final String TAG="HttpPostUtils";
	public HttpPostUtils()
	{
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param path
	 * @param fileBody
	 * @param fileName
	 * @return
	 */
	public static void sendFormByPost(final String path, final byte[] fileBody,
			final String fileName)
	{
		String msg = null;
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				// TODO Auto-generated method stub
				try
				{
					HttpClient httpClient = new DefaultHttpClient();
					HttpPost httpPost = new HttpPost(path);
					MultipartEntity entity = new MultipartEntity();
					entity.addPart("myfile", new ByteArrayBody(fileBody,
							fileName));
					httpPost.setEntity(entity);
					HttpResponse response= httpClient.execute(httpPost);
					InputStream in= response.getEntity().getContent();
					String msg = HttpUtils.stream2String(in,"UTF-8");
					Log.i(TAG, "msg------->"+msg+"---leng---"+msg.length());
				} catch (Exception e)
				{
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}).start();
	}
}
