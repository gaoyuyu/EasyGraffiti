/**
 * ��������֤��
 */
package myservlet.control;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class RandomStringImage extends HttpServlet{
	//����ַ��ֵ�
	private static final char[] CHARS = { '2', '3', '4', '5', '6', '7', '8',
		'9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M',
		'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	
	private static Random random = new Random();
	
	//��������ַ�
	public String getRandomString(){
		StringBuilder builder = new StringBuilder();
		for(int i = 0;i < 4;i++)
		{
			builder.append(CHARS[random.nextInt(CHARS.length)]);
			builder.append(" ");
		}
		return builder.toString();
	}
	
	//�������ɫ
	public Color getRandomColor(){
		return new Color(random.nextInt(255),random.nextInt(255),random.nextInt(255));
	}
	
	//��ȡ�����ɫ�ķ�ɫ
	public Color getReverseColor(Color color){
		return new Color(255 - color.getRed(),255-color.getGreen(),255-color.getBlue());
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("image/jpeg");
		
		HttpSession session = request.getSession(true);
		String randomString = getRandomString();
		session.setAttribute("randomString",randomString);
		
		Color color = getRandomColor();				//��ȡ�����ɵ���ɫ
		Color reverse = getReverseColor(color);		//��ȡ��������ɫ��
		
		int width = 100;
		int height = 30;
		
		BufferedImage bi = new BufferedImage(width, height,BufferedImage.TYPE_INT_RGB);
		Graphics2D g = bi.createGraphics();
		
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,18));
		g.setColor(color);
		
		g.fillRect(0,0,width,height);
		g.setColor(reverse);
		
		g.drawString(randomString,17,21);
		
		for(int i = 0,n = random.nextInt(50);i < n;i++){
			g.drawRect(random.nextInt(width),random.nextInt(height),1,1);
		}
		
		OutputStream out = response.getOutputStream();
		//ת��
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(bi);
		
		out.flush();
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}
	
}
