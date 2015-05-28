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

public class AndroidSetSexServlet extends HttpServlet
{
	
	@Override
	public void init(ServletConfig config) throws ServletException
	{
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		}
		catch (ClassNotFoundException e)
		{
			System.out.println(e);
		}
		super.init(config);
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
		String sex = request.getParameter("sex");
		sex = new String(sex.getBytes("ISO-8859-1"),"UTF-8");
		System.out.println("userid--->"+userid);
		System.out.println("sex--->"+sex);
		try
		{
			PrintWriter out = response.getWriter();
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=tuya", "sa","1qa2ws");
			//update users set sex='Ů' where userid=13 
			String update_sql = "update users set sex="+"'"+sex+"'"+" where userid="+userid;
			System.out.println(update_sql);
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
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("SQLException��"+e.toString());
		}
	}
}