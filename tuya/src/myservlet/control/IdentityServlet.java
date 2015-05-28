package myservlet.control;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageDecoder;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class IdentityServlet extends HttpServlet
{
	//不包括0,o,1,I难辨认的字符
	public static final char[] CHARS={'2','3','4','5','6','7','8','9','A','B','C','D','E','F','G','H','J','K','L','M','N','P','Q','R','S','T','U','V','W','X','Y','Z'};
	//随机数
	public static Random random=new Random();
	//获取六位随机数
	public static String getRandomString()
	{
		StringBuffer buffer=new StringBuffer();
		for(int i=0;i<6;i++)
		{
			//每次去一个随机字符
			buffer.append(CHARS[random.nextInt(CHARS.length)]);
		}
		return buffer.toString();
	}
	//获取随机颜色
	public static Color getRandomColor()
	{
		return new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
	}
	//返回某颜色的反应
	public static Color getReverseColor(Color c)
	{
		return new Color(255-c.getRed(),255-c.getGreen(),255-c.getBlue());
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		response.setContentType("image/jpeg");//设置输出类型
		String randomString=getRandomString();
		request.getSession(true).setAttribute("randomString",randomString);
		
		int width=100;
		int height=30;
		
		Color color=getRandomColor();//随机颜色，用于背景颜色
		Color reverse=getReverseColor(color);//反色，用于前景色
		
		BufferedImage bi=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);//创建一个彩色图片
		
		Graphics2D g=bi.createGraphics();//获取绘画对象
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,16));//设置字体
		g.setColor(color);//设置颜色
		g.fillRect(0,0,width,height);//设置背景
		g.setColor(reverse);//设置颜色
		g.drawString(randomString,18,20);//绘制随机字符
		for(int i=0,n=random.nextInt(100);i<n;i++)
		{
			g.drawRect(random.nextInt(width),random.nextInt(height),1,1);//画100个随机噪音点
		}
		ServletOutputStream out=response.getOutputStream();
		JPEGImageEncoder encoder=JPEGCodec.createJPEGEncoder(out);
		
		encoder.encode(bi);
		out.flush();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
