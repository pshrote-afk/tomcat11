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
Cookie cookies[] = request.getCookies();
String name="";
String city="";
int i=0;
Cookie c;
if(cookies!=null)
{
for(i=0;i<cookies.length;i++)
{
c=cookies[i];
if(c.getName().equals("name"))
{
name=c.getValue();
break;
}
}
if(name.length()>0) name = URLDecoder.decode(name);
for(i=0;i<cookies.length;i++)
{
c=cookies[i];
if(c.getName().equals("city"))
{
city=c.getValue();
break;
}
}
if(name.length()>0) city = URLDecoder.decode(city);
}

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
pw.println("<a href='/four/index.html'>Ok</a>");
pw.println("</body>");
pw.println("</html>");
}catch(Exception e)
{
System.out.println(e);
}
}
}