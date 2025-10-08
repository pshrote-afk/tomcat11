package com.thinking.machines.hr.servlets.javascript;
import com.thinking.machines.hr.dl.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
//import java.text.*;
//import java.math.*;
import com.google.gson.*;
public class EmployeesAdd extends HttpServlet
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
//SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
try
{
Gson gson = new Gson();
pw = response.getWriter();
response.setContentType("application/json");
//extract data from request 

/*
String name = request.getParameter("name");
int designationCode = Integer.parseInt(request.getParameter("designationCode"));
java.util.Date dateOfBirth = simpleDateFormat.parse(request.getParameter("dateOfBirth"));
String gender = request.getParameter("gender");
boolean isIndian = Boolean.parseBoolean(request.getParameter("isIndian"));
String basicSalary = request.getParameter("basicSalary");
String panNumber = request.getParameter("panNumber");
String aadharCardNumber = request.getParameter("aadharCardNumber");

employeeDTO.setName(name);
employeeDTO.setDesignationCode(designationCode);
employeeDTO.setDateOfBirth(dateOfBirth);
employeeDTO.setGender(gender);
employeeDTO.setIsIndian(isIndian);
employeeDTO.setBasicSalary(new BigDecimal(basicSalary));
employeeDTO.setPANNumber(panNumber);
employeeDTO.setAadharCardNumber(aadharCardNumber);

*/

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
EmployeeDAO employeeDAO = new EmployeeDAO();
EmployeeDTO employeeDTO;
employeeDTO = gson.fromJson(rawData,EmployeeDTO.class);
try
{
employeeDAO.add(employeeDTO);

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