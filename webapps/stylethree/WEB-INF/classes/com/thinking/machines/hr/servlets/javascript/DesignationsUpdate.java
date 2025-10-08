package com.thinking.machines.hr.servlets.javascript;
import com.thinking.machines.hr.dl.*;
import jakarta.servlet.http.*;
import jakarta.servlet.*;
import java.io.*;
import com.google.gson.*;
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
response.setContentType("application/json");
try
{
Gson gson = new Gson();
pw = response.getWriter();

BufferedReader br = request.getReader();
StringBuffer sb = new StringBuffer();
String x;
while(true)
{
x = br.readLine();
if(x==null) break;
sb.append(x);
}
String rawData = sb.toString();

DesignationDAO designationDAO = new DesignationDAO();
DesignationDTO designationDTO;
designationDTO = gson.fromJson(rawData,DesignationDTO.class);
designationDAO.update(designationDTO);

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