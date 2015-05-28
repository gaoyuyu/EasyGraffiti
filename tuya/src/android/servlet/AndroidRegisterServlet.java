package android.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mybean.data.Userinfo;

public class AndroidRegisterServlet extends HttpServlet
{
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}

	public String handleString(String s)
	{
		try
		{
			byte bb[] = s.getBytes("ISO-8859-1");
			s = new String(bb);
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
		PrintWriter out = response.getWriter();
		String email = request.getParameter("email").trim();
		String username = request.getParameter("username").trim();
		String password = request.getParameter("password").trim();
		String password2 = request.getParameter("password2").trim();
		String sex = request.getParameter("sex").trim();
		username = new String(username.getBytes("ISO-8859-1"),"UTF-8");
		sex = new String(sex.getBytes("ISO-8859-1"),"UTF-8");
		if (username == null)
			username = "";
		int space = username.indexOf(" ");
		if (space != -1)
		{
			response.sendRedirect("zhuce.jsp");
		}

		if (password == null)
			password = "";
		if (password2 == null)
			password2 = "";
		boolean isLD = true;
		if (password.length() > 0 && password2.length() > 0)
			if (password.equals(password2))
				isLD = true;
			else
				isLD = false;
		else
			isLD = false;

		boolean boo = username.length() > 0 && isLD;
		try
		{
			Userinfo userinfo=new Userinfo();
			request.setAttribute("userinfo",userinfo);
			
			Connection con;
			PreparedStatement sql;
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=tuya", "sa", "1qa2ws");
			String insertCondition = "INSERT INTO users(email,password,username,sex,mark,photo) VALUES(?,?,?,?,?,?)";
			sql = con.prepareStatement(insertCondition);
			if (boo)
			{
				sql.setString(1, handleString(email));
				sql.setString(2, password);
				sql.setString(3, username);
				sql.setString(4, sex);
				sql.setString(5,null);
				sql.setString(6, null);
				
				int m = sql.executeUpdate();
				if (m != 0)
				{
					userinfo.setUsername(username);
					
					System.out.println(username+" 注册成功！");
					out.print("1");//1-----表示注册成功
				}
				else
				{
					out.print("0");//0-----表示注册失败
				}
			}
			con.close();
		}
		catch (SQLException exp)
		{
			System.out.println(exp);
		}
		
//		RequestDispatcher dispatcher = request
//				.getRequestDispatcher("index.jsp");
//		dispatcher.forward(request, response);
		
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doPost(request, response);
	}
}
