package myservlet.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mybean.data.Message;
import mybean.data.Userinfo;

public class MessageServlet extends HttpServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7205424123783155986L;

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
		Userinfo userinfo = (Userinfo) request.getSession(true).getAttribute(
				"userinfo");
		String mark = request.getParameter("mark");
		System.out.println(mark);
		if (null != userinfo)
		{
			if (mark.equals("addMessage"))
				addMessage(request, response, userinfo);
			else if (mark.equals("showMessage"))
				showMessage(request, response, userinfo);
		}
	}

	public void showMessage(HttpServletRequest request,
			HttpServletResponse response, Userinfo userinfo)
			throws ServletException, IOException
	{
		String userId2 = request.getParameter("userId2");
		List<Message> messages = null;
		Connection con = null;
		PreparedStatement sql = null;
		ResultSet rs = null;

		try
		{
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=tuya", "sa", "1qa2ws");
			String condition = "select m.mesContent,m.mesTime,u.username,u.photo from message m,users u where m.userId = u.userid and userId2=?";
			sql = con.prepareStatement(condition);
			sql.setString(1, userId2);
			rs = sql.executeQuery();
			 messages = new ArrayList<Message>();
			while (rs.next())
			{
				Message message = new Message();
				String mesContent = rs.getString("mesContent");
				String mesTime = rs.getString("mesTime");
				String username = rs.getString("username");
				String photo = rs.getString("photo");
				message.setMesContent(mesContent);
				message.setMesTime(mesTime);
				message.setUsername(username);
				message.setPhoto(photo);

				messages.add(message);
			}
			for (Message message : messages)
			{
				System.out.println(message.getMesContent());
				System.out.println(message.toString());
			}
			request.setAttribute("message", messages);

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
        
//		request.getRequestDispatcher("message.jsp").forward(request, response);

	}

	public void addMessage(HttpServletRequest request,
			HttpServletResponse response, Userinfo userinfo)
			throws ServletException, IOException
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String userId = userinfo.getUserid();// 用户的id
		String userId2 = userinfo.getUserid2();// 被评论者的id
		String mesContent = request.getParameter("mesContent");
		String mesTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(new java.util.Date());
		System.out.println(mesTime);
		String sql = "insert into message(mesContent,mesTime,userId,userId2) values(?,?,?,?)";

		if (userId == null)
			userId = "1";
		try
		{
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=tuya", "sa", "1qa2ws");
			ps = con.prepareStatement(sql);
			ps.setString(1, handleString(mesContent));
			ps.setString(2, mesTime);
			ps.setString(3, userId);
			ps.setString(4, userId2);
			int result = ps.executeUpdate();
			if (0 != result)
				System.out.println("评论成功！");
		}
		catch (SQLException e)
		{
			System.out.println(e);
			e.printStackTrace();
			return;
		}
		request.getRequestDispatcher(
				"showMessage?mark=showMessage&userId2=" + userId2).forward(
				request, response);
		return;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doPost(request, response);
	}
}
