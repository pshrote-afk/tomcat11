package com.thinking.machines.hr.servlets.javascript;
import com.thinking.machines.hr.dl.*;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
public class DesignationsUpdate extends HttpServlet
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
int code = Integer.parseInt(request.getParameter("code"));
String title = request.getParameter("title");
DesignationDAO designationDAO = new DesignationDAO();
DesignationDTO designationDTO = new DesignationDTO();
designationDTO.setCode(code);
designationDTO.setTitle(title);
designationDAO.update(designationDTO);
pw.print("true");
pw.flush();	//to remove extra \n if attached by default
}catch(DAOException daoException)
{
pw.print("false" + "," + daoException.getMessage());
return;
}
catch(Exception e)
{
try
{
System.out.println(e.getMessage());
response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
}catch(Exception ee)
{
}
}
}//end doPost
}