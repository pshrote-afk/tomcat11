package com.thinking.machines.hr.servlets.javascript;
import com.thinking.machines.hr.dl.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
public class DesignationsGetByCode extends HttpServlet
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
PrintWriter pw=null;
try
{
pw = response.getWriter();
int code = Integer.parseInt(request.getParameter("code"));
response.setContentType("text/plain");
DesignationDAO designationDAO = new DesignationDAO();
try
{
DesignationDTO designationDTO = designationDAO.getByCode(code);
pw.print("true" + ",");
pw.print(designationDTO.getCode()+","+designationDTO.getTitle());

}catch(DAOException daoException)
{
pw.print("false" + "," + daoException.getMessage());
return;
}
}catch(Exception exception)
{
pw.print("false" + "," + exception.getMessage());
}
}//end of doGet
}//end of class