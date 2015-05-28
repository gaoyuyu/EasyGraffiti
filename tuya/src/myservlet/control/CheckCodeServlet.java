package myservlet.control;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckCodeServlet extends HttpServlet
{
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		// 画画
		BufferedImage image = new BufferedImage(70, 35,
				BufferedImage.TYPE_INT_BGR);
		Graphics g = image.getGraphics();
		Random r = new Random();
		g.setColor(new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255)));
		g.fillRect(0, 0,80, 40);
		// 生成一个随机数，并且画到内存映射对象上
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZ01234567890";
		String number = "";
		for (int i = 0; i < 5; i++)
		{
			number += str.charAt(r.nextInt(str.length()));
		}
		HttpSession session = request.getSession();
		session.setAttribute("number", number);
		g.setColor(new Color(10, 20, 30));
		g.drawString(number, 15, 20);
		g.setFont(new Font(Font.SANS_SERIF,Font.BOLD,72));
		// 加一些干扰线
		for (int i = 0; i < 5; i++)
		{
			g
					.setColor(new Color(r.nextInt(255), r.nextInt(255), r
							.nextInt(255)));
			g.drawLine(r.nextInt(80), r.nextInt(40), r.nextInt(80), r
					.nextInt(40));
		}
		// 2 将图片压缩并输出到客户端
		response.setContentType("image/jpeg");
		OutputStream ops = response.getOutputStream();
		javax.imageio.ImageIO.write(image, "jpeg", ops);
	}
}