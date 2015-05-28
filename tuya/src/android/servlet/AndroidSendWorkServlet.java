package android.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AndroidSendWorkServlet extends HttpServlet
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
			s = new String(s.getBytes("ISO-8859-1"),"UTF-8");
		} catch (UnsupportedEncodingException e)
		{
			e.printStackTrace();
		}
		return s;
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		Connection con;
		Statement st;
		PreparedStatement preparedStatement;
		response.setContentType("text/html;charset=GB2312");
		request.setCharacterEncoding("GB2312");
		response.setCharacterEncoding("GB2312");
		String s_shuoshuo = handleString(request.getParameter("s_shuoshuo"));
		String s_time = handleString(request.getParameter("s_time"));
		String s_photo = handleString(request.getParameter("s_photo"));
		String s_good= handleString(request.getParameter("s_good"));
		String s_username = handleString(request.getParameter("s_username"));
		String s_user_photo = handleString(request.getParameter("s_user_photo"));
		String s_userid = handleString(request.getParameter("s_userid"));
		String s_title = handleString(request.getParameter("s_title"));
		String s_mark = handleString(request.getParameter("s_mark"));
		System.out.println(s_shuoshuo);
		System.out.println(s_time);
		System.out.println(s_photo);
		System.out.println(s_good);
		System.out.println(s_username);
		System.out.println(s_user_photo);
		System.out.println(s_mark);
		
		
		try
		{
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=tuya", "sa", "1qa2ws");
			String insert_sql = "insert into Asend_work(s_shuoshuo,s_time,s_photo,s_good,s_username,s_user_photo,s_userid,s_title,s_mark)values(?,?,?,?,?,?,?,?,?)";
			preparedStatement = con.prepareStatement(insert_sql);
			preparedStatement.setString(1, s_shuoshuo);
			preparedStatement.setString(2, s_time);
			preparedStatement.setString(3, s_photo);
			preparedStatement.setString(4, s_good);
			preparedStatement.setString(5, s_username);
			preparedStatement.setString(6, s_user_photo);
			preparedStatement.setString(7, s_userid);
			preparedStatement.setString(8, s_title);
			preparedStatement.setString(9, s_mark);
			int mark = preparedStatement.executeUpdate();
			if(mark != 0 )
			{
				System.out.println("send≥…π¶");
				out.print("1");
			}
			else
			{
				System.out.println("send ß∞‹");
				out.print("0");
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("SQLException£∫"+e.toString());
		}
		
		
		
		
	}
}
