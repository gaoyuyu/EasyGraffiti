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

public class AndroidGetWorkCommentServlet extends HttpServlet
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
		ArrayList<Map<String,Object>> list = null; 
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		String s_worksid = request.getParameter("s_worksid");
		try
		{
			list = new ArrayList<Map<String,Object>>();
			con=DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=tuya", "sa", "1qa2ws");
			st=con.createStatement();
			rs=st.executeQuery("select * from work_comments where s_worksid="+s_worksid);
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
			System.out.println("SQLException��"+e.toString());
		}
	}
}
