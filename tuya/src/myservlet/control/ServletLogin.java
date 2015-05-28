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

public class ServletLogin extends HttpServlet {
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException£º"+e);
		}
	}

	public String handleString(String s) {
		try {
			byte b[] = s.getBytes("ISO-8859-1");
			s = new String(b);
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		return s;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=GB2312");
		request.setCharacterEncoding("GB2312");
		response.setCharacterEncoding("GB2312");
		PrintWriter out = response.getWriter();
		String userid = "";
		Connection con;
		Statement sql;
		Userinfo userinfo = null;
		HttpSession session = request.getSession(true);
		try {
			userinfo = (Userinfo) session.getAttribute("userinfo");
			if (userinfo == null) {
				userinfo = new Userinfo();
				session.setAttribute("userinfo", userinfo);
			}
		} catch (Exception ee) {
			System.out.println("Exception£º"+ee);
		}

		String email = request.getParameter("email").trim();
		String password = request.getParameter("password").trim();

		boolean ok = userinfo.getSuccess();
		email = handleString(email);
		password = handleString(password);

		boolean boo = (email.length() > 0) && (password.length() > 0);
		try {
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=tuya", "sa", "1qa2ws");
			String condition = "select * from users where email='" + email
					+ "' AND " + " password='" + password + "' ";
			sql = con.createStatement();
			if (boo) {
				ResultSet rs = sql.executeQuery(condition);
				boolean m = rs.next();
				if (m == true) {
					userinfo.setUserid(rs.getString(10));
					userinfo.setSuccess(true);
					userinfo.setEmail(email);
				} else {
					userinfo.setSuccess(false);
				}
			} else {
				System.out.println("boo£º"+boo);
			}
			con.close();
		} catch (SQLException e) {

			System.out.println("SQLException£º" + e);
		}
		System.out.println("request.getRequestURI()£º"+request.getRequestURI());
		System.out.println("request.getQueryString()£º"+request.getQueryString());
        if(userinfo.getSuccess()) {
		RequestDispatcher dispatcher = request
				.getRequestDispatcher("index.jsp");
		dispatcher.forward(request, response);
		out.println("1");
        }else {
        	out.println("0");
        	response.setStatus(404);
        }
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
