package com.thinking.machines.hr.servlets;
import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.beans.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
public class Login extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
AdministratorBean administratorBean = (AdministratorBean)request.getAttribute("administratorBean");
String username = administratorBean.getUsername();
String password = administratorBean.getPassword();

AdministratorDAO administratorDAO = new AdministratorDAO();
AdministratorDTO administratorDTO;
try
{
administratorDTO = administratorDAO.getByUsername(username);

if(!(password.equals(administratorDTO.getPassword())))
{
ErrorBean errorBean = new ErrorBean();
errorBean.setError("Invalid username or password");
request.setAttribute("errorBean",errorBean);
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("LoginForm.jsp");
	try
	{
	requestDispatcher.forward(request,response);
	}catch(Exception e)
	{
	//do nothing
	}
}
//control reaches here means username and password are correct and valid
HttpSession httpSession = request.getSession();
httpSession.setAttribute("username",username);
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("index.jsp");
	try
	{
	requestDispatcher.forward(request,response);
	}catch(Exception e)
	{
	//do nothing
	}

}catch(DAOException daoException)
{
ErrorBean errorBean = new ErrorBean();
errorBean.setError(daoException.getMessage());
request.setAttribute("errorBean",errorBean);
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("LoginForm.jsp");
	try
	{
	requestDispatcher.forward(request,response);
	}catch(Exception e)
	{
	//do nothing
	}
}
} //end of doPost

public void doGet(HttpServletRequest request,HttpServletResponse response)
{
doPost(request,response);
}

}