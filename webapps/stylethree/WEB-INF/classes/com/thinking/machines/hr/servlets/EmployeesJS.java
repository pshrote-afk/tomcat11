package com.thinking.machines.hr.servlets;
import com.thinking.machines.hr.beans.*;
import com.thinking.machines.hr.bl.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.*;
public class EmployeesJS extends HttpServlet	//so that EmployeesJS can get methods like and gives HTTP-specific request/response handling like headers, cookies, status codes.
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
doGet(request,response);
}
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
try
{
PrintWriter pw = response.getWriter();
response.setContentType("text/javascript");
ServletContext servletContext = this.getServletContext();
File file = new File(servletContext.getRealPath("")+File.separator+"WEB-INF"+File.separator+"js"+File.separator+"Employees.js");
RandomAccessFile randomAccessFile = new RandomAccessFile(file,"r");
while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
pw.println(randomAccessFile.readLine());
}
randomAccessFile.close();

List<EmployeeBean> blEmployees = EmployeeBL.getAll();
int k=0;
pw.println("var employee;");
for(EmployeeBean employeeBean:blEmployees)
{
pw.println("employee=new Employee();");
pw.println("employee.employeeId=\""+employeeBean.getEmployeeId()+"\";");
pw.println("employee.name=\""+employeeBean.getName()+"\";");
pw.println("employee.designationCode="+employeeBean.getDesignationCode()+";");
pw.println("employee.title=\""+employeeBean.getTitle()+"\";");
pw.println("employee.dateOfBirth=\""+employeeBean.getDateOfBirth()+"\";");
pw.println("employee.gender=\""+employeeBean.getGender()+"\";");
pw.println("employee.isIndian="+employeeBean.getIsIndian()+";");
pw.println("employee.basicSalary="+employeeBean.getBasicSalary()+";");
pw.println("employee.panNumber=\""+employeeBean.getPanNumber()+"\";");
pw.println("employee.aadharCardNumber=\""+employeeBean.getAadharCardNumber()+"\";");
pw.println("employees["+k+"]=employee;");
k++;
}
}catch(Exception exception)
{
System.out.println(exception.getMessage());
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("ErrorPage.jsp");
try
{
requestDispatcher.forward(request,response);
}catch(Exception e)
{
//do nothing
}

}
}
}


