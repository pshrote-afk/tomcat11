package com.thinking.machines.hr.dl;
import java.sql.*;
public class AdministratorDAO
{
public AdministratorDAO()
{
}

public AdministratorDTO getByUsername(String username) throws DAOException
{
try
{
Connection connection = DAOConnection.getConnection();
PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM administrator WHERE username=?");
preparedStatement.setString(1,username);
ResultSet resultSet = preparedStatement.executeQuery();
if(resultSet.next()==false)
{
resultSet.close();
preparedStatement.close();
connection.close();
throw new DAOException("Invalid username or password");
}
AdministratorDTO administratorDTO = new AdministratorDTO();
administratorDTO.setUsername(resultSet.getString("username").trim());
administratorDTO.setPassword(resultSet.getString("password").trim());
resultSet.close();
preparedStatement.close();
connection.close();
return administratorDTO;
}catch(Exception exception)
{
throw new DAOException(exception.getMessage());	//for SQLException
}
}

}