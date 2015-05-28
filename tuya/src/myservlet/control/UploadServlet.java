package myservlet.control;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class UploadServlet extends HttpServlet {
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
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException{
		String path = request.getContextPath();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload fileUpload = new ServletFileUpload(factory);
		//设置文件上传大小
		fileUpload.setSizeMax(4*1024);
		fileUpload.setFileSizeMax(2*1024*1024);
		List<FileItem> list =null;
		try{
			list= fileUpload.parseRequest(request);
			for(FileItem item:list){
				//一种是表单数据，一种是图片数据
				if(!item.isFormField()){
					String img=item.getName();
					String file_upload_path = request.getRealPath("/upload")+"/"+img;
					System.out.println("图片上传的路径：："+file_upload_path);
					item.write(new File(file_upload_path));
				}
			}
			
		}catch(Exception e){
			
		}
	}
}



