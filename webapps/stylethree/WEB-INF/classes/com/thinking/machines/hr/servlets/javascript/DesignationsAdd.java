package com.thinking.machines.hr.servlets.javascript;
import com.thinking.machines.hr.dl.*;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
public class DesignationsAdd extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
try
{
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
}catch(Exception e)
{
//do nothing
}
}

public void doPost(HttpServletRequest request,HttpServletResponse response)
{
PrintWriter pw = null; 
response.setContentType("text/plain");
try
{
pw = response.getWriter();
//later add: formId check
/*
String formId = request.getParameter("formId");
HttpSession httpSession = (HttpSession)request.getSession();
String sessionFormId = (String)httpSession.getAttribute(formId);
httpSession.removeAttribute(formId);
if(formId.equals(sessionFormId) == false)
{
pw.print("false" + "," + "Do not click refresh");
return;
}
*/
String title = request.getParameter("title");
DesignationDAO designationDAO = new DesignationDAO();
DesignationDTO designationDTO = new DesignationDTO();
designationDTO.setTitle(title);
designationDAO.add(designationDTO);
pw.print("true");
pw.flush();	//to remove extra \n if attached by default
}catch(DAOException daoException)
{
pw.print("false" + "," + daoException.getMessage());
}
catch(Exception e)
{
try
{
response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
}catch(Exception ee)
{
}
}
}//end doPost
}