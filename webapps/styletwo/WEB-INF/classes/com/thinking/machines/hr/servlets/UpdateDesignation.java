package com.thinking.machines.hr.servlets;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.beans.*;
import java.util.*;
public class UpdateDesignation extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
doPost(request,response);
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
DesignationDTO designationDTO = new DesignationDTO();
DesignationDAO designationDAO = new DesignationDAO();

//extract data from request scope
DesignationBean designationBean;
designationBean = (DesignationBean)request.getAttribute("designationBean");
designationDTO.setCode(designationBean.getCode());
designationDTO.setTitle(designationBean.getTitle());

int code = Integer.parseInt(request.getParameter("code"));
String title = request.getParameter("title");
designationDTO.setCode(code);
designationDTO.setTitle(title);

try
{
designationDAO.update(designationDTO);
MessageBean messageBean = new MessageBean();
messageBean.setHeading("Designation (Edit Module)");
messageBean.setMessage("Designation successfully updated to ("+designationDTO.getTitle()+")");
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
//DesignationBean designationBean = new DesignationBean();
//designationBean.setCode(designationDTO.getCode());
//designationBean.setTitle(designationDTO.getTitle());
//request.setAttribute("designationBean",designationBean);

ErrorBean errorBean = new ErrorBean();
errorBean.setError(daoException.getMessage());
request.setAttribute("errorBean",errorBean);
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("/DesignationEditForm.jsp");
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