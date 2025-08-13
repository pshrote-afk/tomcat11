package com.thinking.machines.hr.bl;
import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.beans.*;
import java.util.*;
import java.math.*;	//for BigDecimal
import java.text.*;	//for SimpleDateFormat
public class EmployeeBL
{
public static List<EmployeeBean> list2 = null;
public static List<EmployeeBean> getAll()
{
list2 = new ArrayList<>();
try
{
EmployeeDAO employeeDAO = new EmployeeDAO();
List<EmployeeDTO> dlEmployees = employeeDAO.getAll();

Iterator<EmployeeDTO> iterator = dlEmployees.iterator();
EmployeeDTO employeeDTO;
EmployeeBean employeeBean;
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
while(iterator.hasNext())
{
employeeDTO = iterator.next();
employeeBean = new EmployeeBean();
employeeBean.setEmployeeId(employeeDTO.getEmployeeId());
employeeBean.setName(employeeDTO.getName());
employeeBean.setDesignationCode(employeeDTO.getDesignationCode());
employeeBean.setTitle(employeeDTO.getTitle());
employeeBean.setDateOfBirth(simpleDateFormat.format(employeeDTO.getDateOfBirth()));
employeeBean.setGender(employeeDTO.getGender());
employeeBean.setIsIndian(employeeDTO.getIsIndian());
employeeBean.setBasicSalary(employeeDTO.getBasicSalary().toPlainString());
employeeBean.setPANNumber(employeeDTO.getPANNumber());
employeeBean.setAadharCardNumber(employeeDTO.getAadharCardNumber());
list2.add(employeeBean);
}
}catch(DAOException daoException)
{
System.out.println(daoException);
}
return list2;
}
}