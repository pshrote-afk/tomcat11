function validateForm(frm)
{
var isValid=true;
firstInvalidField=null;
var name = frm.name.value.trim();
var nameErrorSection = document.getElementById('nameErrorSection');
nameErrorSection.innerHTML='';
if(name.length==0)
{
nameErrorSection.innerHTML='Name required';
if(firstInvalidField==null) firstInvalidField=frm.name;
isValid=false;
}
var designationCode = frm.designationCode.value;
var designationCodeErrorSection = document.getElementById('designationCodeErrorSection');
designationCodeErrorSection.innerHTML='';
if(designationCode==(-1))
{
designationCodeErrorSection.innerHTML='Designation required';
if(firstInvalidField==null) firstInvalidField=frm.designationCode;
isValid=false;
}
var dateOfBirth = frm.dateOfBirth.value;
var dateOfBirthErrorSection = document.getElementById('dateOfBirthErrorSection');
dateOfBirthErrorSection.innerHTML='';
if(dateOfBirth.length==0)
{
dateOfBirthErrorSection.innerHTML='Date of birth required';
if(firstInvalidField==null) firstInvalidField=frm.dateOfBirth;
isValid=false;
}
var genderErrorSection = document.getElementById('genderErrorSection');
genderErrorSection.innerHTML='';
if(frm.gender[0].checked==false && frm.gender[1].checked==false)
{
genderErrorSection.innerHTML='Gender required';
//we don't put focus on radio buttons - we don't want to select any
isValid=false;
}
var k=0;
var allowedChars='0123456789.';
var basicSalary = frm.basicSalary.value;
var basicSalaryErrorSection = document.getElementById('basicSalaryErrorSection');
basicSalaryErrorSection.innerHTML='';
if(basicSalary.length==0)
{
basicSalaryErrorSection.innerHTML='Basic salary required';
if(firstInvalidField==null) firstInvalidField=frm.basicSalary;
isValid=false; 
}
while(k < basicSalary.length)
{
if(allowedChars.indexOf(basicSalary.charAt(k))==-1)	// if -1 index is returned, it means char not found
{
basicSalaryErrorSection.innerHTML='Invalid basic salary';
if(firstInvalidField==null) firstInvalidField=frm.basicSalary;
isValid=false; 
break;
}
k++;
}
if((basicSalary.indexOf('.')==-1)==false) //if block true, means dot exists
{
var dotIndex = basicSalary.indexOf('.');
//eg: 1234.567
var afterDot = (basicSalary.length) - (dotIndex + 1);
if(afterDot > 2)
{
basicSalaryErrorSection.innerHTML='Only 2 digits after decimal allowed';
if(firstInvalidField==null) firstInvalidField=frm.basicSalary;
isValid=false; 
}
}

var panNumber = frm.panNumber.value.trim();
var panNumberErrorSection = document.getElementById('panNumberErrorSection');
panNumberErrorSection.innerHTML='';
if(panNumber.length==0)
{
panNumberErrorSection.innerHTML='PAN number required';
if(firstInvalidField==null) firstInvalidField=frm.panNumber;
isValid=false;
}
var aadharCardNumber = frm.aadharCardNumber.value.trim();
var aadharCardNumberErrorSection = document.getElementById('aadharCardNumberErrorSection');
aadharCardNumberErrorSection.innerHTML='';
if(aadharCardNumber.length==0)
{
aadharCardNumberErrorSection.innerHTML='Aadhar card number required';
if(firstInvalidField==null) firstInvalidField=frm.aadharCardNumber;
isValid=false;
}
if(firstInvalidField!=null) firstInvalidField.focus();
return isValid;
}
function cancelEmployeeUpdation()
{
document.getElementById('cancelEmployeeUpdationForm').submit();
}
