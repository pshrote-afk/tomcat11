<%@taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<tm:Module name='DESIGNATION'></tm:Module>
<jsp:include page='/MasterPageTopSection.jsp' />
<script>
function addAnotherDesignation()
{
var message = document.getElementById('message');
var addButton = document.getElementById('addButton');
var cancelButton = document.getElementById('cancelButton');
message.innerHTML = 'Designation'
addButton.innerText = 'Add';
addButton.onclick = addDesignation;
cancelButton.innerText = 'Cancel';
var titleInputField = document.createElement("input"); 
titleInputField.type = 'text';
titleInputField.id = 'title'; 
titleInputField.name = 'title';
titleInputField.maxlength = 35;
titleInputField.size = 36;
message.after(titleInputField);		// adding a new generated input
}
function addDesignation()
{
var designationAddForm = document.getElementById('designationAddForm');
if(validateForm(designationAddForm)==false)
{
return;
}
//if control reaches here means form is valid at a basic level
var title = document.getElementById('title').value;
var formId = document.getElementById('formId').value;
var dataToSend = 'formId=' + encodeURI(formId);
var dataToSend = dataToSend + '&title=' + encodeURI(title);
var xmlHttpRequest = new XMLHttpRequest();
xmlHttpRequest.onreadystatechange = function() {
if(this.readyState==4)
{
if(this.status==200)
{
var responseData = this.responseText;
var splits = responseData.split(',');
if(splits[0]=='true')	//means successfully added
{
var titleErrorSection = document.getElementById('titleErrorSection');
titleErrorSection.innerHTML = '';
var message = document.getElementById('message');
var addButton = document.getElementById('addButton');
var cancelButton = document.getElementById('cancelButton');
var titleInputField = document.getElementById('title').remove();
message.innerText = 'Designation added. Add more?'
addButton.innerText = 'Yes';
addButton.onclick = addAnotherDesignation;
cancelButton.innerText = 'No';

}
else if(splits[0]=='false') //means not added. Show error.
{
var titleErrorSection = document.getElementById('titleErrorSection');
titleErrorSection.innerHTML = splits[1];
}
else alert('some problem');
}
}
};
xmlHttpRequest.open('POST','designations/add',true);
xmlHttpRequest.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
xmlHttpRequest.send(dataToSend);
}


</script>

<script src='/stylethree/js/DesignationAddForm.js'></script>
<h2>Designation (Add Module)</h2>
<br>
<form id='designationAddForm'>
<tm:FormId/>
<span id = 'message'>Designation</span>
<input type='text' id='title' name='title' maxlength='35' size='36'>
<span class='error' id='titleErrorSection'>
</span><br><br>
<table>
<tr>
<td>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</td>
<td>
<button type='button' onclick='addDesignation()' id='addButton'>Add</button>
</form>
</td>
<td>
<button type='button' id='cancelButton' onclick='cancelAddition()'>Cancel</button>
</td>
</tr>
</table>
<form action='/stylethree/Designations.jsp' id='cancelDesignationAdditionForm'>
</form>

<jsp:include page='/MasterPageBottomSection.jsp' />