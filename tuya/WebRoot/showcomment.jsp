<%@ page language="java" import="java.util.*" pageEncoding="gb2312"%>
<%@ page import="mybean.data.Comment" %>
<%
       String jsonString = (String)request.getAttribute("jsonString");


 %>
         <%
         out.print(jsonString);
          %>

