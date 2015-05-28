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

public class AndroidAddUserCommentServlet extends HttpServlet
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
		String s_worksid = request.getParameter("s_worksid");
		String work_comment = request.getParameter("work_comment");
		String c_userid = request.getParameter("c_userid");
		String c_comed_userid = request.getParameter("c_comed_userid");
		c_comed_userid = new String(c_comed_userid.getBytes("ISO-8859-1"),"UTF-8");
		c_userid = new String(c_userid.getBytes("ISO-8859-1"),"UTF-8");
		work_comment = new String(work_comment.getBytes("ISO-8859-1"),"UTF-8");
		String c_username = request.getParameter("c_username");
		c_username = new String(c_username.getBytes("ISO-8859-1"),"UTF-8");
		String c_time = request.getParameter("c_time");
		c_time = new String(c_time.getBytes("ISO-8859-1"),"UTF-8");
		try
		{
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=tuya", "sa", "1qa2ws");
			//insert work_comments (s_worksid,work_comment,c_username)values(1,'亨有艺术感','小系列')
			String insert_sql = "insert work_comments (s_worksid,work_comment,c_username,c_time,c_userid,c_comed_userid)values(?,?,?,?,?,?)";
			System.out.println(insert_sql);
			ps = con.prepareStatement(insert_sql);
			ps.setString(1, s_worksid);
			ps.setString(2, work_comment);
			ps.setString(3, c_username);
			ps.setString(4, c_time);
			ps.setString(5, c_userid);
			ps.setString(6, c_comed_userid);
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
