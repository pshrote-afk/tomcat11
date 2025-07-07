package com.thinking.machines.hr.servlets;
import com.thinking.machines.hr.dl.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.text.*;
import java.io.*;
import java.math.*;
import java.util.*;
public class AddEmployee extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
doPost(request,response);
}
public void doPost(HttpServletRequest request,HttpServletResponse response) 
{
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
try
{
response.setContentType("text/html");
PrintWriter pw = response.getWriter();

String name = request.getParameter("name");
int designationCode = Integer.parseInt(request.getParameter("designationCode"));
java.util.Date dateOfBirth = simpleDateFormat.parse(request.getParameter("dateOfBirth"));
String gender = request.getParameter("gender");
String isIndian = request.getParameter("isIndian");
if(isIndian==null) isIndian="N";	//if null, means unchecked
BigDecimal basicSalary = new BigDecimal(request.getParameter("basicSalary"));
String panNumber = request.getParameter("panNumber");
String aadharCardNumber = request.getParameter("aadharCardNumber");

//validate 1. designation code 2. pan 3. aadhar
EmployeeDAO employeeDAO = new EmployeeDAO();
boolean designationCodeExists = employeeDAO.designationCodeExists(designationCode);
boolean panNumberExists = employeeDAO.panNumberExists(panNumber);
boolean aadharCardNumberExists = employeeDAO.aadharCardNumberExists(aadharCardNumber);
// enter if block if any of above three are problematic - send back form with filled data, and error messages
if(designationCodeExists==false || panNumberExists==true || aadharCardNumberExists==true)
{
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
pw.println("function cancelEmployeeAddition()");
pw.println("{");
pw.println("document.getElementById('cancelEmployeeAdditionForm').submit();");
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
pw.println("<b>Employee (Add Module)</b><br>");
pw.println("<form method='post' action='/styleone/addEmployee' onsubmit='return validateForm(this)'>");
pw.println("<table>");
pw.println("<tr>");
pw.println("<td>Name</td>");
pw.println("<td>");
pw.println("<input type='text' id='name' name='name' maxlength='35' size='36' value='"+name+"'>");
pw.println("<span id='nameErrorSection' style='color:red'></span>");
pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Designation</td>");
pw.println("<td>");

pw.println("<select id='designationCode' name='designationCode'>");
pw.println("<option value='-1'>&lt;Select Designation&gt;</option>");

DesignationDAO designationDAO = new DesignationDAO();
List<DesignationDTO> designations = designationDAO.getAll();

for(DesignationDTO designationDTO:designations)
{
if(designationCodeExists==true && designationCode==designationDTO.getCode()) pw.println("<option selected value='"+designationDTO.getCode()+"'>"+designationDTO.getTitle()+"</option>");
else pw.println("<option value='"+designationDTO.getCode()+"'>"+designationDTO.getTitle()+"</option>");
}
pw.println("</select>");
if(designationCodeExists==true) pw.println("<span id='designationCodeErrorSection' style='color:red'></span>");
else pw.println("<span id='designationCodeErrorSection' style='color:red'>Designation does not exist.</span>");

pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Date of birth</td>");
pw.println("<td>");
//done done
pw.println("<input type='date' id='dateOfBirth' name='dateOfBirth' value='"+simpleDateFormat.format(dateOfBirth)+"'>");
pw.println("<span id='dateOfBirthErrorSection' style='color:red'></span>");
pw.println("</td>");
pw.println("</tr>");

pw.println("<tr>");
pw.println("<td>Gender</td>");
if(gender.equals("M")) pw.println("<td><input checked type='radio' id='male' name='gender' value='M'>Male");
else pw.println("<td><input checked type='radio' id='male' name='gender' value='M'>Male");
pw.println("&nbsp;&nbsp;&nbsp;&nbsp;");
if(gender.equals("F")) pw.println("<input checked type='radio' id='female' name='gender' value='F'>Female");
else pw.println("<input type='radio' id='female' name='gender' value='F'>Female");

pw.println("<span id='genderErrorSection' style='color:red'></span>");
pw.println("</td>");
pw.println("</tr>");

pw.println("<tr>");
pw.println("<td>Indian?</td>");
pw.println("<td>");
if(isIndian.equals("Y")) pw.println("<input checked type='checkbox' id='isIndian' name='isIndian' value='Y'>");
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

if(panNumberExists) pw.println("<span id='panNumberErrorSection' style='color:red'>PAN number already exists</span>");

else pw.println("<span id='panNumberErrorSection' style='color:red'></span>");

pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Aadhar Card Number</td>");
pw.println("<td>");
pw.println("<input type='text' id='aadharCardNumber' name='aadharCardNumber' maxlength='15' size='16' value='"+aadharCardNumber+"'>");

if(aadharCardNumberExists) pw.println("<span id='aadharCardNumberErrorSection' style='color:red'>Aadhar card number exists</span>");
else pw.println("<span id='aadharCardNumberErrorSection' style='color:red'></span>");

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
pw.println("<button type='submit'>Add</button>");
pw.println("</form>");
pw.println("</td>");
pw.println("<td>");
pw.println("<button type='button' onclick='cancelEmployeeAddition()'>Cancel</button>");
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
pw.println("<form action='/styleone/employeesView' id='cancelEmployeeAdditionForm'>");
pw.println("</form>");
pw.println("</body>");
pw.println("</html>");

return;
}

EmployeeDTO employeeDTO = new EmployeeDTO();
employeeDTO.setName(name);
employeeDTO.setDesignationCode(designationCode);
employeeDTO.setDateOfBirth(dateOfBirth);
employeeDTO.setGender(gender);
employeeDTO.setIsIndian(isIndian.equals("Y"));
employeeDTO.setBasicSalary(basicSalary);
employeeDTO.setPANNumber(panNumber);
employeeDTO.setAadharCardNumber(aadharCardNumber);

employeeDAO = new EmployeeDAO();
employeeDAO.add(employeeDTO);
pw.println("<!DOCTYPE HTML>");
pw.println("<html lang = 'en'>");
pw.println("<head>");
pw.println("<title>HR Application</title>");
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
pw.println("<h3>Notification</h3>");
pw.println("Employee added with employee id: "+employeeDTO.getEmployeeId()+"<br><br>");
pw.println("Add more employees?");
pw.println("<table>");
pw.println("<tr>");
pw.println("<td>");
pw.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
pw.println("</td>");
pw.println("<td>");
pw.println("<form action='/styleone/getEmployeeAddForm'>");
pw.println("<button type='submit'>Yes</button>");
pw.println("</form>");
pw.println("</td>");
pw.println("<td>");
pw.println("<form action='/styleone/employeesView'>");
pw.println("<button type='submit'>No</button>");
pw.println("</form>");
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
pw.println("</body>");
pw.println("</html>");


}catch(DAOException daoException)
{
System.out.println(daoException.getMessage());

// collect all errors. And return to top of page
//return form with all values intact
}
catch(Exception exception)
{
System.out.println(exception.getMessage());
}


}
}