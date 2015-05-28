package myservlet.control;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mybean.data.Userinfo;

public class ServletUpLoad extends HttpServlet
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
			e.printStackTrace();
		}
	}
	
	public String handleString(String s)
	{
		try
		{
			byte b[] = s.getBytes("ISO-8859-1");
			s = new String(b);
		}
		catch (Exception ee)
		{
			ee.printStackTrace();
		}
		return s;
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException
	{
		Userinfo userinfo = (Userinfo) request.getSession(true).getAttribute(
		"userinfo");
		String title=request.getParameter("title").trim();
		String introduce=request.getParameter("introduce").trim();
		String sort=request.getParameter("sort").trim();
		String label=request.getParameter("label").trim();
		String worksUploadTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());
		String userId = userinfo.getUserid();// ÓÃ»§µÄid
		
		System.out.println(userId+" "+worksUploadTime+" "+handleString(title)+" "+handleString(introduce)+" "+handleString(sort)+" "+handleString(label));
		
		
		
		
		
		
		
		
		
		
	}
}
