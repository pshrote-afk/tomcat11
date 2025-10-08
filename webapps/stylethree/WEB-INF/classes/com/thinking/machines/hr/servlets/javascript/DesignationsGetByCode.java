package com.thinking.machines.hr.servlets.javascript;
import com.thinking.machines.hr.dl.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import com.google.gson.*;
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
response.setContentType("application/json");
DesignationDAO designationDAO = new DesignationDAO();
try
{
DesignationDTO designationDTO = designationDAO.getByCode(code);

Gson gson = new Gson();

JsonObject jsonObject = new JsonObject();
jsonObject.addProperty("success",true);
jsonObject.add("data",gson.toJsonTree(designationDTO));

String jsonString = gson.toJson(jsonObject);
pw.print(jsonString);

}catch(DAOException daoException)
{
Gson gson = new Gson();
JsonObject jsonObject = new JsonObject();
jsonObject.addProperty("success",false);
jsonObject.addProperty("errorMessage",daoException.getMessage());
String jsonString = gson.toJson(jsonObject);
pw.print(jsonString);
return;
}
}catch(Exception exception)
{
Gson gson = new Gson();
JsonObject jsonObject = new JsonObject();
jsonObject.addProperty("success",false);
jsonObject.addProperty("errorMessage",exception.getMessage());
String jsonString = gson.toJson(jsonObject);
pw.print(jsonString);
}
}//end of doGet
}//end of class