package android.servlet;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class AndroidPublishPicServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		String parh = request.getContextPath();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		//设置上传文件的大小
		fileUpload.setSizeMax(4*1024*1024);
		fileUpload.setFileSizeMax(2*1024*1024);
		List<FileItem> list = null;
		try
		{
			list = fileUpload.parseRequest(request);
			for(FileItem item:list)
			{
				//一种是表当数据，一种是图片数据
				if(!item.isFormField())
				{
					String img = item.getName();
					String file_upload_path ="D:\\GAO\\tuya\\WebRoot\\"+"publish"+"\\"+img;
					System.out.println("-------图片上传路径-------"+file_upload_path);
					item.write(new File(file_upload_path));
				}
			}
		} catch (Exception e)
		{
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("Exception："+e.toString());
			
		}
		
		
		
		
		
		
	}
}
