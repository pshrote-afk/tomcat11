function Employee() //this is a class in JavaScript
{
this.employeeId="";
this.name="";
this.designationCode=0;
this.title="";
this.dateOfBirth="";
this.gender="";
this.isIndian=true;
this.basicSalary="";
this.panNumber="";
this.aadharCardNumber="";
}
var selectedRow=null;
var employees=[];
function selectEmployee(row,employeeId)
{
if(row==selectedRow) return;
if(selectedRow==null)
{
row.style.color='white';
row.style.background='gray';
}
else
{
selectedRow.style.color='black';
selectedRow.style.background='white';
row.style.color='white';
row.style.background='gray';
}
selectedRow = row;
var i;
for(i=0;i<employees.length;i++)
{
if(employees[i].employeeId==employeeId)
{
break;
}
}
var emp=employees[i];
document.getElementById('detailPanel_employeeId').innerHTML=emp.employeeId;
document.getElementById('detailPanel_name').innerHTML=emp.name;
document.getElementById('detailPanel_designation').innerHTML=emp.title;
document.getElementById('detailPanel_dateOfBirth').innerHTML=emp.dateOfBirth;
document.getElementById('detailPanel_gender').innerHTML=emp.gender;
document.getElementById('detailPanel_isIndian').innerHTML=emp.isIndian;
document.getElementById('detailPanel_basicSalary').innerHTML=emp.basicSalary;
document.getElementById('detailPanel_panNumber').innerHTML=emp.panNumber;
document.getElementById('detailPanel_aadharCardNumber').innerHTML=emp.aadharCardNumber;
}

function createDynamicRowClickHandler(rowAddress,employeeId)
{
return function()
{
selectEmployee(rowAddress,employeeId);
};
}

function populateEmployeesGridTable()
{
var employeesGridTable = document.getElementById("employeesGridTable");
var employeesGridTableBody = employeesGridTable.getElementsByTagName("tbody")[0];
var employeesGridTableBodyRowTemplate = employeesGridTableBody.getElementsByTagName("tr")[0];
//remove template ROW from DOM (Document Object Model). (not from memory)
employeesGridTableBodyRowTemplate.remove();
var employeesGridTableColumnsTemplateCollection = employeesGridTableBodyRowTemplate.getElementsByTagName("td");
var cellTemplate;
var k;
var dynamicRow;
var dynamicRowCells;
var placeHolderFor;
for(k=0;k<employees.length;k++)
{
dynamicRow = employeesGridTableBodyRowTemplate.cloneNode(true);
employeesGridTableBody.appendChild(dynamicRow);
dynamicRowCells = dynamicRow.getElementsByTagName("td");
for(var i=0;i<dynamicRowCells.length;i++)
{
cellTemplate = dynamicRowCells[i];
placeHolderFor = cellTemplate.getAttribute("placeHolderId");
if(placeHolderFor==null) continue;
if(placeHolderFor=="serialNumber") cellTemplate.innerHTML = (k+1);
if(placeHolderFor=="employeeId") cellTemplate.innerHTML = employees[k].employeeId;
if(placeHolderFor=="name") cellTemplate.innerHTML = employees[k].name;
if(placeHolderFor=="designationCode") cellTemplate.innerHTML = employees[k].designationCode;
if(placeHolderFor=="title") cellTemplate.innerHTML = employees[k].title;
if(placeHolderFor=="dateOfBirth") cellTemplate.innerHTML = employees[k].dateOfBirth;
if(placeHolderFor=="gender") cellTemplate.innerHTML = employees[k].gender;
if(placeHolderFor=="isIndian") cellTemplate.innerHTML = employees[k].isIndian;
if(placeHolderFor=="basicSalary") cellTemplate.innerHTML = employees[k].basicSalary;
if(placeHolderFor=="panNumber") cellTemplate.innerHTML = employees[k].panNumber;
if(placeHolderFor=="aadharCardNumber") cellTemplate.innerHTML = employees[k].aadharCardNumber;
if(placeHolderFor=="editOption") cellTemplate.innerHTML="<a href='/stylethree/EmployeeEditForm.jsp?employeeId="+employees[k].employeeId+"'>Edit</a>";
if(placeHolderFor=="deleteOption") cellTemplate.innerHTML="<a href='/stylethree/ConfirmDeleteEmployee.jsp?employeeId="+employees[k].employeeId+"'>Delete</a>";
//add click event to dynamicRow
dynamicRow.onclick=createDynamicRowClickHandler(dynamicRow,employees[k].employeeId);


}
}//employees DS loop ends
}//function end
window.addEventListener('load',populateEmployeesGridTable);