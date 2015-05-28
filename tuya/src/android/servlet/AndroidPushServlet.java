 package android.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mybean.data.AD_comcount;

import GsonTool.GsonTool;

public class AndroidPushServlet extends HttpServlet
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
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		PrintWriter out = response.getWriter();
		AD_comcount adComcount = new AD_comcount();
		String c_comed_userid = request.getParameter("c_comed_userid");
		try
		{
			con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=tuya", "sa", "1qa2ws");
			String query_comnum_sql = "select count(*) from work_comments where c_comed_userid = "+c_comed_userid;
			st = con.createStatement();
			rs = st.executeQuery(query_comnum_sql);
			System.out.println(query_comnum_sql);
			while(rs.next())
			{
				System.out.println("数据条数："+rs.getString(1));
				adComcount.setCount(Integer.valueOf(rs.getString(1)));
				out.println(GsonTool.createJsonString(adComcount));
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
