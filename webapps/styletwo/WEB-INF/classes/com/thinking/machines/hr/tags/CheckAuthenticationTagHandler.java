package com.thinking.machines.hr.tags;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.tagext.*;
import jakarta.servlet.jsp.*;
public class CheckAuthenticationTagHandler extends TagSupport
{
public int doStartTag()
{
HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
HttpSession session = request.getSession();
if(session.getAttribute("username")==null)
{
HttpServletResponse response = (HttpServletResponse)pageContext.getResponse();
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("/LoginForm.jsp");
	try
	{
	requestDispatcher.forward(request,response);
	return super.SKIP_BODY;
	}catch(Exception e)
	{
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