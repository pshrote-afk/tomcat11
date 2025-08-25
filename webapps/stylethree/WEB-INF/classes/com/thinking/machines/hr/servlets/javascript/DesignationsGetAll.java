package com.thinking.machines.hr.servlets.javascript;
import com.thinking.machines.hr.dl.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.*;
public class DesignationsGetAll extends HttpServlet
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
try
{
PrintWriter pw = response.getWriter();
response.setContentType("text/plain");

DesignationDAO designationDAO = new DesignationDAO();
try
{
List<DesignationDTO> designations = designationDAO.getAll();
int i=0;
for(DesignationDTO designationDTO:designations)
{
i++;
pw.print(designationDTO.getCode()+","+designationDTO.getTitle());
if(i < designations.size()) pw.print(",");
}
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());

//reroute to error page

}


}catch(Exception exception)
{
System.out.println(exception.getMessage());
}
}//end of doGet
}//end of class