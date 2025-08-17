<%@taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<tm:Module name='EMPLOYEE'></tm:Module>
<jsp:useBean id='employeeBean' scope='request' class='com.thinking.machines.hr.beans.EmployeeBean' />
<jsp:include page='/MasterPageTopSection.jsp' />
<script src='/styletwo/js/EmployeeDeleteForm.js'></script>
<h2>Employee (Delete Module)</h2><br>
<form method='post' action='/styletwo/DeleteEmployee.jsp' onsubmit='return validateForm(this)'>
<input type='hidden' id='employeeId' name='employeeId' value='${employeeBean.employeeId}'>
<table border='1'>
<tr>
<td>Name: </td>
<td>
<b>${employeeBean.name}</b>
</td>
</tr>
<tr>
<td>Designation: </td>
<td><b>
<tm:EntityList populatorClass='com.thinking.machines.hr.bl.DesignationBL' populatorMethod='getAll' name='designation'>
<tm:If condition='${employeeBean.designationCode==designation.code}'>
<b>${designation.title}</b>
</tm:If>
</tm:EntityList>
</td>
</tr>
<tr>
<td>Date of birth: </td>
<td>
<b>${employeeBean.dateOfBirth}</b>
</td>
</tr>
<tr>
<td>Gender:</td>
<td>	
<tm:If condition='${employeeBean.male}'>
<b>Male</b>
</tm:If>
<tm:If condition='${employeeBean.female}'>
<b>Female</b>
</tm:If>
</td>
</tr>
<tr>
<td>Nationality: </td>
<td>
<tm:If condition='${employeeBean.indian}'>
<b>Indian</b>
</tm:If>
<tm:If condition='${! employeeBean.indian}'>
<b>Not Indian</b>
</tm:If>
</td>
</tr>
<tr>
<td>Basic salary: </td>
<td>
<b>${employeeBean.basicSalary}</b>
</td>
</tr>
<tr>
<td>PAN number: </td>
<td>
<b>${employeeBean.panNumber}</b>
</td>
</tr>
<tr>
<td>Aadhar Card Number:</td>
<td>
<b>${employeeBean.aadharCardNumber}</b>
</td>
</tr>
</table>
<br><br>
<table>
<tr>
<td>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</td>
<td>
<button type='submit'>Delete</button>
</form>
</td>
<td>
<button type='button' onclick='cancelEmployeeDeletion()'>Cancel</button>
</td>
</tr>
</table>
<form action='/styletwo/Employees.jsp' id='cancelEmployeeDeletionForm'>
</form>

<jsp:include page='/MasterPageBottomSection.jsp' />

