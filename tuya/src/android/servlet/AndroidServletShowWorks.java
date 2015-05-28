package android.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import util.ToUnicode;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import mybean.data.Userinfo;
import mybean.data.WorksInfo;
import mybean.data.WorksInfoList;
import GsonTool.GsonTool;

/**
 * 获取作品列表
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class AndroidServletShowWorks extends HttpServlet
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
		if (ok == true)
		{
			continueDoPost(request, response);
		}
	}

	/**
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void continueDoPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		ArrayList<Map<String,Object>> list = null;
		Connection con;
		Statement sql;
		ResultSet rs,rs1 = null;
		WorksInfo worksInfo=null;
		int pageSize = 10;
		String pageNow =request.getParameter("pageNow");
		try
		{
			list = new ArrayList<Map<String,Object>>();
			HttpSession session=request.getSession(true);
		    worksInfo=new WorksInfo();
			Userinfo userInfo=new Userinfo();
			session.setAttribute("worksInfo",worksInfo);
			session.setAttribute("userInfo",userInfo);
			con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=tuya", "sa", "1qa2ws");
			sql=con.createStatement();
			//select top 5 * from Aworks where worksid not in (select top 0 worksid from Aworks)
			//"+(Integer.valueOf(pageNow)-1)*pageSize+"
			String query_sql = "select top 10 * from Asend_work where s_worksid not in (select top "+(Integer.valueOf(pageNow)-1)*pageSize+" s_worksid from Asend_work) order by s_good desc";
			System.out.println("----->"+query_sql);
			rs=sql.executeQuery(query_sql);
			ResultSetMetaData metaData = rs.getMetaData();
			int cols_len = metaData.getColumnCount();
			while(rs.next())
			{
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i = 0; i < cols_len; i++)
				{
					String cols_name = metaData.getColumnName(i + 1);
					Object cols_value = rs.getObject(cols_name);
					if (cols_value == null)
					{
						cols_value = "";
					}
					map.put(cols_name, cols_value);
				}
				list.add(map);
			}
			PrintWriter out = response.getWriter();
			String json = GsonTool.createJsonString(list);
			json = ToUnicode.toUnicodeString(json);
			System.out.println("----->"+json);
			out.print(json);
			con.close();
		}
		catch (SQLException exp)
		{
			System.out.println(exp);
		}
//		System.out.println(worksInfo.toString());
//		PrintWriter out = response.getWriter();
//		System.out.println();
//		out.println(GsonTool.createJsonString(worksInfo));
//		RequestDispatcher dispatcher=request.getRequestDispatcher("show.jsp");
//		dispatcher.forward(request,response);
		/**
		 * List<Map<String, String>> list = null;
		Gson gson = new Gson();
		list = gson.fromJson(json, new TypeToken<List<Map<String, String>>>(){}.getType());
		 */
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		doPost(request, response);
	}
}
