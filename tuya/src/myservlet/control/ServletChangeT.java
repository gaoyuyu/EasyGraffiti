package myservlet.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mybean.data.Userinfo;

public class ServletChangeT extends HttpServlet
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

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		HttpSession session = request.getSession(true);
		Userinfo userinfo = (Userinfo) session.getAttribute("userinfo");
		if (null == userinfo)
		{
			response.sendRedirect("denglu.jsp");
			return;
		}
		else
		{
			System.out.println(userinfo.getUsername());
			try
			{
				contineWork(request, response, userinfo);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	public void contineWork(HttpServletRequest request,
			HttpServletResponse response, Userinfo userinfo)
			throws IOException, ServletException
	{
		// TODO Auto-generated method stub
		Connection con = null;
		Statement sql = null;
		HttpSession session = request.getSession(true);

		String userName = userinfo.getUsername();
		String userId = userinfo.getUserid();

		String tempAddress = Math.random() + "";
		File f1 = new File("D:/qwe", tempAddress);
		FileOutputStream fo = new FileOutputStream(f1);
		InputStream is = request.getInputStream();

		byte[] buf = new byte[1024];
		int length;
		while ((length = is.read(buf)) != -1)
		{
			fo.write(buf, 0, length);
		}
		is.close();
		fo.close();

		RandomAccessFile raf = new RandomAccessFile(f1, "r");
		int second = 1;
		String secondLine = null;
		while (second <= 2)
		{
			secondLine = raf.readLine();
			second++;
		}

		int position = secondLine.lastIndexOf("filename"); // ��ȡ�ڶ����г���filename��λ��"

		String fileName = secondLine.substring(position + 10, secondLine
				.length() - 1);
		byte[] cc = fileName.getBytes("ISO-8859-1");
		fileName = new String(cc);
		fileName = fileName.replace(" ", "");

		String saveFileName = userId + fileName; // �û�ͷ����

		// ��ȡ�����лس����ŵ�λ��
		raf.seek(0);
		long forthEndPosition = 0;
		int forth = 1;
		while ((length = raf.readByte()) != -1 && (forth <= 4))
		{
			if (length == '\n')
			{
				forthEndPosition = raf.getFilePointer();
				forth++;
			}
		}

		// ���ݿͻ��ϴ��ļ������֣������ļ����д���
		File dir = new File("D:/shengsiyuan/tuya/WebRoot/images");
		dir.mkdir();

		// ����ɾ���û������ϴ�����ͼ���ļ�
		File[] file = dir.listFiles();
		for (int k = 0; k < file.length; k++)
		{
			if (file[k].getName().startsWith(userName))
			{
				file[k].delete();
			}
		}

		File saveFile = new File(dir, saveFileName);
		RandomAccessFile raf2 = new RandomAccessFile(saveFile, "rw");
		raf.seek(raf.length()); // ��λ����ʱ�ļ��Ľ�β��
		long endPosition = raf.getFilePointer();
		long mark = endPosition;
		int j = 1;

		// ��ȡ��ʱ�ļ������лس�����λ��
		while ((mark >= 0) && (j <= 6))
		{
			mark--;
			raf.seek(mark);
			length = raf.read();

			if (length == '\n')
			{
				endPosition = raf.getFilePointer();
				j++;
			}
		}
		raf.seek(forthEndPosition);
		long startPoint = raf.getFilePointer();

		while (startPoint < endPosition - 1)
		{
			length = raf.readByte();
			raf2.write(length); // �༭�ϴ��ļ�
			startPoint = raf.getFilePointer();
		}
		f1.delete();
		raf2.close();
		raf.close();

		try
		{
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;database=tuya", "sa", "1qa2ws");
			sql = con.createStatement();
			String condition = "update users set photo='" + saveFileName
					+ "' where userid=" + userId;
			int result = sql.executeUpdate(condition);
			if (0 != result)
			{
				System.out.println("ͷ���޸ĳɹ���");
			}
			else
			{
				System.out.println("�Բ������ͼ����ܲ��ϸ�����ϴ�ʧ�ܣ�");
				return;
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return;
		}
		request.getRequestDispatcher("gerenziliao.jsp").forward(request,
				response);
	}
}
