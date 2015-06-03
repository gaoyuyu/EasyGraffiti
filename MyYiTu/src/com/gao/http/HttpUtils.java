package com.gao.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

public class HttpUtils
{

	public HttpUtils()
	{
		// TODO Auto-generated constructor stub
	}
	/**
	 * ��get��ʽ�����˷������󣬲�������˵���Ӧ������ַ�����ʽ���ء�
	 * ���û����Ӧ�����򷵻ؿ��ַ��� 
	 * @param url �����url��ַ
	 * @param params �������
	 * @param charset url���õı���
	 * @return
	 */
	public static String getServletContentByGet(String url,Map<String,String> params,String charset)
	{
		if(url == null)
		{
			return "";
		}
		url = url.trim();
		URL tagetUrl = null;
		try
		{
			if(params == null)
			{
				tagetUrl = new URL(url);
			}
			else
			{
				StringBuilder sb = new StringBuilder(url+"?");
				for(Map.Entry<String, String> me : params.entrySet())
				{
					//�����������к������ĵ�����������
					sb.append(me.getKey()).append("=").append(URLEncoder.encode(me.getValue(),charset)).append("&");
				}
				sb.deleteCharAt(sb.length()-1);
				tagetUrl = new URL(sb.toString());
			}
			System.err.println("get:url--->"+tagetUrl.toString());
			HttpURLConnection conn = (HttpURLConnection)tagetUrl.openConnection();
			conn.setConnectTimeout(3000);
			conn.setRequestMethod("GET");
			conn.setDoInput(true);
			
			int reponseCode = conn.getResponseCode();
			System.err.println("reponseCode--->"+reponseCode);
			if(reponseCode == HttpURLConnection.HTTP_OK)
			{
				return stream2String(conn.getInputStream(), charset);
			}
		} catch (Exception e)
		{
			// TODO: handle exception
//			System.out.println(e.getMessage());
		}
		return "";
	}
	/**
	 * ��post��ʽ�����˷������󣬲�������˵���Ӧ������ַ�����ʽ���ء�
	 * ���û����Ӧ�����򷵻ؿ��ַ��� 
	 * @param url �����url��ַ
	 * @param params �������
	 * @param charset url���õı���
	 * @return
	 */
	public static String getServletContentByPost(String url,Map<String,String> params,String charset)
	{
		if(url == null)
		{
			return "";
		}
		url = url.trim();
		URL tagetUrl = null;
		OutputStream out = null;
		try
		{
			tagetUrl = new URL(url);
			HttpURLConnection conn = (HttpURLConnection)tagetUrl.openConnection();
//			conn.setConnectTimeout(3000);
			conn.setRequestMethod("POST");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			
			StringBuilder sb = new StringBuilder();
			if(params != null && !params.isEmpty())
			{
				for(Map.Entry<String, String> me : params.entrySet())
				{
					//�����������е����Ľ��б���
					sb.append(me.getKey()).append("=").append(URLEncoder.encode(me.getValue(),charset)).append("&");
				}
				sb.deleteCharAt(sb.length()-1);
			}
			byte[] data = sb.toString().getBytes();
			conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");  
			conn.setRequestProperty("Content-Length", String.valueOf(data.length));
			out = conn.getOutputStream();
			out.write(data);
			System.out.println("post:url---->"+tagetUrl.toString());
			
			int responseCode = conn.getResponseCode();
			if(responseCode == HttpURLConnection.HTTP_OK)
			{
				return stream2String(conn.getInputStream(),charset);
			}
		} catch (Exception e)
		{
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return "";
	}
	
	
	
	/**
	 * �������ϻ�ȡͼƬ�����ֽ��������ʽ����
	 * 
	 * @return
	 */
	public static byte[] getImageViewArray(String URL_PATH)
	{
		byte[] data = null;
		InputStream inputStream = null;
		// ����Ҫ�رյ��������ֱ��д�뵽�ڴ�����
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try
		{
			URL url = new URL(URL_PATH);
			if (url != null)
			{
				HttpURLConnection httpURLConnection = (HttpURLConnection) url
						.openConnection();
				httpURLConnection.setConnectTimeout(3000);// �������ӳ�ʱ
				httpURLConnection.setRequestMethod("GET");
				httpURLConnection.setDoInput(true);
				int response_code = httpURLConnection.getResponseCode();
				int len=0;
				byte[] b_data = new byte[1024];
				if (response_code == 200)
				{
					inputStream = httpURLConnection.getInputStream();
					while ((len = inputStream.read(b_data))!= -1)
					{
						byteArrayOutputStream.write(b_data, 0, len);
					}
					data = byteArrayOutputStream.toByteArray();
				}
			}
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			if(inputStream!=null)
			{
				try
				{
					inputStream.close();
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

		return data;

	}
	

	public static String stream2String(InputStream inputStream, String charset) throws IOException
	{
		// TODO Auto-generated method stub
		if(inputStream == null)
		{
			return "";
		}
		byte[] buffer  = new byte[1024];
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		int len = 0;
		while((len = inputStream.read(buffer)) != -1)
		{
			bout.write(buffer, 0, len);
		}
		String result = new String(bout.toByteArray(),charset);
		inputStream.close();
		return result;
	}

	private static String changeInputStream(InputStream inputStream)
	{
		// TODO Auto-generated method stub
		String ServletContent="";
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		int len = 0;
		byte[] data = new byte[1024];
		try
		{
			while((len = inputStream.read(data)) != -1)
			{
				outputStream.write(data, 0, len);
			}
			ServletContent = new String(outputStream.toByteArray());
			System.out.println("HttpUtils--ServletContent-->"+ServletContent);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ServletContent;
	}
	
//	public static String getDataByPost(String url,Map<String,String>)
	
}
