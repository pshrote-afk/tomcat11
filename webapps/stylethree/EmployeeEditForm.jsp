<%@taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<tm:Module name='EMPLOYEE'></tm:Module>

<script>
function goToEmployeesView()
{
// below function submits a form which takes action to Employees.jsp
cancelEmployeeUpdation();
}
function designationCodeExists(designationCode)
{
var xmlHttpRequest = new XMLHttpRequest();
xmlHttpRequest.onreadystatechange= function() {
if(this.readyState==4)
{
if(this.status==200)
{
success = JSON.parse(this.responseText).success;
return success;
}
}
};
requestURL = "employees/designationCodeExists?designationCode=" + designationCode;
xmlHttpRequest.open("GET",requestURL,true);
xmlHttpRequest.send();
}
function panNumberExists(panNumber)
{
var xmlHttpRequest = new XMLHttpRequest();
xmlHttpRequest.onreadystatechange= function() {
if(this.readyState==4)
{
if(this.status==200)
{
success = JSON.parse(this.responseText).success;
return success;
}
}
};
requestURL = "employees/panNumberExists?panNumber=" + panNumber;
xmlHttpRequest.open("GET",requestURL,true);
xmlHttpRequest.send();
}
function aadharCardNumberExists(aadharCardNumber)
{
var xmlHttpRequest = new XMLHttpRequest();
xmlHttpRequest.onreadystatechange= function() {
if(this.readyState==4)
{
if(this.status==200)
{
success = JSON.parse(this.responseText).success;
return success;
}
}
};
requestURL = "employees/aadharCardNumberExists?aadharCardNumber=" + aadharCardNumber;
xmlHttpRequest.open("GET",requestURL,true);
xmlHttpRequest.send();
}

function updateEmployee()
{
var employeeEditForm = document.getElementById('employeeEditForm');
if(validateForm(employeeEditForm)==false)
{
return;
}
var errorSection = document.getElementById('errorSection');
var designationCodeErrorSection = document.getElementById('designationCodeErrorSection');
var basicSalaryCodeErrorSection = document.getElementById('basicSalaryErrorSection');
var panNumberErrorSection = document.getElementById('panNumberErrorSection');
var aadharCardNumberErrorSection = document.getElementById('aadharCardNumberErrorSection');
errorSection.innerText = "";
designationCodeErrorSection.innerText = "";
basicSalaryCodeErrorSection.innerText = "";
panNumberErrorSection.innerText = "";
aadharCardNumberErrorSection.innerText = "";

var employeeId = document.getElementById('employeeId').value;
var name = document.getElementById('name').value;
var designationCode = document.getElementById('designationCode').value;
var dateOfBirth = document.getElementById('dateOfBirth').value;
var gender = document.getElementById('employeeEditForm').gender.value;	//note: we got form's id and then .gender.value returns the value of selected radio button
var isIndian = document.getElementById('isIndian').checked;	//returns true/false
var basicSalary = document.getElementById('basicSalary').value;
var panNumber = document.getElementById('panNumber').value;
var aadharCardNumber = document.getElementById('aadharCardNumber').value;

var employee = {
"employeeId":employeeId,
"name":name,
"designationCode":designationCode,
"dateOfBirth":dateOfBirth,
"gender":gender,
"isIndian":isIndian,
"basicSalary":basicSalary,
"panNumber":panNumber,
"aadharCardNumber":aadharCardNumber
};

var designationCodeExistence = designationCodeExists(designationCode);

var basicSalaryLengthExceeds;

if(basicSalary.length > 10) basicSalaryLengthExceeds = true;	//10 digits + 1 decimal point = 11 length. But database only has range to hold 8 digit number
else basicSalaryLengthExceeds = false;

var panNumberExistence = panNumberExists(panNumber);
var aadharCardNumberExistence = aadharCardNumberExists(aadharCardNumber);

// enter if block if any of above four are problematic 
if(designationCodeExistence==false || basicSalaryLengthExceeds==true || panNumberExistence==true || aadharCardNumberExistence==true)
{
if(designationCodeExistence==false) designationCodeErrorSection.innerText = "Invalid designation code";
if(basicSalaryLengthExceeds==true) basicSalaryErrorSection.innerText = "Only 8 digits allowed before decimal";
if(panNumberExistence==true) panNumberErrorSection.innerText = "PAN number already exists";
if(aadharCardNumberExistence==true) aadharCardNumberErrorSection.innerText = "Aadhar card number already exists";
return;	// because all 4 of these should be valid, and thus this if block should never be entered;
}
//control reaches here means all 4 fields are valid. Proceed to actually edit.

var xmlHttpRequest = new XMLHttpRequest();
xmlHttpRequest.onreadystatechange = function() {
if(this.readyState==4)
{
if(this.status==200)
{
var responseData = this.responseText;
responseData = JSON.parse(responseData);
if(responseData.success==true)
{
var employeeEditForm = document.getElementById('employeeEditForm');
employeeEditFormTable.style.display='none';	
var message = document.getElementById('message');
var editButton = document.getElementById('editButton');
var cancelButton = document.getElementById('cancelButton');
message.innerText = 'Employee updated successfully.'
editButton.innerText = 'OK';
editButton.onclick = goToEmployeesView;
cancelButton.style.display = 'none';
}
else if(responseData.success==false)
{
//daoException in errorSection
errorSection.innerText = responseData.errorMessage;
}
else
{
alert('some problem');
}
}
}
};
xmlHttpRequest.open("POST","employees/update",true);
xmlHttpRequest.setRequestHeader("Content-Type","application/json");
xmlHttpRequest.send(JSON.stringify(employee));
}

