package com.thinking.machines.hr.servlets.javascript;
import com.thinking.machines.hr.dl.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.text.*;
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
response.setContentType("text/plain");
EmployeeDAO employeeDAO = new EmployeeDAO();
try
{
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
EmployeeDTO employeeDTO = employeeDAO.getByEmployeeId(employeeId);
pw.print("true" + ",");
pw.print(employeeDTO.getEmployeeId()+",");
pw.print(employeeDTO.getName()+",");
pw.print(employeeDTO.getDesignationCode()+",");
pw.print(employeeDTO.getTitle()+",");
pw.print(simpleDateFormat.format(employeeDTO.getDateOfBirth())+",");
pw.print(employeeDTO.getGender()+",");
pw.print(employeeDTO.getIsIndian()+",");
pw.print(employeeDTO.getBasicSalary().toPlainString()+",");
pw.print(employeeDTO.getPANNumber()+",");
pw.print(employeeDTO.getAadharCardNumber()+",");
}catch(DAOException daoException)
{
pw.print("false" + "," + daoException.getMessage());
return;
}
}catch(Exception exception)
{
pw.print("false" + "," + exception.getMessage());
}
}//end of doGet
}//end of class