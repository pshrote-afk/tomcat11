package com.thinking.machines.hr.servlets;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.beans.*;
import java.text.*;
public class EditEmployee extends HttpServlet
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
String employeeId = request.getParameter("employeeId");
EmployeeDAO employeeDAO = new EmployeeDAO();
EmployeeDTO employeeDTO = new EmployeeDTO();
try
{
employeeDTO = employeeDAO.getByEmployeeId(employeeId);
//control moves forward means such a code exists
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
EmployeeBean employeeBean = new EmployeeBean();
employeeBean.setEmployeeId(employeeDTO.getEmployeeId());
employeeBean.setName(employeeDTO.getName());
employeeBean.setDesignationCode(employeeDTO.getDesignationCode());
employeeBean.setTitle(employeeDTO.getTitle());
employeeBean.setDateOfBirth(simpleDateFormat.format(employeeDTO.getDateOfBirth()));
employeeBean.setGender(employeeDTO.getGender());
employeeBean.setIsIndian(employeeDTO.getIsIndian() ? "Y" : "N");
employeeBean.setBasicSalary(employeeDTO.getBasicSalary().toPlainString());
employeeBean.setPanNumber(employeeDTO.getPANNumber());
employeeBean.setAadharCardNumber(employeeDTO.getAadharCardNumber());

request.setAttribute("employeeBean",employeeBean);
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("/EmployeeEditForm.jsp");
try
{
requestDispatcher.forward(request,response); 
}catch(Exception exception)
{
System.out.println(exception.getMessage());
}
}catch(DAOException daoException)
{
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("Employees.jsp");
try
{
requestDispatcher.forward(request,response); 
}catch(Exception exception)
{
System.out.println(exception.getMessage());
}
}
}
}