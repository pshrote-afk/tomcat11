package com.thinking.machines.hr.servlets;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;
import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.beans.*;
public class DeleteDesignation extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
doPost(request,response);
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
DesignationDAO designationDAO = new DesignationDAO();
//extract bean from request scope
DesignationBean designationBean;
designationBean = (DesignationBean)request.getAttribute("designationBean");
int code = designationBean.getCode();
try
{
designationDAO.delete(code);
MessageBean messageBean = new MessageBean();
messageBean.setHeading("Designation (Delete Module)");
messageBean.setMessage("Designation ("+designationBean.getTitle()+") successfully deleted.");
messageBean.setGenerateButtons(true);
messageBean.setGenerateTwoButtons(false);
messageBean.setButtonOneText("OK");
messageBean.setButtonOneAction("Designations.jsp");
request.setAttribute("messageBean",messageBean);
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("Notification.jsp");
try
{
requestDispatcher.forward(request,response);
}catch(Exception exception)
{
System.out.println(exception.getMessage());
}

}catch(DAOException daoException)
{
MessageBean messageBean = new MessageBean();
messageBean.setHeading("Designation (Delete Module)");
messageBean.setMessage(daoException.getMessage());
messageBean.setGenerateButtons(true);
messageBean.setGenerateTwoButtons(false);
messageBean.setButtonOneText("OK");
messageBean.setButtonOneAction("Designations.jsp");
request.setAttribute("messageBean",messageBean);
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("Notification.jsp");
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