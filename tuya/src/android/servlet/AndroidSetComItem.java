package android.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.faces.config.ConfigureListener.ServletContextAdapter;

import GsonTool.GsonTool;

import mybean.data.AD_ComItem;
import mybean.data.AD_static;

public class AndroidSetComItem extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		/**
		 * 				comm_eEditor.putString("s_user_photo", s_user_photo);
				comm_eEditor.putString("s_worksid", s_worksid);
				comm_eEditor.putString("s_good", s_good);
				comm_eEditor.putString("s_time", s_time);
				comm_eEditor.putString("s_photo", s_photo);
				comm_eEditor.putString("s_shuoshuo", s_shuoshuo);
				comm_eEditor.putString("is_good", is_good);
				comm_eEditor.putString("s_username", s_username);
				comm_eEditor.putString("s_title", s_title);
				comm_eEditor.putString("s_userid", s_userid);
		 */
		String s_user_photo = request.getParameter("s_user_photo");
		String s_worksid = request.getParameter("s_worksid");
		String s_good = request.getParameter("s_good");
		String s_time = request.getParameter("s_time");
		String s_photo = request.getParameter("s_photo");
		String s_shuoshuo = request.getParameter("s_shuoshuo");
		String is_good = request.getParameter("is_good");
		String s_username = request.getParameter("s_username");
		String s_title = request.getParameter("s_title");
		String s_userid = request.getParameter("s_userid");
		
		s_user_photo = new String(s_user_photo.getBytes("ISO-8859-1"),"UTF-8");
		s_worksid = new String(s_worksid.getBytes("ISO-8859-1"),"UTF-8");
		s_good = new String(s_good.getBytes("ISO-8859-1"),"UTF-8");
		s_time = new String(s_time.getBytes("ISO-8859-1"),"UTF-8");
		s_shuoshuo = new String(s_shuoshuo.getBytes("ISO-8859-1"),"UTF-8");
		s_photo = new String(s_photo.getBytes("ISO-8859-1"),"UTF-8");
		is_good = new String(is_good.getBytes("ISO-8859-1"),"UTF-8");
		s_username = new String(s_username.getBytes("ISO-8859-1"),"UTF-8");
		s_title = new String(s_title.getBytes("ISO-8859-1"),"UTF-8");
		s_userid = new String(s_userid.getBytes("ISO-8859-1"),"UTF-8");
		System.out.println("s_shuoshuo"+s_shuoshuo);
		AD_static.adComItem = new AD_ComItem();
		AD_static.adComItem.setIs_good(is_good);
		AD_static.adComItem.setS_good(s_good);
		AD_static.adComItem.setS_photo(s_photo);
		AD_static.adComItem.setS_shuoshuo(s_shuoshuo);
		AD_static.adComItem.setS_time(s_time);
		AD_static.adComItem.setS_title(s_title);
		AD_static.adComItem.setS_user_photo(s_user_photo);
		AD_static.adComItem.setS_userid(s_userid);
		AD_static.adComItem.setS_username(s_username);
		AD_static.adComItem.setS_worksid(s_worksid);
		System.out.println("Set----------->"+AD_static.adComItem.toString());
		PrintWriter out = response.getWriter();
		
		System.out.println("static object  seted already.");
	}
}
