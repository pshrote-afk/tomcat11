package com.thinking.machines.hr.servlets;
import java.util.*;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import com.thinking.machines.hr.dl.*;
public class ServletOne extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
try
{
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
}catch(Exception e)
{
//do nothing
}
}
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
try
{
PrintWriter pw = response.getWriter();
response.setContentType("text/plain");
DesignationDAO designationDAO = new DesignationDAO();
List<DesignationDTO> designations = designationDAO.getAll();
int i = 0;
for(DesignationDTO designationDTO:designations)
{
i++;
pw.print(designationDTO.getCode()+", "+designationDTO.getTitle());
if(i < designations.size()) pw.print(", ");
}
}catch(Exception e)
{
try
{
response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
}catch(Exception ee)
{
}
}
}//end doGet
}//end class