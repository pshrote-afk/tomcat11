package com.thinking.machines.hr.servlets.javascript;
import com.thinking.machines.hr.dl.*;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import com.google.gson.*;
public class DesignationsDelete extends HttpServlet
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
PrintWriter pw = null; 
response.setContentType("application/json");
try
{
pw = response.getWriter();
int code = Integer.parseInt(request.getParameter("code"));
DesignationDAO designationDAO = new DesignationDAO();
designationDAO.delete(code);

Gson gson = new Gson();
JsonObject jsonObject = new JsonObject();
jsonObject.addProperty("success",true);
String jsonString = gson.toJson(jsonObject);
pw.print(jsonString);
pw.flush();	
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());

Gson gson = new Gson();
JsonObject jsonObject = new JsonObject();
jsonObject.addProperty("success",true);
jsonObject.addProperty("data",daoException.getMessage());
String jsonString = gson.toJson(jsonObject);
pw.print(jsonString);
pw.flush();	
return;
}
catch(Exception e)
{
try
{
System.out.println(e.getMessage());
response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
}catch(Exception ee)
{
}
}
}//end doPost
}