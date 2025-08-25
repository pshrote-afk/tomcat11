package com.thinking.machines.hr.servlets.javascript;
import com.thinking.machines.hr.dl.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
public class EmployeesDesignationCodeExists extends HttpServlet
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
PrintWriter pw =null;
response.setContentType("plain/text");
try
{
pw = response.getWriter();
int designationCode = Integer.parseInt(request.getParameter("designationCode"));
EmployeeDAO employeeDAO = new EmployeeDAO();
boolean exists = employeeDAO.designationCodeExists(designationCode);
if(exists) pw.print("true");
else pw.print("false");
}catch(DAOException daoException)
{

}
catch(Exception e)
{
System.out.println(e.getMessage());
}
}


}//end of class