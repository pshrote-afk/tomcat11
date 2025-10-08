package com.thinking.machines.hr.servlets.javascript;
import com.thinking.machines.hr.dl.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import com.google.gson.*;
public class EmployeesAadharCardNumberExists extends HttpServlet
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
PrintWriter pw = null;
response.setContentType("plain/text");
try
{
pw = response.getWriter();
String aadharCardNumber = request.getParameter("aadharCardNumber");
EmployeeDAO employeeDAO = new EmployeeDAO();
boolean exists = employeeDAO.aadharCardNumberExists(aadharCardNumber);

Gson gson = new Gson();
JsonObject jsonObject = new JsonObject();

if(exists)
{
jsonObject.addProperty("success",true);
String jsonString = gson.toJson(jsonObject);
pw.print(jsonString);
pw.flush();
}
else
{
jsonObject.addProperty("success",false);
String jsonString = gson.toJson(jsonObject);
pw.print(jsonString);
pw.flush();
}
}catch(DAOException daoException)
{
Gson gson = new Gson();
JsonObject jsonObject = new JsonObject();
jsonObject.addProperty("success",true);
String jsonString = gson.toJson(jsonObject);
pw.print(jsonString);
pw.flush();
}
catch(Exception e)
{
System.out.println(e.getMessage());
}
}


}//end of class