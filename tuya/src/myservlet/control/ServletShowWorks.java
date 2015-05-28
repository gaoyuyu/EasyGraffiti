package myservlet.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import GsonTool.GsonTool;

import mybean.data.Userinfo;
import mybean.data.WorksInfo;

@SuppressWarnings("serial")
public class ServletShowWorks extends HttpServlet
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

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		response.setContentType("text/html;charset=utf-8");
		HttpSession session = request.getSession(true);
		Userinfo userinfo = (Userinfo) session.getAttribute("userinfo");
		boolean ok = true;
		if (userinfo == null)
		{
			ok = false;
			response.sendRedirect("denglu.jsp");
		}
		if (ok == true)
		{
			continueDoPost(request, response);
		}
	}

	private void continueDoPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		
		Connection con;
		Statement sql;
		ResultSet rs,rs1 = null;
		WorksInfo worksInfo=null;
		try
		{
			HttpSession session=request.getSession(true);
		    worksInfo=new WorksInfo();
			Userinfo userInfo=new Userinfo();
			session.setAttribute("worksInfo",worksInfo);
			session.setAttribute("userInfo",userInfo);
			String worksid= request.getParameter("num");
			con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=tuya", "sa", "1qa2ws");
			sql=con.createStatement();
			rs=sql.executeQuery("select * from works where worksid = '"+worksid+"'");
			while(rs.next())
			{
				worksInfo.setTitle(rs.getString(2));
				worksInfo.setIntroduce(rs.getString(3));
				worksInfo.setWorksUploadTime(rs.getString(6));
				String userId=rs.getString(7);
				worksInfo.setUserId(userId);
				worksInfo.setWorksnum(rs.getString(8));
				rs1=sql.executeQuery("select * from users where userId = '"+userId+"'");
				while(rs1.next())
				{
					userInfo.setUsername(rs1.getString(3));
					userInfo.setPhoto(rs1.getString(9));
				}
			}
			
			con.close();
		}
		catch (SQLException exp)
		{
			System.out.println(exp);
		}
//		System.out.println(worksInfo.toString());
//		PrintWriter out = response.getWriter();
//		out.println(GsonTool.createJsonString(worksInfo));
		RequestDispatcher dispatcher=request.getRequestDispatcher("show.jsp");
		dispatcher.forward(request,response);
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doPost(request, response);
	}
}
