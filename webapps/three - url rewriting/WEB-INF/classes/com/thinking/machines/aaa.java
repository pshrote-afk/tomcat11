package com.thinking.machines;
import java.io.*; //for PrintWriter
import jakarta.servlet.*;
import jakarta.servlet.http.*;
public class aaa extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
try
{
String name = request.getParameter("name");
System.out.println("Data arrived.");
System.out.println("Name: "+name);
PrintWriter pw;
pw = response.getWriter();
response.setContentType("text/html");

pw.println("<!DOCTYPE HTML>");
pw.println("<html lang='en'>");
pw.println("<head>");
pw.println("<meta charset='utf-8'>");
pw.println("<title>Form Page 2</title>");
pw.println("<script>");
pw.println("function validateForm(frm)");
pw.println("{"); 
pw.println("var ct=frm.city.value.trim()");
pw.println("if(ct.length==0)");
pw.println("{"); 
pw.println("alert('City required')");
pw.println("frm.city.focus();");
pw.println("return false;");
pw.println("}");
pw.println("return true");
pw.println("}");
pw.println("</script>");
pw.println("</head>");

pw.println("<body>");
pw.println("<form action='/three/bbb' onsubmit='return validateForm(this)'>");
pw.println("<input type='hidden' id='name' name='name' value='"+name+"'>");
pw.println("City");
pw.println("<input type='text' id='city' name='city'>");
pw.println("<button type='submit'>Next</button>");
pw.println("</form>");
pw.println("</body>");
pw.println("</html>");
}catch(Exception e)
{
System.out.println(e); //remove after testing
}
}
}