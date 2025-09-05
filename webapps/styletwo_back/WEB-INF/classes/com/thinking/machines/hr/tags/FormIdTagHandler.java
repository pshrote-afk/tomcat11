package com.thinking.machines.hr.tags;
import jakarta.servlet.jsp.tagext.*;
import java.io.*;	//for IOException
import java.util.*;
import jakarta.servlet.jsp.*;	//for JspWriter
import jakarta.servlet.*;
import jakarta.servlet.http.*;
public class FormIdTagHandler extends TagSupport
{
public FormIdTagHandler()
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
HttpSession httpSession = request.getSession(true);
String formId = UUID.randomUUID().toString();
httpSession.setAttribute(formId,formId);
JspWriter jw = pageContext.getOut();
try
{
jw.println("<input type='hidden' id='formId' name='formId' value='"+formId+"'>");
}catch(IOException ioe)
{
//do nothing
}
return super.SKIP_BODY;
}
public int doEndTag()
{
return super.EVAL_PAGE;
}

}