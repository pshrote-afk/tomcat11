package com.thinking.machines.hr.tags;
import jakarta.servlet.jsp.tagext.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
public class FormIdTagHandler extends TagSupport
{
public FormIdTagHandler()
{
}
public int doStartTag()
{
HttpServletRequest request = (HttpServletRequest)pageContext.getRequest();
HttpSession httpSession = request.getSession(true);
String formId = UUID.randomUUID().toString();
httpSession.setAttribute("formId",formId);
return super.SKIP_BODY;
}
public int doEndTag()
{
return super.EVAL_PAGE;
}

}