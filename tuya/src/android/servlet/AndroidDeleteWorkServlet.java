package android.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.OutputKeys;

public class AndroidDeleteWorkServlet extends HttpServlet
{
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		try
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e)
		{
			System.out.println(e);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		Connection con;
		PreparedStatement ps;
		String s_worksid = request.getParameter("s_worksid");
		try
		{
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=tuya", "sa", "1qa2ws");
			//É¾³ýAsend_work±íµÄ¼ÇÂ¼
			String del_sql_1 = "delete from Asend_work where s_worksid = "+s_worksid;
			//É¾³ýuser_isgood µãÔÞ¼ÇÂ¼
			String del_sql_2 = "delete from user_isgood where s_worksid = "+s_worksid;
			//É¾³ýwork_comments ÆÀÂÛ¼ÇÂ¼
			String del_sql_3 = "delete from work_comments where s_worksid = "+s_worksid;
			ps = con.prepareStatement(del_sql_1);
			int del_mark_1 = ps.executeUpdate();
			
			ps = con.prepareStatement(del_sql_2);
			int del_mark_2 = ps.executeUpdate();
			
			ps = con.prepareStatement(del_sql_3);
			int del_mark_3 = ps.executeUpdate();
			System.out.println("É¾³ýAsend_work±íµÄ¼ÇÂ¼--->"+del_mark_1);
			System.out.println("É¾³ýuser_isgood µãÔÞ¼ÇÂ¼--->"+del_mark_2);
			System.out.println("É¾³ýwork_comments ÆÀÂÛ¼ÇÂ¼--->"+del_mark_3);
			if(((1 ==del_mark_1)||(1 == del_mark_2))||(1==del_mark_3))
			{
				out.print("1");
			}
			else
			{
				out.print("0");
			}
			
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.toString());
		}
	}
}
