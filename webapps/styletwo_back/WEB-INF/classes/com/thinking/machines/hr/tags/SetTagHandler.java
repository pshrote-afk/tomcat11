package com.thinking.machines.hr.tags;
import jakarta.servlet.jsp.*;	//for PageContext
import jakarta.servlet.jsp.tagext.*;	//for TagSupport

public class SetTagHandler extends TagSupport
{
private int module;
private int HOME;
private int DESIGNATION;
private int EMPLOYEE;

public SetTagHandler()
{
reset();
}
private void reset()
{
this.module=0;
this.HOME=-1;
this.DESIGNATION=-2;
this.EMPLOYEE=-3;
}
public int doStartTag()
{
return super.EVAL_BODY_INCLUDE;
}
public int doEndTag()
{
reset();
return super.EVAL_PAGE;
}

public void setModule(int module)
{
this.module=module;
pageContext.setAttribute("module",new Integer(module),PageContext.REQUEST_SCOPE);
}
public int getModule()
{
return this.module;
}
public void setHOME(int HOME)
{
this.HOME=HOME;
pageContext.setAttribute("HOME",new Integer(HOME),PageContext.REQUEST_SCOPE);
}
public int getHOME()
{
return this.HOME;
}
public void setDESIGNATION(int DESIGNATION)
{
this.DESIGNATION=DESIGNATION;
pageContext.setAttribute("DESIGNATION",new Integer(DESIGNATION),PageContext.REQUEST_SCOPE);
}
public int getDESIGNATION()
{
return this.DESIGNATION;
}
public void setEMPLOYEE(int EMPLOYEE)
{
this.EMPLOYEE=EMPLOYEE;
pageContext.setAttribute("EMPLOYEE",new Integer(EMPLOYEE),PageContext.REQUEST_SCOPE);
}
public int getEMPLOYEE()
{
return this.EMPLOYEE;
}

}