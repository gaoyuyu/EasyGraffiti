package myservlet.control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpLoad extends HttpServlet
{
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String tempAddress = Math.random() + ".jpg";
		File f1 = new File("d:/qweqwe", tempAddress);
		FileOutputStream fos = new FileOutputStream(f1);
		InputStream is = request.getInputStream();

		byte[] buf = new byte[1024];
		int length = 0;
		while (-1 != (length = is.read(buf)))
		{
			fos.write(buf, 0, length);
		}

		is.close();
		fos.close();

		RandomAccessFile randomAccessFile1 = new RandomAccessFile(f1, "r");
		int second = 1;
		String secondLine = null;
		while (second <= 2)
		{
			secondLine = randomAccessFile1.readLine();
			second++;
		}
		int position = secondLine.lastIndexOf("filename") + 10;

		String fileName = secondLine.substring(position,
				secondLine.length() - 1);

		String saveFileName = Math.random() + fileName;

		randomAccessFile1.seek(0);
		long forthEndPosition = 0;
		int forth = 1;
		while (-1 != (length = randomAccessFile1.readByte()) && forth <= 4)
		{
			if (length == '\n')
			{
				forthEndPosition = randomAccessFile1.getFilePointer();
				forth++;
			}
		}

		File f2 = new File("d:/qweqwe", saveFileName);
		RandomAccessFile randomAccessFile2 = new RandomAccessFile(f2, "rw");

		randomAccessFile1.seek(randomAccessFile1.length());
		long endPosition = randomAccessFile1.getFilePointer();
		long mark = endPosition;
		int j = 1;
		while ((mark >= 0) && (j <= 6))
		{
			mark--;
			randomAccessFile1.seek(mark);
			length = randomAccessFile1.read();

			if (length == '\n')
			{
				endPosition = randomAccessFile1.getFilePointer();
				j++;
			}
		}
		randomAccessFile1.seek(forthEndPosition);
		long startPoint = randomAccessFile1.getFilePointer();

		while (startPoint < endPosition - 1)
		{
			length = randomAccessFile1.readByte();
			randomAccessFile2.write(length); // 编辑上传文件
			startPoint = randomAccessFile1.getFilePointer();
		}

		f1.delete();
		randomAccessFile2.close();
		randomAccessFile1.close();
		System.out.println("文件上传成功！");
		request.getRequestDispatcher("send.jsp").forward(request, response);
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			doPost(request, response);
		}
		catch (ServletException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
