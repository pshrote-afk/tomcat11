package com.thinking.machines.hr.servlets.javascript;
import com.thinking.machines.hr.dl.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.*;
import com.google.gson.*;
public class DesignationsGetAll extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
try
{
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
}catch(Exception e)
{
}
}
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
try
{
PrintWriter pw = response.getWriter();
response.setContentType("application/json");

DesignationDAO designationDAO = new DesignationDAO();
try
{
List<DesignationDTO> designations = designationDAO.getAll();

Gson gson = new Gson();
String jsonString = gson.toJson(designations);
pw.print(jsonString);
pw.flush();

}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());

//reroute to error page

}

}catch(Exception exception)
{
System.out.println(exception.getMessage());
}
}//end of doGet
}//end of class