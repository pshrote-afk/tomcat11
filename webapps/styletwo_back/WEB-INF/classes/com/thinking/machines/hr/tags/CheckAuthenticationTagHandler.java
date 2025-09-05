package com.thinking.machines.hr.tags;
import jakarta.servlet.jsp.tagext.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class CheckAuthenticationTagHandler extends TagSupport
{
public CheckAuthenticationTagHandler()
{
reset();
}
private void reset()
{
//do nothing
}
public int doStartTag()
{
HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
HttpServletResponse response = (HttpServletResponse)pageContext.getResponse();
HttpSession httpSession = request.getSession();
if(httpSession.getAttribute("username")==null)
{
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("/styletwo/Designations.jsp");
try
{
requestDispatcher.forward(request,response);
System.out.println("Incorrect place for control to reach");
}catch(Exception e)
{
System.out.println(e.getMessage());	//remove after testing
//do nothing
}
}
return super.SKIP_BODY;
}
public int doEndTag()
{
return super.EVAL_PAGE;
}
}
