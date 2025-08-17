package com.thinking.machines.hr.beans;
import java.math.*;
public class EmployeeBean implements java.io.Serializable,Comparable<EmployeeBean>
{
private String employeeId;
private String name;
private int designationCode;
private String title;
private String dateOfBirth;	//DTO has java.util.Date. Bean will have String for simplicity.
private String gender;
private String isIndian;
private String basicSalary;
private String panNumber;
private String aadharCardNumber;

public EmployeeBean()
{
this.employeeId="";
this.name="";
this.designationCode=0;
this.title="";
this.dateOfBirth="";
this.gender="";
this.isIndian="";
this.basicSalary="";
this.panNumber="";
this.aadharCardNumber="";
}
public boolean isMale()
{
return this.gender.equals("M");
}
public boolean isFemale()
{
return this.gender.equals("F");
}
public boolean isIndian()
{
return this.isIndian.equals("Y");
}
public void setEmployeeId(java.lang.String employeeId)
{
this.employeeId=employeeId;
}
public java.lang.String getEmployeeId()
{
return this.employeeId;
}
public void setName(java.lang.String name)
{
this.name=name;
}
public java.lang.String getName()
{
return this.name;
}
public void setDesignationCode(int designationCode)
{
this.designationCode=designationCode;
}
public int getDesignationCode()
{
return this.designationCode;
}
public void setTitle(java.lang.String title)
{
this.title=title;
}
public java.lang.String getTitle()
{
return this.title;
}
public void setDateOfBirth(String dateOfBirth)
{
this.dateOfBirth=dateOfBirth;
}
public String getDateOfBirth()
{
return this.dateOfBirth;
}
public void setGender(java.lang.String gender)
{
this.gender=gender;
}
public java.lang.String getGender()
{
return this.gender;
}
public void setIsIndian(String isIndian)
{
this.isIndian=isIndian;
}
public String getIsIndian()
{
return this.isIndian;
}
public void setBasicSalary(String basicSalary)
{
this.basicSalary=basicSalary;
}
public String getBasicSalary()
{
return this.basicSalary;
}
public void setPanNumber(java.lang.String panNumber)
{
this.panNumber=panNumber;
}
public java.lang.String getPanNumber()
{
return this.panNumber;
}
public void setAadharCardNumber(java.lang.String aadharCardNumber)
{
this.aadharCardNumber=aadharCardNumber;
}
public java.lang.String getAadharCardNumber()
{
return this.aadharCardNumber;
}

public int compareTo(EmployeeBean other)
{
return this.employeeId.compareToIgnoreCase(other.employeeId);
}
public boolean equals(Object object)
{
if(!(object instanceof EmployeeBean)) return false;
EmployeeBean other = (EmployeeBean)object;
return this.employeeId.equalsIgnoreCase(other.employeeId);
}
public int hashCode()
{
return this.employeeId.hashCode();
}

}