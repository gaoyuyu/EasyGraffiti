package myservlet.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mybean.data.Message;
import mybean.data.Userinfo;

public class HandleUserinfo extends HttpServlet
{
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		}
		catch (ClassNotFoundException e)
		{
			System.out.println(e);
		}
	}

	// 处理字符串的方法
	public String handleString(String s)
	{
		try
		{
			byte b[] = s.getBytes("ISO-8859-1");
			s = new String(b);
		}
		catch (Exception ee)
		{
			ee.printStackTrace();
		}
		return s;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		Connection con;
		PreparedStatement sql;
		ResultSet rs;
		try
		{
			Userinfo userinfo=null;
			HttpSession session = request.getSession(true);
			try
			{
				userinfo = (Userinfo)session.getAttribute("userinfo");
				if (userinfo == null)
				{
					userinfo = new Userinfo();
					session.setAttribute("userinfo", userinfo);
				}
			}
			catch (Exception ee)
			{
				System.out.println(ee);
			}
			String email=userinfo.getEmail();
			String userid1 = request.getParameter("num");
			 
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=tuya", "sa", "1qa2ws");
			sql = con.prepareStatement("select * from users where userid = '"
					+ userid1 + "' ");
			rs = sql.executeQuery();
			while (rs.next())
			{
				userinfo.setUsername(rs.getString(3));
				userinfo.setSex(rs.getString(4));
				userinfo.setPlace(rs.getString(5));
				userinfo.setInformation(rs.getString(6));
				userinfo.setMark(rs.getString(7));
				userinfo.setIntroduction(rs.getString(8));
				userinfo.setPhoto(rs.getString(9));
				userinfo.setUserid2(rs.getString(10));
			}
			try
			{
				String userId2= userid1;
				
				con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=tuya", "sa", "1qa2ws");
				String condition = "select m.mesContent,m.mesTime,u.username,u.photo from message m,users u where m.userId = u.userid and userId2=?";
				sql = con.prepareStatement(condition);
				sql.setString(1, userId2);
				rs = sql.executeQuery();
				List<Message> messages = new ArrayList<Message>();
				while (rs.next())
				{
					Message message = new Message();

					String mesContent = rs.getString("mesContent");
					String mesTime = rs.getString("mesTime");
					String username = rs.getString("username");
					String photo = rs.getString("photo");
					System.out.println(photo);

					message.setMesContent(mesContent);
					message.setMesTime(mesTime);
					message.setUsername(username);
					message.setPhoto(photo);

					messages.add(message);
				}
				for (Message message : messages)
				{
					System.out.println(message.getMesContent());
				}
				request.setAttribute("message", messages);

			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			con.close();
		}
		catch (SQLException exp)
		{
			System.out.println(exp);
		}
		
			RequestDispatcher dispatcher = request
				.getRequestDispatcher("person.jsp");
		dispatcher.forward(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doPost(request, response);
	}
}
