package com.thinking.machines.hr.servlets;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import com.google.gson.*;
import java.io.*;	//for PrintWriter
import java.time.*;
import java.util.*;
public class GetMonthGrid extends HttpServlet
{
class TMDatePicker
{
private int [][] getDays(int month,int year)
{
//Date firstDayOfMonth = new Date(year-1980,month-1,1);
Calendar firstDayOfMonthCalendar = Calendar.getInstance();
firstDayOfMonthCalendar.clear();
firstDayOfMonthCalendar.set(year,month-1,1);

int dayOfWeekOfFirstDayOfMonth = firstDayOfMonthCalendar.get(Calendar.DAY_OF_WEEK);
YearMonth yearMonth = YearMonth.of(year,month);

int numberOfDaysInMonth = yearMonth.lengthOfMonth();
Date lastDayOfMonth = new Date(year-1900,month-1,numberOfDaysInMonth);
Calendar lastDayOfMonthCalendar = Calendar.getInstance();
lastDayOfMonthCalendar.setTime(lastDayOfMonth);

int dayOfWeekOfLastDayOfMonth = lastDayOfMonthCalendar.get(Calendar.DAY_OF_WEEK);
int weekNumber = lastDayOfMonthCalendar.get(Calendar.WEEK_OF_MONTH);

int days[][] = new int[weekNumber][7];

int c = dayOfWeekOfFirstDayOfMonth - 1;
int r = 0;

for(int i=1;i<=numberOfDaysInMonth;i++)
{
days[r][c]=i;
c++;
if(c==7)
{
c=0;
r++;
}
}
return days;
}


}//end of TMDatePicker

public void doGet(HttpServletRequest request,HttpServletResponse response)
{
PrintWriter pw = null;
response.setContentType("application/json");
try
{
pw = response.getWriter();
int month = Integer.parseInt(request.getParameter("month"));
int year = Integer.parseInt(request.getParameter("year"));

TMDatePicker tmdp = new TMDatePicker();
int [][] days = tmdp.getDays(month,year);

/*
//below is for testing
for(int r=0;r<days.length;r++)
{
for(int c=0;c<days[r].length;c++)
{
System.out.printf("%2d ",days[r][c]);
}
System.out.println("\n");
}
//above is for testing
*/

Gson gson = new Gson();
String jsonString = gson.toJson(days);
pw.print(jsonString);
pw.flush();
}catch(Exception exception)
{
System.out.println(exception);
}
}
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
}//end of class