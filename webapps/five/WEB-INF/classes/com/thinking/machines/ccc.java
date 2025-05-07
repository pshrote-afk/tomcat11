package com.thinking.machines;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.net.*;
public class ccc extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
try
{
HttpSession ss = request.getSession();
String name = (String)ss.getAttribute("name");
String city = (String)ss.getAttribute("city");

response.setContentType("text/html");
PrintWriter pw;
pw = response.getWriter();

pw.println("<!DOCTYPE HTML>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<meta charset='utf-8'>");
pw.println("<title>Summary page</title>");
pw.println("</head>");

pw.println("<body>");
pw.println("Data saved.<br>");
pw.println("Name: "+name+" <br>");
pw.println("City: "+city+" <br>");
pw.println("<a href='/five/index.html'>Ok</a>");
pw.println("</body>");
pw.println("</html>");
}catch(Exception e)
{
System.out.println(e);
}
}
}