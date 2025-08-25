package com.thinking.machines.hr.servlets;
import  com.thinking.machines.hr.beans.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
public class NotifyFormResubmission extends HttpServlet
{
public void doPost(HttpServletRequest request, HttpServletResponse response)
{
doGet(request,response);
}
public void doGet(HttpServletRequest request, HttpServletResponse response)
{
//check Authentication
HttpSession httpSession = request.getSession();
if(httpSession.getAttribute("username")==null)
{
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("/LoginForm.jsp");
try
{
requestDispatcher.forward(request,response);
return;
}catch(Exception exception)
{
//do nothing
}
}
MessageBean messageBean = new MessageBean();
messageBean.setHeading("Warning");
messageBean.setMessage("Do not refresh page while submitting form");
messageBean.setGenerateButtons(true);
messageBean.setButtonOneText("OK");
messageBean.setButtonOneAction("index.jsp");
request.setAttribute("messageBean",messageBean);
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("/Notification.jsp");
try
{
requestDispatcher.forward(request,response);
}catch(Exception exception)
{
//do nothing
}

}
}