function editEmployee()
{
var employeeId = new URLSearchParams(window.location.search).get('employeeId');	//creating object for parsing of search query. window is global variable, location represents current page's URL, search stands for search query - href would fetch entire query including http:// ...
var xmlHttpRequest = new XMLHttpRequest();
xmlHttpRequest.onreadystatechange = function() {
if(this.readyState==4)
{
if(this.status==200)
{
var responseData = this.responseText;
responseData = JSON.parse(responseData);

if(responseData.success==true)
{
document.getElementById('employeeId').value = responseData.data.employeeId;	//set hidden form field
document.getElementById('name').value = responseData.data.name;
document.getElementById('designationCode').value = responseData.data.designationCode;
document.getElementById('dateOfBirth').value = responseData.data.dateOfBirth;
document.getElementById('employeeEditForm').gender.value = (responseData.data.gender == 'M')?'M':'F';
document.getElementById('isIndian').checked = (responseData.data.isIndian==true);		//returns true/false
document.getElementById('basicSalary').value = responseData.data.basicSalary;
document.getElementById('panNumber').value = responseData.data.panNumber;
document.getElementById('aadharCardNumber').value = responseData.data.aadharCardNumber;
}
else if(responseData.success==false) //means user manually typed incorrect code in search bar. Redirect to Employees.jsp page w/o further encouragement
{
window.location.href = 'Employees.jsp';
}
else 
{
alert('some problem');
}
}
}
};
var requestURL = 'employees/getByEmployeeId?employeeId=' + encodeURI(employeeId);
xmlHttpRequest.open('GET',requestURL,true);
xmlHttpRequest.send();
}

function populateDesignations()
{
var xmlHttpRequest = new XMLHttpRequest();
xmlHttpRequest.onreadystatechange = function() {
if(this.readyState==4)
{
if(this.status==200)
{
var responseData = this.responseText;
var designations = JSON.parse(responseData);

var designationCodeSelect = document.getElementById('designationCode');
var dynamicOption;
for(var i=0;i<designations.length;i++)
{
dynamicOption = document.createElement('option');
dynamicOption.value = designations[i].code;
dynamicOption.text = designations[i].title;
designationCodeSelect.appendChild(dynamicOption);
}
}
}
};
xmlHttpRequest.open('GET','designations/getAll',true);
xmlHttpRequest.send();
}
window.addEventListener('load',populateDesignations);
window.addEventListener('load',editEmployee);
</script>

<jsp:include page='/MasterPageTopSection.jsp' />
<script src='/stylethree/js/EmployeeEditForm.js'></script>
<h2>Employee (Edit Module)</h2><br>
<form id='employeeEditForm'>
<tm:FormId/>
<input type='hidden' id='employeeId' name='employeeId' value=''>
<span id = 'message'></span>
<table id='employeeEditFormTable'>
<tr>
<td>Name</td>
<td>
<input type='text' id='name' name='name' maxlength='35' size='36' value=''>
<span id='nameErrorSection' style='color:red'></span>
</td>
</tr>
<tr>
<td>Designation</td>
<td>
<select id='designationCode' name='designationCode'>
<option value='-1'>&lt;Select Designation&gt;</option>
</select>

<span id='designationCodeErrorSection' style='color:red'></span>
</td>
</tr>
<tr>
<td>Date of birth</td>
<td>
<input type='date' id='dateOfBirth' name='dateOfBirth' value='1900-01-01'>	
<span id='dateOfBirthErrorSection' style='color:red'></span>
</td>
</tr>
<tr>
<td>Gender</td>
<td>
<input type='radio' id='male' name='gender' value='M'>Male
<input type='radio' id='female' name='gender' value='F'>Female
<span id='genderErrorSection' style='color:red'></span>
</td>
</tr>
<tr>
<td>Indian?</td>
<td>
<input type='checkbox' id='isIndian' name='isIndian' value='Y'>
</td>
<td>
<span id='isIndianErrorSection' style='color:red'></span>
</td>
</tr>
<tr>
<td>Basic salary</td>
<td>
<input type='text' id='basicSalary' name='basicSalary' maxlength='11' size='13' value='' style='text-align:right'>
<span id='basicSalaryErrorSection' style='color:red'></span>
</td>
</tr>
<tr>
<td>PAN number</td>
<td>
<input type='text' id='panNumber' name='panNumber' maxlength='15' size='16' value=''>
<span id='panNumberErrorSection' style='color:red'></span>
</td>
</tr>
<tr>
<td>Aadhar Card Number</td>
<td>
<input type='text' id='aadharCardNumber' name='aadharCardNumber' maxlength='15' size='16' value=''>
<span id='aadharCardNumberErrorSection' style='color:red'></span>
</td>
</tr>
</table>
<span id='errorSection' style='color:red'></span>
<br><br>
<table>
<tr>
<td>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</td>
<td>
<button type='button' id='editButton' onclick='updateEmployee()'>Update</button>
</form>
</td>
<td>
<button type='button' id='cancelButton' onclick='cancelEmployeeUpdation()'>Cancel</button>
</td>
</tr>
</table>
<form action='/stylethree/Employees.jsp' id='cancelEmployeeUpdationForm'>
</form>

<jsp:include page='/MasterPageBottomSection.jsp' />

