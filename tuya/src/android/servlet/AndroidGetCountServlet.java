package android.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AndroidGetCountServlet extends HttpServlet
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
		Connection con;
		PreparedStatement ps;
		ResultSet rs;
		int rowCount =0;
		int pageSize=5;
		int pageCount;
		try
		{
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=tuya", "sa", "1qa2ws");
			String count_sql = "select count(*) from Asend_work";
			ps = con.prepareStatement(count_sql);
			rs = ps.executeQuery();
			if(rs.next())
			{
				rowCount = rs.getInt(1);//得到有多少列，即多少条数据
				System.out.println("rowCount-->"+rowCount);
			}
			if(rowCount%pageSize == 0)
			{
				pageCount = rowCount/pageSize;//整除的话就，得到页数
				System.out.println("pageCount-整除的话就，得到页数-->"+pageCount);
			}
			else
			{
				pageCount = rowCount/pageSize + 1;//不整除 的话，+1
				System.out.println("pageCount--不整除 的话，+1>"+pageCount);
			}
			PrintWriter out = response.getWriter();
			out.println(pageCount);
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
