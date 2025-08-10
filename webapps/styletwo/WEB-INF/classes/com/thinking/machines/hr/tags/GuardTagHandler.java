package com.thinking.machines.hr.tags;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.tagext.*;
import jakarta.servlet.jsp.*;
public class GuardTagHandler extends TagSupport
{
public GuardTagHandler()
{
reset();
}
private void reset()
{
//do nothing
}
public int doStartTag()
{
// String username = (String)pageContext.getAttribute("username",PageContext.SESSION_SCOPE);
HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
HttpSession session = request.getSession();
if(session.getAttribute("username")==null)
{
return super.EVAL_BODY_INCLUDE;
}
return super.SKIP_BODY;
}
public int doEndTag()
{
reset();
return super.EVAL_PAGE;
}
       }