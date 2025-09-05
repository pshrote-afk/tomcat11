package com.thinking.machines.hr.dl;
import java.sql.*;
public class AdministratorDAO 
{
public AdministratorDTO getByUsername(String username) throws DAOException
{
if(username==null) throw new DAOException("Enter username");
try
{
Connection connection = DAOConnection.getConnection();
PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM administrator WHERE uname=?");
preparedStatement.setString(1,username);
ResultSet resultSet;
resultSet = preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid username or password");
}
AdministratorDTO administratorDTO;
administratorDTO = new AdministratorDTO();
administratorDTO.setUsername(resultSet.getString("uname").trim());
administratorDTO.setPassword(resultSet.getString("pwd").trim());
resultSet.close();
preparedStatement.close();
connection.close();
return administratorDTO;
}catch(SQLException sqlException)
{
System.out.println(sqlException.getMessage()); //I dont think its right to wrap sqlException in DAOException
}
return null;
}

}