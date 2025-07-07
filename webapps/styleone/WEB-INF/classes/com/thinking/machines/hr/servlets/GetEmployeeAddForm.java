package com.thinking.machines.hr.servlets;
import com.thinking.machines.hr.dl.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.*;
public class GetEmployeeAddForm extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
doPost(request,response);
}
public void doPost(HttpServletRequest request,HttpServletResponse response)
{
try
{
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
pw.println("<input type='text' id='name' name='name' maxlength='35' size='36'>");
pw.println("<span id='nameErrorSection' style='color:red'></span>");
pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Designation</td>");
pw.println("<td>");

pw.println("<select id='designationCode' name='designationCode'>");
pw.println("<option value='-1'>&lt;Select Designation&gt;</option>");
for(DesignationDTO designationDTO:designations)
{
pw.println("<option value='"+designationDTO.getCode()+"'>"+designationDTO.getTitle()+"</option>");
}
pw.println("</select>");
pw.println("<span id='designationCodeErrorSection' style='color:red'></span>");
pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Date of birth</td>");
pw.println("<td>");
pw.println("<input type='date' id='dateOfBirth' name='dateOfBirth' value='1970-01-01'>");
pw.println("<span id='dateOfBirthErrorSection' style='color:red'></span>");
pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Gender</td>");
pw.println("<td><input type='radio' id='male' name='gender' value='M'>Male");
pw.println("&nbsp;&nbsp;&nbsp;&nbsp;");
pw.println("<input type='radio' id='female' name='gender' value='F'>Female");
pw.println("<span id='genderErrorSection' style='color:red'></span>");
pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Indian?</td>");
pw.println("<td>");
pw.println("<input type='checkbox' id='isIndian' name='isIndian' value='Y'>");
pw.println("<span id='isIndianErrorSection' style='color:red'></span>");
pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Basic salary</td>");
pw.println("<td>");
pw.println("<input type='text' id='basicSalary' name='basicSalary' maxlength='12' size='13' style='text-align:right'>");
pw.println("<span id='basicSalaryErrorSection' style='color:red'></span>");
pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>PAN number</td>");
pw.println("<td>");
pw.println("<input type='text' id='panNumber' name='panNumber' maxlength='15' size='16'>");
pw.println("<span id='panNumberErrorSection' style='color:red'></span>");
pw.println("</td>");
pw.println("</tr>");
pw.println("<tr>");
pw.println("<td>Aadhar Card Number</td>");
pw.println("<td>");
pw.println("<input type='text' id='aadharCardNumber' name='aadharCardNumber' maxlength='15' size='16'>");
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

}catch(DAOException daoException)
{
System.out.println(daoException.getMessage()); //remove after testing and setup 500 (internal page error)
}
catch(Exception exception)
{
System.out.println(exception.getMessage()); //remove after testing and setup 500 (internal page error)
}

}



}