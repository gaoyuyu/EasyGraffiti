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
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.ToUnicode;
import GsonTool.GsonTool;

public class AndroidGetSendWork4LogoutServlet extends HttpServlet
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
			System.out.println(e);
		}
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String pageNow =request.getParameter("pageNow");
		int pageSize = 5;
		ArrayList<Map<String,Object>> list = null; 
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		String userid = request.getParameter("userid");
		try
		{
			list = new ArrayList<Map<String,Object>>();
			con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=tuya", "sa", "1qa2ws");
			String query_sql4logout = "select top 5 Asend_work.*  from Asend_work "
				+ " where Asend_work.s_worksid not in (select top "+(Integer.valueOf(pageNow)-1)*pageSize+" Asend_work.s_worksid from Asend_work order by s_worksid desc) order by s_worksid desc";
			st=con.createStatement();
			rs=st.executeQuery(query_sql4logout);
			System.out.println("query_sql4logout-->"+query_sql4logout);
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
			out.print(json);
			con.close();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("SQLException£º"+e.toString());
		}
	}
}
