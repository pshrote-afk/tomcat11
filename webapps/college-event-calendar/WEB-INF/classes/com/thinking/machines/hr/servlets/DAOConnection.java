package com.thinking.machines.hr.servlets;
import java.sql.*;
public class DAOConnection
{
private static Connection connection;
private DAOConnection()
{
}
public static Connection getConnection() throws DAOException
{
try
{
Class.forName("com.mysql.cj.jdbc.Driver");
connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/college_event_calendar","tmdbuser","tmdbuser");
return connection;
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());
}
}
}

//Connection connection = DAOConnection.getConnection();