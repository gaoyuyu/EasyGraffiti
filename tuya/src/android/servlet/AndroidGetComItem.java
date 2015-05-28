package android.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mybean.data.AD_ComItem;
import mybean.data.AD_static;
import util.ToUnicode;
import GsonTool.GsonTool;

public class AndroidGetComItem extends HttpServlet
{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{
		PrintWriter out = response.getWriter();
		
//		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
//		Map<String,String> map = new HashMap<String, String>();
//		map.
		if(null !=AD_static.adComItem)
		{
			System.out.println("Get--------------------->"+AD_static.adComItem.toString());
			String json = GsonTool.createJsonString(AD_static.adComItem);
			json = ToUnicode.toUnicodeString(json);
			out.println(json);
			AD_static.adComItem = null;
		}
		else
		{
			out.println("0");
		}
	
		
	}
}
