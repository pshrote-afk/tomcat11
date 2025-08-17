package com.thinking.machines.hr.servlets;
import com.thinking.machines.hr.dl.*;
import com.thinking.machines.hr.beans.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.math.*;
import java.text.*;
public class UpdateEmployee extends HttpServlet
{
public void doGet(HttpServletRequest request,HttpServletResponse response)
{
doPost(request,response);
}

public void doPost(HttpServletRequest request,HttpServletResponse response)
{
//check Authentication
HttpSession httpSession = request.getSession();
if(httpSession.getAttribute("username")==null)
{
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("/LoginForm.jsp");
try
{
requestDispatcher.forward(request,response);
return;
}catch(Exception exception)
{
//do nothing
}
}
SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
try
{
//extract data from bean
EmployeeBean employeeBean;
employeeBean = (EmployeeBean)request.getAttribute("employeeBean");
String employeeId = employeeBean.getEmployeeId();
String name = employeeBean.getName();
int designationCode = employeeBean.getDesignationCode();
java.util.Date dateOfBirth = simpleDateFormat.parse(employeeBean.getDateOfBirth());
String gender = employeeBean.getGender();
String isIndian = employeeBean.getIsIndian();
if(isIndian.equals("")) isIndian="N";
String basicSalary = employeeBean.getBasicSalary();
String panNumber = employeeBean.getPanNumber();
String aadharCardNumber = employeeBean.getAadharCardNumber();

EmployeeDAO employeeDAO = new EmployeeDAO();
EmployeeDTO tempEmployeeDTO = employeeDAO.getByEmployeeId(employeeId);
String oldPANNumber = tempEmployeeDTO.getPANNumber();
String oldAadharCardNumber = tempEmployeeDTO.getAadharCardNumber();

//validate 1. designation code 2. basicSalary length 3. pan 4. aadhar
boolean designationCodeExists = employeeDAO.designationCodeExists(designationCode);
boolean basicSalaryLengthExceeds;
if(basicSalary.length() > 10) basicSalaryLengthExceeds = true;	//10 digits + 1 decimal point = 11 length. But database only has range to hold 8 digit number
else basicSalaryLengthExceeds = false;
boolean panNumberExists = employeeDAO.panNumberExists(panNumber);
boolean aadharCardNumberExists = employeeDAO.aadharCardNumberExists(aadharCardNumber);
// enter if block if any of above three are problematic - send request to form with filled error bean
if(designationCodeExists==false || basicSalaryLengthExceeds==true || (panNumberExists==true && !(panNumber.equalsIgnoreCase(oldPANNumber))) || (aadharCardNumberExists==true && !(aadharCardNumber.equalsIgnoreCase(oldAadharCardNumber))) )
{
EmployeeErrorBean employeeErrorBean = new EmployeeErrorBean();
if(designationCodeExists==false) employeeErrorBean.setDesignationCodeError("Invalid designation code");
if(basicSalaryLengthExceeds==true) employeeErrorBean.setBasicSalaryError("Only 8 digits allowed before decimal");
if(panNumberExists==true && !(panNumber.equalsIgnoreCase(oldPANNumber))) employeeErrorBean.setPanNumberError("PAN number already exists");
if(aadharCardNumberExists==true && !(aadharCardNumber.equalsIgnoreCase(oldAadharCardNumber))) employeeErrorBean.setAadharCardNumberError("Aadhar card number already exists");
request.setAttribute("employeeErrorBean",employeeErrorBean);
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("EmployeeEditForm.jsp");
try
{
requestDispatcher.forward(request,response);
}
catch(Exception e)
{//do nothing
}
return;
}

EmployeeDTO employeeDTO = new EmployeeDTO();
employeeDTO.setEmployeeId(employeeId);
employeeDTO.setName(name);
employeeDTO.setDesignationCode(designationCode);
employeeDTO.setDateOfBirth(dateOfBirth);
employeeDTO.setGender(gender);
employeeDTO.setIsIndian(isIndian.equals("Y"));
employeeDTO.setBasicSalary(new BigDecimal(basicSalary));
employeeDTO.setPANNumber(panNumber);
employeeDTO.setAadharCardNumber(aadharCardNumber);
try
{
employeeDAO.update(employeeDTO);
employeeBean.setEmployeeId(employeeDTO.getEmployeeId());
MessageBean messageBean = new MessageBean();
messageBean.setHeading("Employee (Edit Module)");
messageBean.setMessage("Employee updated.");
messageBean.setGenerateButtons(true);
messageBean.setGenerateTwoButtons(false);
messageBean.setButtonOneText("OK");
messageBean.setButtonOneAction("Employees.jsp");

request.setAttribute("messageBean",messageBean);
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("/Notification.jsp");
requestDispatcher.forward(request,response); 	//may throw ServletException
}catch(DAOException daoException)
{
ErrorBean errorBean = new ErrorBean();
errorBean.setError(daoException.getMessage());

request.setAttribute("errorBean",errorBean);
RequestDispatcher requestDispatcher;
requestDispatcher = request.getRequestDispatcher("/EmployeeEditForm.jsp");
requestDispatcher.forward(request,response);		//may throw ServletException
}
}catch(Exception exception)
{
System.out.println(exception.getMessage()); //remove after testing
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