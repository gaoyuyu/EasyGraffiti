package android.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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

public class AndroidAddGoodServlet extends HttpServlet
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
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		Connection con;
		Statement st;
		String s_good = request.getParameter("s_good");
		String s_worksid = request.getParameter("s_worksid");
		try
		{
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=tuya", "sa", "1qa2ws");
			String update_sql = "update Asend_work set s_good="+"'"+s_good+"'"+" where s_worksid="+s_worksid;
			System.out.println(update_sql);
			st = con.createStatement();
			int result = st.executeUpdate(update_sql);
			if(result != 0)
			{
				out.println("1");
				System.out.println("操作成功");
			}
			else
			{
				out.println("0");
				System.out.println("操作失败");
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("SQLException："+e.toString());
		}
	}
}
