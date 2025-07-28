package com.thinking.machines.hr.servlets;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;
import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.beans.*;
public class ConfirmDeleteDesignation extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
doPost(request,response);
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
int code=Integer.parseInt(request.getParameter("code"));
DesignationDAO designationDAO = new DesignationDAO();
try
{
DesignationDTO designationDTO = designationDAO.getByCode(code);
DesignationBean designationBean = new DesignationBean();
designationBean.setCode(designationDTO.getCode());
designationBean.setTitle(designationDTO.getTitle());
request.setAttribute("designationBean",designationBean);
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("ConfirmDeleteDesignation.jsp");
try
{
requestDispatcher.forward(request,response);
}catch(Exception exception)
{
System.out.println(exception.getMessage());
}





}catch(DAOException daoException)
{
//means such a code doesn't exist
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