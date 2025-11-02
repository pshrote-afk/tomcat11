package com.thinking.machines.hr.servlets;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;
import com.google.gson.*;
import java.io.*;	//for PrintWriter
import java.text.*;	//for SimpleDateFormat
import java.util.*;
public class GetEvents extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
try
{
response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
}catch(Exception exception)
{
//do nothing
}
}
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
PrintWriter pw = null;
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
try
{
pw = response.getWriter();
int month = Integer.parseInt(request.getParameter("month"));
int year = Integer.parseInt(request.getParameter("year"));
Connection connection = DAOConnection.getConnection();
PreparedStatement preparedStatement = connection.prepareStatement("SELECT events.*,committees.committee_name FROM events INNER JOIN committees ON events.committee_id=committees.committee_id WHERE MONTH(events.date)=? AND YEAR(events.date)=?");
preparedStatement.setInt(1,month);
preparedStatement.setInt(2,year);
ResultSet resultSet;
resultSet = preparedStatement.executeQuery();

String title;
java.sql.Date date;
java.sql.Time startTime;
java.sql.Time endTime;
String venue;
String description;
boolean outsiders;
String committeeName;
Map<String,Object> details;
int dayOfMonth;
JsonObject event;
JsonArray data = new JsonArray();	//NTS
Gson gson = new Gson();
while(resultSet.next())
{
title = resultSet.getString("title");
date = resultSet.getDate("date");
startTime = resultSet.getTime("start_time");
endTime = resultSet.getTime("end_time");
venue = resultSet.getString("venue");
description = resultSet.getString("description");
outsiders = resultSet.getBoolean("open_for_outsiders");
committeeName = resultSet.getString("committee_name");

details = new LinkedHashMap<>();
details.put("title", title);
details.put("date", date);
details.put("startTime", startTime);
details.put("endTime", endTime);
details.put("venue", venue);
details.put("description", description);

details.put("outsiders", (outsiders)?"Yes":"No");
details.put("committeeName", committeeName);

dayOfMonth = date.toLocalDate().getDayOfMonth();

event = new JsonObject();
event.addProperty("dayOfMonth",dayOfMonth);
event.add("details",gson.toJsonTree(details));
data.add(event);
}
String jsonString = gson.toJson(data);
pw.print(jsonString);
pw.flush();
}catch(Exception exception)
{
System.out.println(exception);
Gson gson = new Gson();
JsonObject jsonObject = new JsonObject();
jsonObject.addProperty("message","Unable to get events");
String jsonString = gson.toJson(jsonObject);
pw.print(jsonString);
pw.flush();
}
}
}//end of class