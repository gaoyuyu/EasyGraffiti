package myservlet.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import GsonTool.GsonTool;

import mybean.data.Comment;
import mybean.data.Message;

public class Show extends HttpServlet {
	Connection con = null;
	Statement statement=null;
	ResultSet rs1=null,rs2 = null;
	String worksid;
	// 处理字符串的方法
	public String handleString(String s)
	{
		try
		{
			byte b[] = s.getBytes("ISO-8859-1");
			s = new String(b,"gb2312");
		}
		catch (Exception ee)
		{
			ee.printStackTrace();
		}
		return s;
	}
	
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
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		response.setContentType("text/html;charset=gb2312");
		request.setCharacterEncoding("gb2312");
		response.setCharacterEncoding("gb2312");
		String name = request.getParameter("name");
		String newname = handleString(name);
		PrintWriter out = response.getWriter();
		List<Comment> comments=new ArrayList<Comment>();
		System.out.println("name->>"+newname);
		
		try {
			String sql1 = "select worksid from works where title like '"+newname+"'";
			System.out.println(" id  sql->>"+sql1);
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=tuya", "sa", "1qa2ws");
			statement = con.createStatement();
			rs1 =statement.executeQuery(sql1);
			while(rs1.next()){
				worksid=rs1.getString("worksid");
			}
			    System.out.println("id=>"+worksid);
			    
			String sql2 = "select com_content,username from comment where worksid='"+worksid+"'";
			System.out.println(" comment  sql->>"+sql2);
			rs2 = statement.executeQuery(sql2);
			while(rs2.next()){
				Comment comment = new Comment();
				comment.setWorksId(worksid);
				comment.setComment(rs2.getString("com_content"));
				comment.setUsername(rs2.getString("username"));
				comments.add(comment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String s2 =GsonTool.createJsonString(comments);
		Gson gson = new Gson();
		List<Comment> list = gson.fromJson(s2,  
                new TypeToken<List<Comment>>() {  
                }.getType());  
		for (Comment com : list) {  
            System.out.println(com.toString());  
        } 
//		out.print(GsonTool.createJsonString(comments));
		request.setAttribute("jsonString", GsonTool.createJsonString(comments));
		request.getRequestDispatcher("/showcomment.jsp").forward(
				request, response);
		
	}
	
	public String getId(String title){
		
		
		return null;
	}
}
