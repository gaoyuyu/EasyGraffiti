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

public class AndroidAddUserGoodServlet extends HttpServlet
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
		PreparedStatement ps;
		String is_good = request.getParameter("is_good");
		String userid = request.getParameter("userid");
		System.out.println(is_good);
		String s_worksid = request.getParameter("s_worksid");
		try
		{
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=tuya", "sa", "1qa2ws");
			String insert_sql = "insert user_isgood (is_good,s_worksid,userid) values(?,?,?)";
			System.out.println(insert_sql);
			
			ps = con.prepareStatement(insert_sql);
			ps.setString(1, is_good);
			ps.setString(2, s_worksid);
			ps.setString(3, userid);
			int result = ps.executeUpdate();
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
