package com.thinking.machines.hr.tags;
import jakarta.servlet.jsp.*; 	//for PageContext
import jakarta.servlet.jsp.tagext.*;	//for BodyTagSupport
import java.util.*; //for List
import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.beans.*;
public class DesignationsTagHandler extends BodyTagSupport //BodyTagSupport has method doAfterBody()
{
private List<DesignationBean> designations;
private int index;

public DesignationsTagHandler()
{
this.designations=null;
this.index=0;
}

private void reset()
{
index=0;
if(designations!=null)
{
designations.clear();
}
designations=null;
}

public int doStartTag()
{
List<DesignationDTO> dlDesignations;
try
{
dlDesignations = (new DesignationDAO()).getAll();
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
return super.SKIP_BODY;
}
DesignationBean designationBean;
designations = new ArrayList<>();
for(DesignationDTO designationDTO:dlDesignations)
{
designationBean = new DesignationBean();
designationBean.setCode(designationDTO.getCode());
designationBean.setTitle(designationDTO.getTitle());
designations.add(designationBean);
}
index=0;
designationBean = new DesignationBean();
designationBean = designations.get(index);
pageContext.setAttribute("designationBean",designationBean,PageContext.REQUEST_SCOPE);
pageContext.setAttribute("serialNumber",new Integer(index+1),PageContext.REQUEST_SCOPE);
return super.EVAL_BODY_INCLUDE;
}

public int doAfterBody()
{
index++;
if(index==designations.size()) return super.SKIP_BODY;
DesignationBean designationBean = this.designations.get(index);
pageContext.setAttribute("designationBean",designationBean,PageContext.REQUEST_SCOPE);
pageContext.setAttribute("serialNumber",new Integer(index+1),PageContext.REQUEST_SCOPE);
return super.EVAL_BODY_AGAIN;
}

public int doEndTag()
{
reset();
return super.EVAL_PAGE;
}


}