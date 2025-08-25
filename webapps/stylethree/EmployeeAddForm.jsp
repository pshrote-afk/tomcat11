<%@taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<tm:Module name='EMPLOYEE'></tm:Module>

<script>
function designationCodeExists(designationCode)
{
var xmlHttpRequest = new XMLHttpRequest();
xmlHttpRequest.onreadystatechange= function() {
if(this.readyState==4)
{
if(this.status==200)
{
return this.responseText;
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
return this.responseText;
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
return this.responseText;
}
}
};
requestURL = "employees/aadharCardNumberExists?aadharCardNumber=" + aadharCardNumber;
xmlHttpRequest.open("GET",requestURL,true);
xmlHttpRequest.send();
}

function addAnotherEmployee()
{
window.location.href = 'EmployeeAddForm.jsp';
}

function addEmployee()
{
var employeeAddFrom = document.getElementById('employeeAddForm');
if(validateForm(employeeAddForm)==false)
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


var name = document.getElementById('name').value;
var designationCode = document.getElementById('designationCode').value;
var dateOfBirth = document.getElementById('dateOfBirth').value;
var gender = document.getElementById('employeeAddForm').gender.value;	//note: we got form's id and then .gender.value returns the value of selected radio button
var isIndian = document.getElementById('isIndian').checked;	//returns true/false
var basicSalary = document.getElementById('basicSalary').value;
var panNumber = document.getElementById('panNumber').value;
var aadharCardNumber = document.getElementById('aadharCardNumber').value;

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
if(panNumberExistence==true) 
{
panNumberErrorSection.innerText = "PAN number already exists";
}
if(aadharCardNumberExistence==true) aadharCardNumberErrorSection.innerText = "Aadhar card number already exists";
return;	// because all 4 of these should be valid, and thus this if block should never be entered;
}
//control reaches here means all 4 fields are valid. Proceed to actually add.

var dataToSend = "name=" + encodeURI(name) + "&designationCode=" + encodeURI(designationCode) + "&dateOfBirth=" + encodeURI(dateOfBirth) + "&gender=" + encodeURI(gender) + "&isIndian=" + encodeURI(isIndian) + "&basicSalary=" + encodeURI(basicSalary) + "&panNumber=" + encodeURI(panNumber) + "&aadharCardNumber=" + encodeURI(aadharCardNumber);
var xmlHttpRequest = new XMLHttpRequest();
xmlHttpRequest.onreadystatechange = function() {
if(this.readyState==4)
{
if(this.status==200)
{
var responseData = this.responseText;
var splits = responseData.split(",");
if(splits[0]=='false')
{
//daoException in errorSection
errorSection.innerText = splits[1];
}
else if(splits[0]=='true')
{
var employeeAddForm = document.getElementById('employeeAddForm');
employeeAddFormTable.style.display='none';	
var message = document.getElementById('message');
var addButton = document.getElementById('addButton');
var cancelButton = document.getElementById('cancelButton');
message.innerText = 'Employee added. Add more?'
addButton.innerText = 'Yes';
addButton.onclick = addAnotherEmployee;
cancelButton.innerText = 'No';
}
else
{
alert('some problem');
}
}
}
};
xmlHttpRequest.open("POST","employees/add",true);
xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
xmlHttpRequest.send(dataToSend);
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
var splits = responseData.split(",");
var designationCodeSelect = document.getElementById('designationCode');
var dynamicOption;
for(var i=0;i<splits.length;i+=2)
{
dynamicOption = document.createElement('option');
dynamicOption.value = splits[i];
dynamicOption.text = splits[i+1];
designationCodeSelect.appendChild(dynamicOption);
}
}
}
};
xmlHttpRequest.open('GET','designations/getAll',true);
xmlHttpRequest.send();
}
window.addEventListener('load',populateDesignations);
</script>

<jsp:include page='/MasterPageTopSection.jsp' />
<script src='/stylethree/js/EmployeeAddForm.js'></script>
<h2>Employee (Add Module)</h2><br>
<form id='employeeAddForm'>
<tm:FormId/>
<span id = 'message'></span>
<table id='employeeAddFormTable'>
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
<button type='button' id='addButton' onclick='addEmployee()'>Add</button>
</form>
</td>
<td>
<button type='button' id='cancelButton' onclick='cancelEmployeeAddition()'>Cancel</button>
</td>
</tr>
</table>
<form action='/stylethree/Employees.jsp' id='cancelEmployeeAdditionForm'>
</form>

<jsp:include page='/MasterPageBottomSection.jsp' />

