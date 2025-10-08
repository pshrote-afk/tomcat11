package com.thinking.machines.hr.servlets;
import java.util.*;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import com.thinking.machines.hr.dl.*;
public class ServletThree extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
try
{
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
}catch(Exception e)
{
//do nothing
}
}

public void doPost(HttpServletRequest request,HttpServletResponse response)
{
try
{

String firstName = request.getParameter("firstName");
String lastName = request.getParameter("lastName");
String age = request.getParameter("age");
System.out.println(firstName + lastName + age);
PrintWriter pw = response.getWriter();
response.setContentType("text/plain");
pw.println(firstName+","+lastName+","+age);
}catch(Exception e)
{
try
{
response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
}catch(Exception ee)
{
}
}
}//end doPost
}//end class