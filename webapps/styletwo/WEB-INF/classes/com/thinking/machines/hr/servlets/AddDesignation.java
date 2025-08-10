package com.thinking.machines.hr.servlets;
import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.beans.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
public class AddDesignation extends HttpServlet
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
DesignationBean designationBean;
designationBean = (DesignationBean)request.getAttribute("designationBean");
String title = designationBean.getTitle();
DesignationDTO designationDTO = new DesignationDTO();
designationDTO.setTitle(title);
DesignationDAO designationDAO = new DesignationDAO();
try
{
designationDAO.add(designationDTO);
designationBean.setCode(designationDTO.getCode());
MessageBean messageBean = new MessageBean();
messageBean.setHeading("Designation (Add Module)");
messageBean.setMessage("Designation added. Add more?");
messageBean.setGenerateButtons(true);
messageBean.setGenerateTwoButtons(true);
messageBean.setButtonOneText("Yes");
messageBean.setButtonOneAction("DesignationAddForm.jsp");
messageBean.setButtonTwoText("No");
messageBean.setButtonTwoAction("Designations.jsp");

request.setAttribute("messageBean",messageBean);
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("/Notification.jsp");
requestDispatcher.forward(request,response); 	//may throw ServletException
}catch(DAOException daoException)
{
ErrorBean errorBean = new ErrorBean();
errorBean.setError(daoException.getMessage());

request.setAttribute("errorBean",errorBean);
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("/DesignationAddForm.jsp");
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