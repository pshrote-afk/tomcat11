package com.thinking.machines.hr.servlets.javascript;
import com.thinking.machines.hr.dl.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import com.google.gson.*;
public class EmployeesGetByEmployeeId extends HttpServlet
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
PrintWriter pw=null;
try
{
pw = response.getWriter();
String employeeId = request.getParameter("employeeId");
response.setContentType("application/json");
EmployeeDAO employeeDAO = new EmployeeDAO();
try
{
EmployeeDTO employeeDTO = employeeDAO.getByEmployeeId(employeeId);

GsonBuilder gsonBuilder = new GsonBuilder();	//purpose: to customize Gson
gsonBuilder.setDateFormat("yyyy-MM-dd");	// instead of SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
Gson gson = gsonBuilder.create(); 	//returns instance of customized Gson class

JsonObject jsonObject = new JsonObject();
jsonObject.addProperty("success",true);
jsonObject.add("data",gson.toJsonTree(employeeDTO));

String jsonString = gson.toJson(jsonObject);
pw.print(jsonString);
pw.flush();
}catch(DAOException daoException)
{
Gson gson = new Gson();
JsonObject jsonObject = new JsonObject();
jsonObject.addProperty("success",false);
jsonObject.addProperty("errorMessage",daoException.getMessage());
String jsonString = gson.toJson(jsonObject);
pw.print(jsonString);
pw.flush();
return;
}
}catch(Exception exception)
{
Gson gson = new Gson();
JsonObject jsonObject = new JsonObject();
jsonObject.addProperty("success",false);
jsonObject.addProperty("errorMessage",exception.getMessage());
String jsonString = gson.toJson(jsonObject);
pw.print(jsonString);
pw.flush();
}
}//end of doGet
}//end of class