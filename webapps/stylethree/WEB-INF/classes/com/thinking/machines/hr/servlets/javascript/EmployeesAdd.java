package com.thinking.machines.hr.servlets.javascript;
import com.thinking.machines.hr.dl.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.text.*;
import java.math.*;
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
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
try
{
pw = response.getWriter();
response.setContentType("text/plain");
//extract data from request 

String name = request.getParameter("name");
int designationCode = Integer.parseInt(request.getParameter("designationCode"));
java.util.Date dateOfBirth = simpleDateFormat.parse(request.getParameter("dateOfBirth"));
String gender = request.getParameter("gender");
boolean isIndian = Boolean.parseBoolean(request.getParameter("isIndian"));
String basicSalary = request.getParameter("basicSalary");
String panNumber = request.getParameter("panNumber");
String aadharCardNumber = request.getParameter("aadharCardNumber");

EmployeeDAO employeeDAO = new EmployeeDAO();
EmployeeDTO employeeDTO = new EmployeeDTO();

employeeDTO.setName(name);
employeeDTO.setDesignationCode(designationCode);
employeeDTO.setDateOfBirth(dateOfBirth);
employeeDTO.setGender(gender);
employeeDTO.setIsIndian(isIndian);
employeeDTO.setBasicSalary(new BigDecimal(basicSalary));
employeeDTO.setPANNumber(panNumber);
employeeDTO.setAadharCardNumber(aadharCardNumber);
try
{
employeeDAO.add(employeeDTO);
pw.print("true");
}catch(DAOException daoException)
{
pw.print("false"+","+daoException.getMessage());
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