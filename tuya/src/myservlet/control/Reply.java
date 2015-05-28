package myservlet.control;

import java.io.IOException;
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

public class Reply extends HttpServlet {

	Connection con = null;
	Statement statement = null;
	ResultSet rs1 = null, rs2 = null;
	String worksid;
	PreparedStatement ps = null;

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
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=gb2312");
		request.setCharacterEncoding("gb2312");
		response.setCharacterEncoding("gb2312");
		String content = handleString(request.getParameter("content")); //评论内容
		String worksname = handleString(request.getParameter("worksname"));//作品名字
        String username = handleString(request.getParameter("username"));//评论者
		System.out.println("->>"+content+","+worksname+","+username);
		try {
			String sql1 = "select worksid from works where title like '"+ worksname + "'";
			System.out.println(" id  sql->>" + sql1);
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=tuya", "sa", "1qa2ws");
			statement = con.createStatement();
			rs1 = statement.executeQuery(sql1);
			while (rs1.next()) {
				worksid = rs1.getString("worksid");
			}

		    System.out.println("id=>"+worksid);
		    String sql2 = "insert into comment( com_content,username,worksid ) values(?,?,?)  ";
			ps = con.prepareStatement(sql2);
			ps.setString(1, content);
			ps.setString(2, username);
			ps.setString(3, worksid);
			int result = ps.executeUpdate();
			if (result!=0)
				System.out.println("手机端评论成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}