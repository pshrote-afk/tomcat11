package com.thinking.machines.hr.beans;
public class DesignationBean implements java.io.Serializable,Comparable<DesignationBean>
{
private int code;
private String title;
public DesignationBean()
{
this.code=0;
this.title="";
}
public void setCode(int code)
{
this.code=code;
}
public int getCode()
{
return this.code;
}
public void setTitle(String title)
{
this.title=title;
}
public String getTitle()
{
return this.title;
}
public int compareTo(DesignationBean other)
{
return this.title.compareToIgnoreCase(other.title);
}
public boolean equals(Object object)
{
if(!(object instanceof DesignationBean)) return false;
DesignationBean other = (DesignationBean)object;
return this.code==other.code;
}
public int hashCode()
{
return this.code;
}
}