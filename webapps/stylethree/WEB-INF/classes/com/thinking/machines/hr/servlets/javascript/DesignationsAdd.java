package com.thinking.machines.hr.servlets.javascript;
import com.thinking.machines.hr.dl.*;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import com.google.gson.*;
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
response.setContentType("application/json");
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

Gson gson = new Gson();

JsonObject jsonObject = new JsonObject();
jsonObject.addProperty("success",true);

String jsonString = gson.toJson(jsonObject);
pw.print(jsonString);
pw.flush();	
}catch(DAOException daoException)
{
Gson gson = new Gson();

JsonObject jsonObject = new JsonObject();
jsonObject.addProperty("success",false);
jsonObject.addProperty("errorMessage",daoException.getMessage());

String jsonString = gson.toJson(jsonObject);
pw.print(jsonString);
pw.flush();
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