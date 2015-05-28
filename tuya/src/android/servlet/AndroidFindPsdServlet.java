package android.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AndroidFindPsdServlet extends HttpServlet
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
		Statement ps;
		ResultSet rs;
		String email = request.getParameter("email");
		try
		{
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=tuya", "sa","1qa2ws");
			String query_psd = "select password from users where email = '"+email+"'";
			System.out.println("query_psd-->"+query_psd);
			ps = con.createStatement();
			rs = ps.executeQuery(query_psd);
			if(rs.next())
			{
				System.out.println(rs.getString(1));
				out.println(rs.getString(1));
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
