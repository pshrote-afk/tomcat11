package com.thinking.machines.hr.servlets;
import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.beans.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
public class DeleteEmployee extends HttpServlet
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
try
{
//extract data from bean
EmployeeBean employeeBean;
employeeBean = (EmployeeBean)request.getAttribute("employeeBean");
String employeeId = employeeBean.getEmployeeId();

EmployeeDTO employeeDTO = new EmployeeDTO();
EmployeeDAO employeeDAO = new EmployeeDAO();

try
{
employeeDTO = employeeDAO.deleteByEmployeeId(employeeId);
MessageBean messageBean = new MessageBean();
messageBean.setHeading("Employee (Delete Module)");
messageBean.setMessage("Employee ("+employeeDTO.getName()+") deleted successfully.");
messageBean.setGenerateButtons(true);
messageBean.setGenerateTwoButtons(false);
messageBean.setButtonOneText("OK");
messageBean.setButtonOneAction("Employees.jsp");

request.setAttribute("messageBean",messageBean);
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("/Notification.jsp");
requestDispatcher.forward(request,response); 	//may throw ServletException
}catch(DAOException daoException)
{
MessageBean messageBean = new MessageBean();
messageBean.setHeading("Employee (Delete Module)");
messageBean.setMessage(daoException.getMessage());
messageBean.setGenerateButtons(true);
messageBean.setGenerateTwoButtons(false);
messageBean.setButtonOneText("OK");
messageBean.setButtonOneAction("Employees.jsp");
request.setAttribute("messageBean",messageBean);
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("Notification.jsp");
requestDispatcher.forward(request,response);		//may throw ServletException
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