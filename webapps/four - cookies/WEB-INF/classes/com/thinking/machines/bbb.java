package com.thinking.machines;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.net.*;
public class bbb extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
try
{
String name = request.getParameter("name");
String city = request.getParameter("city");
System.out.println("Data arrived.");
System.out.println("Name: "+name);
System.out.println("City: "+city);
Cookie c1 = new Cookie("name",URLEncoder.encode(name));
Cookie c2 = new Cookie("city",URLEncoder.encode(city));
response.addCookie(c1);
response.addCookie(c2);
response.setContentType("text/html");
PrintWriter pw;
pw = response.getWriter();

pw.println("<!DOCTYPE HTML>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<meta charset='utf-8'>");
pw.println("<title>Form Page 3</title>");
pw.println("</head>");

pw.println("<body>");
pw.println("Name: "+name+" <br>");
pw.println("City: "+city+" <br>");
pw.println("<a href='/four/ccc'>Save</a>");
pw.println("</body>");
pw.println("</html>");
}catch(Exception e)
{
System.out.println(e);
}
}
}