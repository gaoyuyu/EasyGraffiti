package myservlet.control;

import java.io.IOException;
import java.io.PrintWriter;
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

import GsonTool.GsonTool;

import mybean.data.Message;
import mybean.data.Userinfo;

public class CommentServlet extends HttpServlet
{

	/**
	 * -------里面有json字符串
	 */
	private static final long serialVersionUID = 1L;

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
			if (mark.equals("addComment"))
				addComment(request, response, userinfo);
			else if (mark.equals("showComment"))
				showComment(request, response, userinfo);
		}
	}
	
	public void showComment(HttpServletRequest request,
			HttpServletResponse response, Userinfo userinfo)
			throws ServletException, IOException
	{
		response.setContentType("text/html;charset=utf-8");
		String userId2 = request.getParameter("userId2");
		System.out.println("02"+userId2);
		PrintWriter out = response.getWriter();
		Connection con = null;
		PreparedStatement sql = null;
		ResultSet rs = null;
		List<Message> messages=null;
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
			}
			request.setAttribute("message", messages);

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

//	request.getRequestDispatcher("show.jsp").forward(request, response);
	out.print("messages->"+GsonTool.createJsonString(messages));

		
	}

	public void addComment(HttpServletRequest request,
			HttpServletResponse response, Userinfo userinfo)
			throws ServletException, IOException
	{
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		String userId = userinfo.getUserid();// 用户的id
		String userId2 = request.getParameter("userId2");// 被评论者的id
		System.out.println("01"+userId2);
		
		String mesContent = request.getParameter("mesContent");
		String mesTime = new SimpleDateFormat("yyyy-MM-dd HH:mm")
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
		System.out.println("URL:"+request.getRequestURI());
		request.getRequestDispatcher(
				"showComment?mark=showComment&userId2=" + userId2).forward(
				request, response);
		return;
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doPost(request, response);
	}
}
