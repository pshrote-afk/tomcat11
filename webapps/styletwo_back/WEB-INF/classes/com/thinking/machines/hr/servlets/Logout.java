package com.thinking.machines.hr.servlets;
import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.beans.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
public class Logout extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
HttpSession httpSession = request.getSession();
httpSession.removeAttribute("username");
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
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
doGet(request,response);
}
}