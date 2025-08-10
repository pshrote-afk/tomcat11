package com.thinking.machines.hr.tags;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.jsp.tagext.*;
import jakarta.servlet.jsp.*;
import java.util.*;	//for List
import java.lang.reflect.*;
public class EntityListTagHandler extends BodyTagSupport
{
private String populatorClass;
private String populatorMethod;
private String name;	//store in request scope with this name
private List list1;
private int index;
Class genericClassInList;
public void setPopulatorClass(java.lang.String populatorClass)
{
this.populatorClass=populatorClass;
}
public java.lang.String getPopulatorClass()
{
return this.populatorClass;
}
public void setPopulatorMethod(java.lang.String populatorMethod)
{
this.populatorMethod=populatorMethod;
}
public java.lang.String getPopulatorMethod()
{
return this.populatorMethod;
}
public void setName(java.lang.String name)
{
this.name=name;
}
public java.lang.String getName()
{
return this.name;
}
public EntityListTagHandler()
{
reset();
}
private void reset()
{
this.populatorClass="";
this.populatorMethod="";
this.name="";
list1=null;
index=0;
genericClassInList = Object.class;
}

public int doStartTag()
{
try
{
Class c = Class.forName(populatorClass);
Object obj = c.newInstance();
Class parameters[] = new Class[0];
Method method = c.getMethod(populatorMethod,parameters);
this.list1 = (List)method.invoke(obj);

if(list1==null) return super.SKIP_BODY;
if(list1.size()==0) return super.SKIP_BODY;
Object bean = list1.get(index);
pageContext.setAttribute(this.name,bean,PageContext.REQUEST_SCOPE);
pageContext.setAttribute("serialNumber",new Integer(index+1),PageContext.REQUEST_SCOPE);
index++;
}catch(Throwable t)
{
//some logging act should be done later on (in next styles)
return super.SKIP_BODY;
}
return super.EVAL_BODY_INCLUDE;
}

public int doAfterBody()
{
if(index == list1.size()) return super.SKIP_BODY;
Object bean = list1.get(index);
pageContext.setAttribute(this.name,bean,PageContext.REQUEST_SCOPE);
pageContext.setAttribute("serialNumber",new Integer(index+1),PageContext.REQUEST_SCOPE);
index++;
return super.EVAL_BODY_AGAIN;
}

public int doEndTag()
{
reset();
return super.EVAL_PAGE;
}
}