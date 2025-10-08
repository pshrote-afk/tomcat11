package com.thinking.machines.hr.servlets.javascript;
import com.thinking.machines.hr.dl.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.text.*;
import java.math.*;
import com.google.gson.*;
public class EmployeesUpdate extends HttpServlet
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
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
try
{
pw = response.getWriter();
response.setContentType("application/json");
//extract data from request 

BufferedReader bufferedReader = request.getReader();
StringBuffer stringBuffer = new StringBuffer();
String x;
while(true)
{
x = bufferedReader.readLine();
if(x==null) break;
stringBuffer.append(x);
}
String rawData = stringBuffer.toString();

Gson gson = new Gson();

EmployeeDAO employeeDAO = new EmployeeDAO();
EmployeeDTO employeeDTO;
employeeDTO = gson.fromJson(rawData,EmployeeDTO.class);

try
{
employeeDAO.update(employeeDTO);
JsonObject jsonObject = new JsonObject();
jsonObject.addProperty("success",true);
String jsonString = gson.toJson(jsonObject);
pw.print(jsonString);
pw.flush();
}catch(DAOException daoException)
{
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