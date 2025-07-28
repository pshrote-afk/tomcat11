package com.thinking.machines.hr.servlets;
import com.thinking.machines.hr.dl.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
public class EditEmployee extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
doPost(request,response);
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
try
{
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
String employeeId = request.getParameter("employeeId");
// get other info by employeeId
EmployeeDAO employeeDAO = new EmployeeDAO();
EmployeeDTO employeeDTO;
employeeDTO = employeeDAO.getByEmployeeId(employeeId);
employeeId = employeeDTO.getEmployeeId();
String name = employeeDTO.getName();
int designationCode = employeeDTO.getDesignationCode();
String title = employeeDTO.getTitle(); //not needed in this case
java.util.Date dateOfBirth = employeeDTO.getDateOfBirth();
String gender = employeeDTO.getGender();
Boolean isIndian = employeeDTO.getIsIndian();
BigDecimal basicSalary = employeeDTO.getBasicSalary();
String panNumber = employeeDTO.getPANNumber();
String aadharCardNumber = employeeDTO.getAadharCardNumber();

DesignationDAO designationDAO;
designationDAO = new DesignationDAO();
List<DesignationDTO> designations;
designations = designationDAO.getAll();

PrintWriter pw = response.getWriter();
response.setContentType("text/html");

pw.println("<!DOCTYPE HTML>");
pw.println("<html lang = 'en'>");
pw.println("<head>");
pw.println("<title>HR Application</title>");
pw.println("<script>");
pw.println("function validateForm(frm)");
pw.println("{");
pw.println("var isValid=true;");
pw.println("firstInvalidField=null;");
pw.println("");
pw.println("var name = frm.name.value.trim();");
pw.println("var nameErrorSection = document.getElementById('nameErrorSection');");
pw.println("nameErrorSection.innerHTML='';");
pw.println("if(name.length==0)");
pw.println("{");
pw.println("nameErrorSection.innerHTML='Name required';");
pw.println("if(firstInvalidField==null) firstInvalidField=frm.name;");
pw.println("isValid=false;");
pw.println("}");
pw.println("var designationCode = frm.designationCode.value;");
pw.println("var designationCodeErrorSection = document.getElementById('designationCodeErrorSection');");
pw.println("designationCodeErrorSection.innerHTML='';");
pw.println("if(designationCode==(-1))");
pw.println("{");
pw.println("designationCodeErrorSection.innerHTML='Designation required';");
pw.println("if(firstInvalidField==null) firstInvalidField=frm.designationCode;");
pw.println("isValid=false;");
pw.println("}");
pw.println("var dateOfBirth = frm.dateOfBirth.value;");
pw.println("var dateOfBirthErrorSection = document.getElementById('dateOfBirthErrorSection');");
pw.println("dateOfBirthErrorSection.innerHTML='';");
pw.println("if(dateOfBirth.length==0)");
pw.println("{");
pw.println("dateOfBirthErrorSection.innerHTML='Date of birth required';");
pw.println("if(firstInvalidField==null) firstInvalidField=frm.dateOfBirth;");
pw.println("isValid=false;");
pw.println("}");
pw.println("");
pw.println("var genderErrorSection = document.getElementById('genderErrorSection');");
pw.println("genderErrorSection.innerHTML='';");
pw.println("if(frm.gender[0].checked==false && frm.gender[1].checked==false)");
pw.println("{");
pw.println("genderErrorSection.innerHTML='Gender required';");
pw.println("//we don't put focus on radio buttons - we don't want to select any");
pw.println("isValid=false;");
pw.println("}");
pw.println("");
pw.println("var k=0;");
pw.println("var allowedChars='0123456789.';");
pw.println("var basicSalary = frm.basicSalary.value;");
pw.println("var basicSalaryErrorSection = document.getElementById('basicSalaryErrorSection');");
pw.println("basicSalaryErrorSection.innerHTML='';");
pw.println("if(basicSalary.length==0)");
pw.println("{");
pw.println("basicSalaryErrorSection.innerHTML='Basic salary required';");
pw.println("if(firstInvalidField==null) firstInvalidField=frm.basicSalary;");
pw.println("isValid=false; ");
pw.println("}");
pw.println("while(k < basicSalary.length)");
pw.println("{");
pw.println("if(allowedChars.indexOf(basicSalary.charAt(k))==-1)	// if -1 index is returned, it means char not found");
pw.println("{");
pw.println("basicSalaryErrorSection.innerHTML='Invalid basic salary';");
pw.println("if(firstInvalidField==null) firstInvalidField=frm.basicSalary;");
pw.println("isValid=false; ");
pw.println("break;");
pw.println("}");
pw.println("k++;");
pw.println("}");
pw.println("if((basicSalary.indexOf('.')==-1)==false) //if block true, means dot exists");
pw.println("{");
pw.println("var dotIndex = basicSalary.indexOf('.');");
pw.println("//eg: 1234.567");
pw.println("var afterDot = (basicSalary.length) - (dotIndex + 1);");
pw.println("if(afterDot > 2)");
pw.println("{");
pw.println("basicSalaryErrorSection.innerHTML='Only 2 digits after decimal allowed';");
pw.println("if(firstInvalidField==null) firstInvalidField=frm.basicSalary;");
pw.println("isValid=false; ");
pw.println("}");
pw.println("}");
pw.println("");
pw.println("var panNumber = frm.panNumber.value.trim();");
pw.println("var panNumberErrorSection = document.getElementById('panNumberErrorSection');");
pw.println("panNumberErrorSection.innerHTML='';");
pw.println("if(panNumber.length==0)");
pw.println("{");
pw.println("panNumberErrorSection.innerHTML='PAN number required';");
pw.println("if(firstInvalidField==null) firstInvalidField=frm.panNumber;");
pw.println("isValid=false;");
pw.println("}");
pw.println("var aadharCardNumber = frm.aadharCardNumber.value.trim();");
pw.println("var aadharCardNumberErrorSection = document.getElementById('aadharCardNumberErrorSection');");
pw.println("aadharCardNumberErrorSection.innerHTML='';");
pw.println("if(aadharCardNumber.length==0)");
pw.println("{");
pw.println("aadharCardNumberErrorSection.innerHTML='Aadhar card number required';");
pw.println("if(firstInvalidField==null) firstInvalidField=frm.aadharCardNumber;");
pw.println("isValid=false;");
pw.println("}");
pw.println("");
pw.println("");
pw.println("if(firstInvalidField!=null) firstInvalidField.focus();");
pw.println("return isValid;");
pw.println("}");
pw.println("function cancelEmployeeUpdation()");
pw.println("{");
pw.println("document.getElementById('cancelEmployeeUpdationForm').submit();");
pw.println("}");
pw.println("</script>");
pw.println("</head>");
pw.println("<body>");
pw.println("<!-- Main container starts here -->");
pw.println("<div style='width:90hw;height:auto;border:1px solid black'>");
pw.println("<!-- Header container starts here -->");
pw.println("<div style='margin:5px;width:90hw;height:auto;border:1px solid black'>");
pw.println("<img src='/styleone/images/logo.png' style='float:left;width:35px;height:auto;padding:5px'><div style='margin-top:9px;margin-bottom:9px;font-size:20pt'>Thinking Machines</div>");
pw.println("</div><!-- Header container ends here -->");
pw.println("<!-- content-section starts here -->");
pw.println("<div style='width:90hw;height:70vh;margin:5px;border:1px solid white'>");
pw.println("<!-- left panel starts here -->");
pw.println("<div style='height:65vh;margin:5px;float:left;padding:5px;border:1px solid black'>");
pw.println("<a href='/styleone/designationsView'>Designations</a><br>");
pw.println("<a href='/styleone/employeesView'>Employees</a>");
pw.println("</div><!-- left panel ends here -->");
pw.println("<!-- right panel starts here -->");
pw.println("<div style='height:65vh;margin-left:105px;margin-right:5px;margin-bottom:5px;margin-top:5px;padding:5px;border:1px solid black'>");
pw.println("<b>Employee (Edit Module)</b><br>");
pw.println("<form method='post' action='/styleone/updateEmployee' onsubmit='return validateForm(this)'>");
pw.println("<input type='hidden' id='employeeId' name='employeeId' value='"+employeeId+"'>"); 
pw.println("<table>");
pw.println("<tr>");
pw.println("<td>Name</td>");
pw.println("<td>");
pw.println("<input type='text' id='name' name='name' maxlength='35' size='36' value='"+name+"'> ");
pw.println("<span id='nameErrorSection' style='color:red'></span>");
pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Designation</td>");
pw.println("<td>");

pw.println("<select id='designationCode' name='designationCode'>");
pw.println("<option value='-1'>&lt;Select Designation&gt;</option>");
int code;
for(DesignationDTO designationDTO:designations)
{
code = designationDTO.getCode();
if(code==designationCode) pw.println("<option selected value='"+code+"'>"+designationDTO.getTitle()+"</option>");
else pw.println("<option value='"+code+"'>"+designationDTO.getTitle()+"</option>");
}
pw.println("</select>");
pw.println("<span id='designationCodeErrorSection' style='color:red'></span>");
pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Date of birth</td>");
pw.println("<td>");
String dateOfBirthString = simpleDateFormat.format(dateOfBirth); 
pw.println("<input type='date' id='dateOfBirth' name='dateOfBirth' value='"+dateOfBirthString+"'>");
pw.println("<span id='dateOfBirthErrorSection' style='color:red'></span>");
pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Gender</td>");
if(gender.equals("M"))
{
pw.println("<td><input checked type='radio' id='male' name='gender' value='M'>Male");
}
else
{
pw.println("<td><input type='radio' id='male' name='gender' value='M'>Male");
}
pw.println("&nbsp;&nbsp;&nbsp;&nbsp;");
if(gender.equals("F"))
{
pw.println("<input checked type='radio' id='female' name='gender' value='F'>Female");
}
else
{
pw.println("<input type='radio' id='female' name='gender' value='F'>Female");
}
pw.println("<span id='genderErrorSection' style='color:red'></span>");
pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Indian?</td>");
pw.println("<td>");
if(isIndian) pw.println("<input checked type='checkbox' id='isIndian' name='isIndian' value='Y'>");
else pw.println("<input type='checkbox' id='isIndian' name='isIndian' value='Y'>");
pw.println("<span id='isIndianErrorSection' style='color:red'></span>");
pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Basic salary</td>");
pw.println("<td>");
pw.println("<input type='text' id='basicSalary' name='basicSalary' maxlength='12' size='13' style='text-align:right' value='"+basicSalary.toPlainString()+"'>");
pw.println("<span id='basicSalaryErrorSection' style='color:red'></span>");
pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>PAN number</td>");
pw.println("<td>");
pw.println("<input type='text' id='panNumber' name='panNumber' maxlength='15' size='16' value='"+panNumber+"'>");
pw.println("<span id='panNumberErrorSection' style='color:red'></span>");
pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Aadhar Card Number</td>");
pw.println("<td>");
pw.println("<input type='text' id='aadharCardNumber' name='aadharCardNumber' maxlength='15' size='16' value='"+aadharCardNumber+"'>");
pw.println("<span id='aadharCardNumberErrorSection' style='color:red'></span>");
pw.println("</td>");
pw.println("</tr>");
pw.println("</table>");
pw.println("");
pw.println("<br><br>");
pw.println("<table>");
pw.println("<tr>");
pw.println("<td>");
pw.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
pw.println("</td>");
pw.println("<td>");
pw.println("<button type='submit'>Update</button>");
pw.println("</form>");
pw.println("</td>");
pw.println("<td>");
pw.println("<button type='button' onclick='cancelEmployeeUpdation()'>Cancel</button>");
pw.println("</td>");
pw.println("</tr>");
pw.println("</table>");
pw.println("</div><!-- right panel ends here -->");
pw.println("</div><!-- content-section ends here -->");
pw.println("<!-- footer starts here -->");
pw.println("<div style='width:90hw;height:auto;margin:5px;text-align:center;border:1px solid footer'>");
pw.println("&copy; Thinking Machines 2025");
pw.println("</div> <!-- footer ends here -->");
pw.println("</div><!-- Main container ends here -->");
pw.println("");
pw.println("<form action='/styleone/employeesView' id='cancelEmployeeUpdationForm'>");
pw.println("</form>");
pw.println("</body>");
pw.println("</html>");

}catch(DAOException daoException)
{
sendBackView(response);
}
catch(Exception exception)
{
sendBackView(response);
}
}
public void sendBackView(HttpServletResponse response)
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