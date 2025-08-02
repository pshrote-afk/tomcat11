package com.thinking.machines.hr.tags;
import jakarta.servlet.jsp.tagext.*;
import java.io.*;	//for IOException
import java.util.*;
import jakarta.servlet.jsp.*;	//for JspWriter
import jakarta.servlet.*;
import jakarta.servlet.http.*;
public class FormResubmittedTagHandler extends TagSupport
{
public FormResubmittedTagHandler()
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
String formId = request.getParameter("formId");
if(formId==null)
{
return super.EVAL_BODY_INCLUDE;
}
HttpSession httpSession = request.getSession();
String formIdInSession = (String)httpSession.getAttribute(formId);
httpSession.removeAttribute(formId);
if(formId.equals(formIdInSession))
{
return super.SKIP_BODY;
}
else
{
return super.EVAL_BODY_INCLUDE;
}
}
public int doEndTag()
{
reset();
return super.EVAL_PAGE;
}

}