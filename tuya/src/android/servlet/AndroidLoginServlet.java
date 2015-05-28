package android.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.ToUnicode;

import GsonTool.GsonTool;

import mybean.data.AD_UserInfo;
import mybean.data.Userinfo;

public class AndroidLoginServlet extends HttpServlet
{
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e)
		{
			System.out.println("ClassNotFoundException£º" + e);
		}
	}

	public String handleString(String s)
	{
		try
		{
			byte b[] = s.getBytes("ISO-8859-1");
			s = new String(b);
		} catch (Exception ee)
		{
			ee.printStackTrace();
		}
		return s;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		response.setContentType("text/html;charset=GB2312");
		request.setCharacterEncoding("GB2312");
		response.setCharacterEncoding("GB2312");
		System.out.println("Method-->" + request.getMethod());
		PrintWriter out = response.getWriter();
		String userid = "";
		Connection con;
		Statement st;
		AD_UserInfo userinfo = new AD_UserInfo();
		String email = request.getParameter("email").trim();
		String password = request.getParameter("password").trim();
		email = handleString(email);
		password = handleString(password);
		boolean boo = (email.length() > 0) && (password.length() > 0);
		try
		{
			con = DriverManager.getConnection(
					"jdbc:sqlserver://localhost:1433;database=tuya", "sa",
					"1qa2ws");
			String condition = "select * from users where email='" + email
					+ "' AND " + " password='" + password + "' ";
			st = con.createStatement();
			if (boo)
			{
				ResultSet rs = st.executeQuery(condition);
				boolean m = rs.next();
				if (m == true)
				{
					userinfo.setEmail(rs.getString(1));
					userinfo.setPassword(rs.getString(2));
					String str = rs.getString(3);
					System.out.println("username-->"+ToUnicode.toUnicodeString(str));
					userinfo.setUsername(rs.getString(3));
					userinfo.setSex(rs.getString(4));
					userinfo.setMark(rs.getString(5));
					userinfo.setPhoto(rs.getString(6));
					userinfo.setUserid(rs.getString(7));
					
				} 
			} else
			{
				System.out.println("boo£º" + boo);
			}
			con.close();
		} catch (SQLException e)
		{

			System.out.println("SQLException£º" + e);
		}
		System.out.println("request.getRequestURI()£º" + request.getRequestURI());
		System.out.println("request.getQueryString()£º"+ request.getQueryString());
		String json = GsonTool.createJsonString(userinfo);
		json = ToUnicode.toUnicodeString(json);
		out.println(json);
	}
}
