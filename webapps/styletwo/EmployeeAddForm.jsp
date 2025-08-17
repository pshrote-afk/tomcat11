<%@taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<tm:Module name='EMPLOYEE'></tm:Module>
<jsp:useBean id='employeeBean' scope='request' class='com.thinking.machines.hr.beans.EmployeeBean' />
<jsp:useBean id='employeeErrorBean' scope='request' class='com.thinking.machines.hr.beans.EmployeeErrorBean' />
<jsp:include page='/MasterPageTopSection.jsp' />
<script src='/styletwo/js/EmployeeAddForm.js'></script>
<h2>Employee (Add Module)</h2><br>
<form method='post' action='/styletwo/AddEmployee.jsp' onsubmit='return validateForm(this)'>
<tm:FormId/>
<table>
<tr>
<td>Name</td>
<td>
<input type='text' id='name' name='name' maxlength='35' size='36' value='${employeeBean.name}'>
<span id='nameErrorSection' style='color:red'></span>
</td>
</tr>
<tr>
<td>Designation</td>
<td>
<select id='designationCode' name='designationCode'>
<option value='-1'>&lt;Select Designation&gt;</option>
<tm:EntityList populatorClass='com.thinking.machines.hr.bl.DesignationBL' populatorMethod='getAll' name='designation'>
<tm:If condition='${employeeBean.designationCode==designation.code}'>
<option selected value='${designation.code}'>${designation.title}</option>
</tm:If>
<tm:If condition='${employeeBean.designationCode!=designation.code}'>
<option value='${designation.code}'>${designation.title}</option>
</tm:If>
</tm:EntityList>
</select>

<span id='designationCodeErrorSection' style='color:red'>${employeeErrorBean.designationCodeError}</span>
</td>
</tr>
<tr>
<td>Date of birth</td>
<td>
<input type='date' id='dateOfBirth' name='dateOfBirth' value='${employeeBean.dateOfBirth}'>	
<span id='dateOfBirthErrorSection' style='color:red'></span>
</td>
</tr>
<tr>
<td>Gender</td>
<tm:If condition='${employeeBean.male}'>
<td><input checked type='radio' id='male' name='gender' value='M'>Male
</tm:If>
<tm:If condition='${! employeeBean.male}'>
<td><input type='radio' id='male' name='gender' value='M'>Male
</tm:If>
&nbsp;&nbsp;&nbsp;&nbsp;
<tm:If condition='${employeeBean.female}'>
<input checked type='radio' id='female' name='gender' value='F'>Female
</tm:If>
<tm:If condition='${! employeeBean.female}'>
<input type='radio' id='female' name='gender' value='F'>Female
</tm:If>
<span id='genderErrorSection' style='color:red'></span>
</td>
</tr>
<tr>
<td>Indian?</td>
<td>
<tm:If condition='${employeeBean.indian}'>
<input checked type='checkbox' id='isIndian' name='isIndian' value='Y'>
</tm:If>
<tm:If condition='${! employeeBean.indian}'>
<input type='checkbox' id='isIndian' name='isIndian' value='Y'>
</tm:If>
<span id='isIndianErrorSection' style='color:red'></span>
</td>
</tr>
<tr>
<td>Basic salary</td>
<td>
<input type='text' id='basicSalary' name='basicSalary' maxlength='11' size='13' value='${employeeBean.basicSalary}' style='text-align:right'>
<span id='basicSalaryErrorSection' style='color:red'>${employeeErrorBean.basicSalaryError}</span>
</td>
</tr>
<tr>
<td>PAN number</td>
<td>
<input type='text' id='panNumber' name='panNumber' maxlength='15' size='16' value='${employeeBean.panNumber}'>
<span id='panNumberErrorSection' style='color:red'>${employeeErrorBean.panNumberError}</span>
</td>
</tr>
<tr>
<td>Aadhar Card Number</td>
<td>
<input type='text' id='aadharCardNumber' name='aadharCardNumber' maxlength='15' size='16' value='${employeeBean.aadharCardNumber}'>
<span id='aadharCardNumberErrorSection' style='color:red'>${employeeErrorBean.aadharCardNumberError}</span>
</td>
</tr>
</table>
<span id='errorSection' style='color:red'>${errorBean.error}</span>
<br><br>
<table>
<tr>
<td>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</td>
<td>
<button type='submit'>Add</button>
</form>
</td>
<td>
<button type='button' onclick='cancelEmployeeAddition()'>Cancel</button>
</td>
</tr>
</table>
<form action='/styletwo/Employees.jsp' id='cancelEmployeeAdditionForm'>
</form>

<jsp:include page='/MasterPageBottomSection.jsp' />

