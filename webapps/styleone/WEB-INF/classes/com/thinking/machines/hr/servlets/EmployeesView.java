package com.thinking.machines.hr.servlets;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.sql.*;
import java.text.*;	//for SimpleDateFormat class
import java.util.*;
import java.io.*;
import com.thinking.machines.hr.dl.*;
public class EmployeesView extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
doGet(request,response);
}
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
try
{
EmployeeDAO employeeDAO = new EmployeeDAO();
List<EmployeeDTO> employeesList;
employeesList = employeeDAO.getAll();
PrintWriter pw = response.getWriter();
response.setContentType("text/html");
pw.println("<!DOCTYPE HTML>");
pw.println("<html lang = 'en'>");
pw.println("<head>");
pw.println("<title>HR Application</title>");
pw.println("<script>");
pw.println("function Employee() //this is a class in JavaScript");
pw.println("{");
pw.println("this.employeeId=\"\";"); 
pw.println("this.name=\"\";");
pw.println("this.designationCode=0;");
pw.println("this.designation=\"\";");
pw.println("this.dateOfBirth=\"\";");
pw.println("this.gender=\"\";");
pw.println("this.isIndian=true;");
pw.println("this.basicSalary=\"\";");
pw.println("this.panNumber=\"\";");
pw.println("this.aadharCardNumber=\"\";");
pw.println("}");
pw.println("var selectedRow=null;");
pw.println("var employees=[];");
pw.println("var employee;");
int i=0;
SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
for(EmployeeDTO employeeDTO:employeesList)
{
pw.println("employee=new Employee();");
pw.println("employee.employeeId=\""+employeeDTO.getEmployeeId()+"\";");
pw.println("employee.name=\""+employeeDTO.getName()+"\";");
pw.println("employee.designationCode="+employeeDTO.getDesignationCode()+";");
pw.println("employee.designation=\""+employeeDTO.getTitle()+"\";");
pw.println("employee.dateOfBirth=\""+sdf.format(employeeDTO.getDateOfBirth())+"\";");
pw.println("employee.gender=\""+employeeDTO.getGender()+"\";");
pw.println("employee.isIndian="+employeeDTO.getIsIndian()+";");
pw.println("employee.basicSalary="+employeeDTO.getBasicSalary().toPlainString()+";");	// .toString() returns value in scientific notation
pw.println("employee.panNumber=\""+employeeDTO.getPANNumber()+"\";");
pw.println("employee.aadharCardNumber=\""+employeeDTO.getAadharCardNumber()+"\";");
pw.println("employees["+i+"]=employee;");
i++;
}
pw.println("function selectEmployee(row,employeeId)");
pw.println("{");
pw.println("if(row==selectedRow) return;");
pw.println("if(selectedRow==null)");
pw.println("{");
pw.println("row.style.color='white';");
pw.println("row.style.background='gray';");
pw.println("}");
pw.println("else");
pw.println("{");
pw.println("selectedRow.style.color='black';");
pw.println("selectedRow.style.background='white';");
pw.println("row.style.color='white';");
pw.println("row.style.background='gray';");
pw.println("}");
pw.println("selectedRow = row;");
pw.println("var i;");
pw.println("for(i=0;i<employees.length;i++)");
pw.println("{");
pw.println("if(employees[i].employeeId==employeeId)");
pw.println("{");
pw.println("break;");
pw.println("}");
pw.println("}");
pw.println("var emp=employees[i];");
pw.println("document.getElementById('detailPanel_employeeId').innerHTML=emp.employeeId;");
pw.println("document.getElementById('detailPanel_name').innerHTML=emp.name;");
pw.println("document.getElementById('detailPanel_designation').innerHTML=emp.designation;");
pw.println("document.getElementById('detailPanel_dateOfBirth').innerHTML=emp.dateOfBirth;");
pw.println("document.getElementById('detailPanel_gender').innerHTML=emp.gender;");
pw.println("document.getElementById('detailPanel_isIndian').innerHTML=emp.isIndian;");
pw.println("document.getElementById('detailPanel_basicSalary').innerHTML=emp.basicSalary;");
pw.println("document.getElementById('detailPanel_panNumber').innerHTML=emp.panNumber;");
pw.println("document.getElementById('detailPanel_aadharCardNumber').innerHTML=emp.aadharCardNumber;");
pw.println("");
pw.println("}");
pw.println("</script>");
pw.println("</head>");
pw.println("<body>");
pw.println("<!-- Main container starts here -->");
pw.println("<div style='width:90hw;height:auto;border:1px solid black'>");
pw.println("<!-- Header container starts here -->");
pw.println("<div style='margin:5px;width:90hw;height:auto;border:1px solid black'>");
pw.println("<a href='/styleone/index.html'><img src='/styleone/images/logo.png' style='float:left;width:35px;height:auto;padding:5px'></a><div style='margin-top:9px;margin-bottom:9px;font-size:20pt'>Thinking Machines</div>");
pw.println("</div><!-- Header container ends here -->");
pw.println("<!-- content-section starts here -->");
pw.println("<div style='width:90hw;height:70vh;margin:5px;border:1px solid white'>");
pw.println("<!-- left panel starts here -->");
pw.println("<div style='height:65vh;margin:5px;float:left;padding:5px;border:1px solid black'>");
pw.println("<a href='/styleone/designationsView'>Designations</a><br>");
pw.println("Employees<br><br>");
pw.println("<a href='/styleone/index.html'>Home</a>");
pw.println("</div><!-- left panel ends here -->");
pw.println("<!-- right panel starts here -->");
pw.println("<div style='height:65vh;margin-left:105px;margin-right:5px;margin-bottom:5px;margin-top:5px;padding:5px;border:1px solid black'>");
pw.println("<div style='height:35vh;margin-left:5px;margin-right:5px;margin-bottom:5px;margin-top:5px;padding:5px;border:1px solid black;overflow:auto'> <!-- Employees div starts here -->");
pw.println("<b>Employees</b>");
pw.println("<table border='5' style=\"width:100%\">");
pw.println("<thead>");
pw.println("<tr>");
pw.println("<th colspan='6' style='text-align:right;padding: 5px'>");
pw.println("<a href='/styleone/getEmployeeAddForm'>Add Employee</a> <!--doubt: Link to where-->");
pw.println("</th>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<th style='width:10%;text-align:center'>S.No.</th>");
pw.println("<th style='width:10%;text-align:center'>Id</th>");
pw.println("<th style='width:35%;text-align:center'>Name</th>");
pw.println("<th style='width:25%;text-align:center'>Designation</th>");
pw.println("<th style='width:10%;text-align:center'>Edit</th>");
pw.println("<th style='width:10%;text-align:center'>Delete</th>");
pw.println("</tr>");
pw.println("</thead>");
pw.println("<tbody>");
pw.println("<!-- Java loop section would generate rows here -->");
int sno=0;
for(EmployeeDTO employeeDTO:employeesList)
{
sno++;
pw.println("<tr style='cursor:pointer' onclick='selectEmployee(this,\""+employeeDTO.getEmployeeId()+"\")'>");
pw.println("<td style='text-align:right'>"+sno+".</td>");
pw.println("<td>"+employeeDTO.getEmployeeId()+"</td>");
pw.println("<td>"+employeeDTO.getName()+"</td>");
pw.println("<td>"+employeeDTO.getTitle()+"</td>");
pw.println("<td style='text-align:center'><a href='/styleone/editEmployee?employeeId="+employeeDTO.getEmployeeId()+"'>Edit</a></td>"); 
pw.println("<td style='text-align:center'><a href='/styleone/confirmDeleteEmployee?employeeId="+employeeDTO.getEmployeeId()+"'>Delete</a></td>");
pw.println("</tr>");
}
pw.println("</tbody>");
pw.println("</table>");
pw.println("</div> <!-- Employees div ends here -->");
pw.println("<div style='height:24vh;margin-left:5px;margin-right:5px;margin-bottom:5px;margin-top:5px;padding:5px;border:1px solid black'> <!-- Employee details div starts here -->");
pw.println("<label style='background:gray;color:white;padding-left:5px;padding-right:5px;'>Details</label><br><br>");
pw.println("<table border='1' style='width:99%;'>");
pw.println("<tr>");
pw.println("<td style='width:33%'>Employee Id: <span id='detailPanel_employeeId'></span></td>");
pw.println("<td style='width:33%'>Name: <span id='detailPanel_name'></span></td>");
pw.println("<td style='width:33%'>Designation: <span id='detailPanel_designation'></span></td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Date of Birth: <span id='detailPanel_dateOfBirth'></span></td>");
pw.println("<td>Gender: <span id='detailPanel_gender'></span></td>");
pw.println("<td>Is Indian: <span id='detailPanel_isIndian'></span></td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Basic Salary: <span id='detailPanel_basicSalary'></span></td>");
pw.println("<td>PAN number: <span id='detailPanel_panNumber'></span></td>");
pw.println("<td>Aadhar Card Number: <span id='detailPanel_aadharCardNumber'></span></td>");
pw.println("</tr>");
pw.println("</table>");
pw.println("</div> <!-- Employee details div ends here -->");
pw.println("</div><!-- right panel ends here -->");
pw.println("</div><!-- content-section ends here -->");
pw.println("<!-- footer starts here -->");
pw.println("<div style='width:90hw;height:auto;margin:5px;text-align:center;border:1px solid footer'>");
pw.println("&copy; Thinking Machines 2025");
pw.println("</div> <!-- footer ends here -->");
pw.println("</div><!-- Main container ends here -->");
pw.println("</body>");
pw.println("</html>");
}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());
}
catch(Exception exception)
{
System.out.println(exception.getMessage());
}	

}
}