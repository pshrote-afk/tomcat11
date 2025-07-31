package com.thinking.machines.hr.servlets;
import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.beans.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
public class AddDesignation extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
String formId = request.getParameter("formId");
HttpSession httpSession = request.getSession();
String sessionFormId = (String)httpSession.getAttribute("formId");
if(formId.equalsIgnoreCase(sessionFormId)==false)
{
MessageBean messageBean = new MessageBean();
messageBean.setHeading("Warning");
messageBean.setMessage("Do not refresh page while submitting form");
messageBean.setGenerateButtons(true);
messageBean.setButtonOneText("OK");
messageBean.setButtonOneAction("Designations.jsp");
request.setAttribute("messageBean",messageBean);
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("Notification.jsp");
try
{
requestDispatcher.forward(request,response);
}catch(Exception exception)
{
// if user hits refresh - exception thrown: Cannot forward after response has been committed. Hence no need to "return;"
//return; // no need. Since request is forwarded, and forwarded .jsp sends s response. 
}

}
else
{
//else it is the same form - remove formId from session scope - and continue processing
httpSession.removeAttribute("formId");
}

try
{
//extract data from bean
DesignationBean designationBean;
designationBean = (DesignationBean)request.getAttribute("designationBean");
String title = designationBean.getTitle();
DesignationDTO designationDTO = new DesignationDTO();
designationDTO.setTitle(title);
DesignationDAO designationDAO = new DesignationDAO();
try
{
designationDAO.add(designationDTO);
designationBean.setCode(designationDTO.getCode());
MessageBean messageBean = new MessageBean();
messageBean.setHeading("Designation (Add Module)");
messageBean.setMessage("Designation added. Add more?");
messageBean.setGenerateButtons(true);
messageBean.setGenerateTwoButtons(true);
messageBean.setButtonOneText("Yes");
messageBean.setButtonOneAction("DesignationAddForm.jsp");
messageBean.setButtonTwoText("No");
messageBean.setButtonTwoAction("Designations.jsp");

request.setAttribute("messageBean",messageBean);
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("/Notification.jsp");
requestDispatcher.forward(request,response); 	//may throw ServletException
}catch(DAOException daoException)
{
ErrorBean errorBean = new ErrorBean();
errorBean.setError(daoException.getMessage());

request.setAttribute("errorBean",errorBean);
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("/DesignationAddForm.jsp");
requestDispatcher.forward(request,response);		//may throw ServletException
}
}catch(Exception exception)
{
System.out.println(exception.getMessage()); //remove after testing
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("ErrorPage.jsp");
try
{
requestDispatcher.forward(request,response);
}catch(Exception e)
{
//do nothing
}
}
}
}