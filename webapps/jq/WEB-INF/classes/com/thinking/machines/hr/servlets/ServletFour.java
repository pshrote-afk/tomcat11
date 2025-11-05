package com.thinking.machines.hr.servlets;
import java.util.*;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import com.thinking.machines.hr.dl.*;
import com.google.gson.*;
public class ServletFour extends HttpServlet
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
Thread.sleep(2000);

String name = request.getParameter("name");
int age = Integer.parseInt(request.getParameter("age"));

Gson gson = new Gson();

JsonObject jsonReply2 = new JsonObject();
jsonReply2.addProperty("name",name);
jsonReply2.addProperty("age",age);

JsonObject jsonReply1 = new JsonObject();
jsonReply1.add("message",jsonReply2);

PrintWriter pw = response.getWriter();
response.setContentType("application/json");
pw.print(gson.toJson(jsonReply1));
pw.flush();
}catch(Exception e)
{
try
{
System.out.println(e);
response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
}catch(Exception ee)
{
}
}
}//end doPost
}//end class