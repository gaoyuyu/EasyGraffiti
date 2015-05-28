package android.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AndroidSetPhotoServlet extends HttpServlet
{
	@Override
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
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		Connection con;
		Statement st;
		response.setContentType("text/html;charset=GB2312");
		request.setCharacterEncoding("GB2312");
		response.setCharacterEncoding("GB2312");
		String userid = request.getParameter("userid");
		String photo = request.getParameter("photo");
		System.out.println(userid);
		System.out.println(photo);
		
		try
		{
			PrintWriter out = response.getWriter();
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=tuya", "sa","1qa2ws");
			String update_sql = "update users set photo="+"'"+photo+"'"+" where userid="+userid;
			st = con.createStatement();
			int result = st.executeUpdate(update_sql);
			if(result != 0)
			{
				out.println("1");
			}
			else
			{
				out.println("0");
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
			System.out.println("SQLException£º"+e.toString());
		}
	}
}
