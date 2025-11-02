package com.thinking.machines.hr.servlets;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;
import com.google.gson.*;
import java.io.*;	//for PrintWriter
import java.text.*;	//for SimpleDateFormat
public class AddEvent extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
try
{
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
}catch(Exception exception)
{
//do nothing
}
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
PrintWriter pw = null;
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
try
{
pw = response.getWriter();
String title = request.getParameter("title");
java.util.Date utilDate = simpleDateFormat.parse(request.getParameter("datepicker"));
java.sql.Date date = new java.sql.Date(utilDate.getTime());
java.sql.Time  startTime = java.sql.Time.valueOf(request.getParameter("startTime") + ":00");
java.sql.Time  endTime = java.sql.Time.valueOf(request.getParameter("endTime") + ":00");
String venue = request.getParameter("venue");
String description = request.getParameter("description");
boolean outsiders= Boolean.parseBoolean(request.getParameter("outsiders"));
int committeeId = Integer.parseInt(request.getParameter("committee"));

Connection connection = DAOConnection.getConnection();
PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO events (title,date,start_time,end_time,venue,description,open_for_outsiders,committee_id) VALUES (?,?,?,?,?,?,?,?)");
preparedStatement.setString(1,title);
preparedStatement.setDate(2,date);
preparedStatement.setTime(3,startTime);
preparedStatement.setTime(4,endTime);
preparedStatement.setString(5,venue);
preparedStatement.setString(6,description);
preparedStatement.setBoolean(7,outsiders);
preparedStatement.setInt(8,committeeId);
preparedStatement.executeUpdate();

Gson gson = new Gson();
JsonObject jsonObject = new JsonObject();
jsonObject.addProperty("message","Event successfully added");
String jsonString = gson.toJson(jsonObject);
pw.print(jsonString);
pw.flush();

response.setContentType("application/json");
connection.close();
preparedStatement.close();
}catch(Exception exception)
{
System.out.println(exception);
Gson gson = new Gson();
JsonObject jsonObject = new JsonObject();
jsonObject.addProperty("message","Unable to add event");
String jsonString = gson.toJson(jsonObject);
pw.print(jsonString);
pw.flush();
}
}
}//end of class