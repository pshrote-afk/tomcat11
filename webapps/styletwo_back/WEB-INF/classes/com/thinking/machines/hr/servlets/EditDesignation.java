package com.thinking.machines.hr.servlets;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.beans.*;
public class EditDesignation extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
doPost(request,response);
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
int code=0;
try
{
code = Integer.parseInt(request.getParameter("code"));
}catch(NumberFormatException numberFormatException)
{
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("Designations.jsp");
try
{
requestDispatcher.forward(request,response);
}catch(Exception exception)
{
System.out.println(exception.getMessage());
}

}

DesignationDAO designationDAO = new DesignationDAO();
DesignationDTO designationDTO = new DesignationDTO();
try
{
designationDTO = designationDAO.getByCode(code);
//control moves forward means such a code exists
DesignationBean designationBean = new DesignationBean();
designationBean.setCode(designationDTO.getCode());
designationBean.setTitle(designationDTO.getTitle());
request.setAttribute("designationBean",designationBean);
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("/DesignationEditForm.jsp");
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
requestDispatcher = request.getRequestDispatcher("Designations.jsp");
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