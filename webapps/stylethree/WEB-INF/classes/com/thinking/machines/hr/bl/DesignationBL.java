package com.thinking.machines.hr.bl;
import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.beans.*;
import java.util.*;
public class DesignationBL
{
public List<DesignationBean> blDesignations = new ArrayList<DesignationBean>();

public List<DesignationBean> getAll()
{
try
{
DesignationDAO designationDAO = new DesignationDAO();
List<DesignationDTO> dlDesignations = designationDAO.getAll();
DesignationBean dBean;
for(DesignationDTO designation:dlDesignations)
{
dBean = new DesignationBean();
dBean.setCode(designation.getCode());
dBean.setTitle(designation.getTitle());
this.blDesignations.add(dBean);
}
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
return blDesignations;
}
}