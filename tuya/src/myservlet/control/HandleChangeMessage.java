package myservlet.control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mybean.data.Userinfo;

public class HandleChangeMessage extends HttpServlet
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
		String username = request.getParameter("username").trim(), sex = request
				.getParameter("sex").trim(), place = request.getParameter(
				"place").trim(), information = request.getParameter(
				"information").trim(), mark = request.getParameter("mark")
				.trim(), introduction = request.getParameter("introduction")
				.trim();
		System.out.println(handleString(sex));
		try
		{
			HttpSession session = request.getSession(true);
			Userinfo userinfo=(Userinfo)session.getAttribute("userinfo");
			String id=userinfo.getUserid();
			
			Connection con;
			Statement sql;
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=tuya", "sa", "1qa2ws");
			sql = con.createStatement();
			ResultSet rs;
			String insertCondition = "update users set username='"+handleString(username)+"', sex='" + handleString(sex) + "',place='" + handleString(place) +"',information='" + handleString(information) + "',mark='" +handleString(mark) + "',introduction='"+handleString(introduction)+"' where userid=" +id ;
			int result = sql.executeUpdate(insertCondition);
			if(0 != result)
				System.out.println("信息修改成功！");
			else{
				System.out.println("该用户的信息不存在，或者信息修改不成功！");
				return;
			}
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
		
		RequestDispatcher dispatcher = request
		.getRequestDispatcher("gerenziliao.jsp");
dispatcher.forward(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doPost(request, response);
	}
}
