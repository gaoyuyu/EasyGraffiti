package myservlet.control;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mybean.data.Userinfo;


public class ServletExit extends HttpServlet
{
	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
	}
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	throws ServletException, IOException
	{
		HttpSession session=request.getSession(true);
		Userinfo userinfo=(Userinfo)session.getAttribute("login");
		boolean ok=true;
		if(userinfo==null)
		{
			ok=false;
			response.sendRedirect("denglu.jsp");
		}
		if(ok==true)
		{
			session.invalidate();//销毁用户的session对象
			response.sendRedirect("index.jsp");
		}
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doPost(request, response);
	}
}
