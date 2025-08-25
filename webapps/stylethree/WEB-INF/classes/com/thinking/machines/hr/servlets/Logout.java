package com.thinking.machines.hr.servlets;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
public class Logout extends HttpServlet
{
public void doGet(HttpServletRequest request, HttpServletResponse response)
{
doPost(request,response);
}

public void doPost(HttpServletRequest request, HttpServletResponse response)
{
HttpSession session = request.getSession();
// session.removeAttribute("username");
session.invalidate();
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("LoginForm.jsp");
try
{
requestDispatcher.forward(request,response);
}
catch(Exception exception)
{
// do nothing
}
}

}