package com.thinking.machines.hr.servlets.javascript;
import com.thinking.machines.hr.dl.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import com.google.gson.*;
public class EmployeesDelete extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
doPost(request,response);
}

public void doPost(HttpServletRequest request,HttpServletResponse response)
{
//check Authentication
HttpSession httpSession = request.getSession();
if(httpSession.getAttribute("username")==null)
{
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("/LoginForm.jsp");
try
{
requestDispatcher.forward(request,response);
return;
}catch(Exception exception)
{
//do nothing
}
}
PrintWriter pw = null;
try
{
pw = response.getWriter();
response.setContentType("application/json");
//extract data from request 

String employeeId = request.getParameter("employeeId");
EmployeeDAO employeeDAO = new EmployeeDAO();
try
{
employeeDAO.deleteByEmployeeId(employeeId);

Gson gson = new Gson();
JsonObject jsonObject = new JsonObject();
jsonObject.addProperty("success",true);
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
}
}catch(Exception exception)
{
System.out.println(exception.getMessage()); //remove after testing
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("ErrorPage.jsp");
try
{
requestDispatcher.forward(request,response);
}catch(Exception e)
{
//do nothing
}
}
}
}