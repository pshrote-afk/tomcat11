package com.thinking.machines;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
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
response.setContentType("text/html");
PrintWriter pw;
pw = response.getWriter();

pw.println("<!DOCTYPE HTML>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<meta charset='utf-8'>");
pw.println("<title>Form Page 3</title>");
pw.println("<script>");
pw.println("function validateForm(frm)");
pw.println("{"); 
pw.println("var mm=document.getElementById('male')");
pw.println("var ff=document.getElementById('female')");
pw.println("if(mm.checked==false && ff.checked==false)");
pw.println("{");
pw.println("alert('Select gender');");
pw.println("return false;");;
pw.println("}");
pw.println("return true;");
pw.println("}");
pw.println("</script>");
pw.println("</head>");

pw.println("<body>");
pw.println("<form action='/two/ccc' onsubmit='return validateForm(this)'>");
pw.println("<input type='hidden' id='name' name='name' value='"+name+"'>");
pw.println("<input type='hidden' id='city' name='city' value='"+city+"'>");
pw.println("Gender");
pw.println("<input type='radio' id='male' name='gender' value='M'> Male");
pw.println("&nbsp;&nbsp;&nbsp;&nbsp;");
pw.println("<input type='radio' id='female' name='gender' value='F'> Female <br>");

pw.println("<button type='submit'>Save</button>");
pw.println("</form>");
pw.println("</body>");
pw.println("</html>");
}catch(Exception e)
{
System.out.println(e);
}
}
}