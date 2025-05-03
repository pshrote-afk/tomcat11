package xyz;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
public class bbb extends HttpServlet
{
public void doGet(HttpServletRequest rq,HttpServletResponse rs)
{
String nn = rq.getParameter("nm");
String cc = rq.getParameter("ct");
String gg = rq.getParameter("gdr");
System.out.println("Data arrived");
System.out.println("Name: "+nn);
System.out.println("City: "+cc);
System.out.println("Gender: "+gg);
try
{
rs.setContentType("text/html");
PrintWriter pw;
pw = rs.getWriter();
pw.println("<!DOCTYPE html>");
pw.println("<head>");
pw.println("<meta charset='utf-8'>");
pw.println("<title>");
pw.println("My First Web Application");
pw.println("</title>");
pw.println("</head>");
pw.println("<body>");
pw.println("Data Saved, my people");
pw.println("<form action='/one/index.html'>");
pw.println("<button type='submit' onsubmit='/one/index.html'>Ok</button>");
pw.println("</body>");
pw.println("</html");
}catch(IOException io)
{
System.out.println(io);
}
}
}