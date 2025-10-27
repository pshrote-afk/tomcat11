package com.thinking.machines.hr.servlets;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import com.thinking.machines.hr.dl.*;
import com.google.gson.*;
public class ServletTwo extends HttpServlet
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
int code = Integer.parseInt(request.getParameter("code"));
PrintWriter pw = response.getWriter();
response.setContentType("text/json");
DesignationDAO designationDAO = new DesignationDAO();
	try
	{
	DesignationDTO designationDTO = designationDAO.getByCode(code);
	Gson gson = new Gson();
	String jsonString = gson.toJson(designationDTO);
	pw.print(jsonString);
	pw.flush();
	}catch(DAOException daoException)
	{
	System.out.println(daoException.getMessage());
	pw.print("INVALID");
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