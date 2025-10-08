<%@taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<tm:Module name='EMPLOYEE'></tm:Module>

<script>
function goToEmployeesView()
{
// below function submits a form which takes action to Employees.jsp
cancelEmployeeDeletion();
}

function deleteEmployee()
{
var employeeId = document.getElementById('employeeId').value;
var dataToSend = "employeeId=" + encodeURI(employeeId)

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
var employeeDeleteForm = document.getElementById('employeeDeleteForm');
employeeDeleteFormTable.style.display='none';	
var message = document.getElementById('message');
var deleteButton = document.getElementById('deleteButton');
var cancelButton = document.getElementById('cancelButton');
message.innerText = 'Employee deleted successfully.'
deleteButton.innerText = 'OK';
deleteButton.onclick = goToEmployeesView;
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
xmlHttpRequest.open("POST","employees/delete",true);
xmlHttpRequest.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
xmlHttpRequest.send(dataToSend);
}

function confirmDeleteEmployee()
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
document.getElementById('name').innerText = responseData.data.name;
document.getElementById('designationCode').innerText = responseData.data.title;	//splits[4] contains designation 'title'. splits[3] contains designation 'code'
document.getElementById('dateOfBirth').innerText = responseData.data.dateOfBirth;
document.getElementById('gender').innerText = (responseData.data.gender == 'M')?'Male':'Female'
document.getElementById('isIndian').innerText = (responseData.data.isIndian==true)?'Indian':'Not Indian';			//returns true/false
document.getElementById('basicSalary').innerText = responseData.data.basicSalary;
document.getElementById('panNumber').innerText = responseData.data.panNumber;
document.getElementById('aadharCardNumber').innerText = responseData.data.aadharCardNumber;
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


window.addEventListener('load',confirmDeleteEmployee);
</script>

<jsp:include page='/MasterPageTopSection.jsp' />
<script src='/stylethree/js/ConfirmDeleteEmployee.js'></script>
<h2>Employee (Delete Module)</h2><br>
<form id='employeeDeleteForm'>
<tm:FormId/>
<input type='hidden' id='employeeId' name='employeeId' value=''>
<span id = 'message'>Are you sure you want to delete employee?</span><br><br>
<table id='employeeDeleteFormTable'>
<tr>
<td>Name:</td>
<td>
<b><span id='name' name='name'></span></b>
<span id='nameErrorSection' style='color:red'></span>
</td>
</tr>
<tr>
<td>Designation:</td>
<td>
<b><span id='designationCode' name='designationCode'></span></b>
<span id='designationCodeErrorSection' style='color:red'></span>
</td>
</tr>
<tr>
<td>Date of birth:</td>
<td>
<b><span id='dateOfBirth' name='dateOfBirth'></span></b>
<span id='dateOfBirthErrorSection' style='color:red'></span>
</td>
</tr>
<tr>
<td>Gender:</td>
<td>
<b><span id='gender' name='gender'></span></b>
<span id='genderErrorSection' style='color:red'></span>
</td>
</tr>
<tr>
<td>Indian?</td>
<td>
<b><span id='isIndian' name='isIndian'></span></b>
</td>
<td>
<span id='isIndianErrorSection' style='color:red'></span>
</td>
</tr>
<tr>
<td>Basic salary:</td>
<td>
<b><span id='basicSalary' name='basicSalary'></span></b>
<span id='basicSalaryErrorSection' style='color:red'></span>
</td>
</tr>
<tr>
<td>PAN number:</td>
<td>
<b><span id='panNumber' name='panNumber'></span></b>
<span id='panNumberErrorSection' style='color:red'></span>
</td>
</tr>
<tr>
<td>Aadhar Card Number:</td>
<td>
<b><span id='aadharCardNumber' name='aadharCardNumber'></span></b>
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
<button type='button' id='deleteButton' onclick='deleteEmployee()'>Delete</button>
</form>
</td>
<td>
<button type='button' id='cancelButton' onclick='cancelEmployeeDeletion()'>Cancel</button>
</td>
</tr>
</table>
<form action='/stylethree/Employees.jsp' id='cancelEmployeeDeletionForm'>
</form>

<jsp:include page='/MasterPageBottomSection.jsp' />

