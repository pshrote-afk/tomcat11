<%@taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<tm:Module name='DESIGNATION'></tm:Module>
<jsp:include page='/MasterPageTopSection.jsp' />
<script>
function goToDesignationsView()
{
// below function submits a form which takes action to Designations.jsp
cancelUpdation();
}
function updateDesignation()
{
var code = document.getElementById('code').value;
var title = document.getElementById('title').value;
var dataToSend = 'code=' + encodeURI(code);
dataToSend = dataToSend + '&title=' + title;
var xmlHttpRequest = new XMLHttpRequest();
xmlHttpRequest.onreadystatechange = function() {
if(this.readyState==4)
{
if(this.status==200)
{
var responseData = this.responseText;
var splits = responseData.split(",");
if(splits[0]=='true')
{
var titleErrorSection = document.getElementById('titleErrorSection');
titleErrorSection.innerHTML = '';
var message = document.getElementById('message');
var editButton = document.getElementById('editButton');
var cancelButton = document.getElementById('cancelButton');
var titleInputField = document.getElementById('title').remove();
message.innerText = 'Designation updated successfully'; 
editButton.innerText = 'OK';
editButton.onclick = goToDesignationsView;
cancelButton.style.display = 'none';
}
else if(splits[0]=='false')
{
var titleErrorSection = document.getElementById('titleErrorSection');
titleErrorSection.innerHTML = splits[1];
return;
}
else
{
alert('some problem');
}


}
}
};
xmlHttpRequest.open('POST','designations/update',true);
xmlHttpRequest.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
xmlHttpRequest.send(dataToSend);
}

function editDesignation()
{
var code = new URLSearchParams(window.location.search).get('code');	//creating object for parsing of search query. window is global variable, location represents current page's URL, search stands for search query - href would fetch entire query including http:// ...
document.getElementById('code').value = code;
var xmlHttpRequest = new XMLHttpRequest();
xmlHttpRequest.onreadystatechange = function() {
if(this.readyState==4)
{
if(this.status==200)
{
var responseData = this.responseText;
var splits = responseData.split(',');
if(splits[0]=='false') //means user manually typed incorrect code in search bar. Redirect to Designations.jsp page w/o further encouragement
{
window.location.href = 'Designations.jsp';
}
else if(splits[0]=='true')
{
var title = document.getElementById('title');
title.value = splits[2];
}
else 
{
alert('some problem');
}
}
}
};
var requestURL = 'designations/getByCode?code=' + encodeURI(code);
xmlHttpRequest.open('GET',requestURL,true);
xmlHttpRequest.send();
}
// doubt: how do we know which code to get
// answer: look at editDesignation() function
window.addEventListener('load',editDesignation);
</script>

<script src='/stylethree/js/DesignationEditForm.js'></script>
<h2>Designation (Edit Module)</h2>
<br>
<form id='designationEditForm' onsubmit='return validateForm(this)'>
<input type='hidden' id='code' name='code' value=''>
<span id = 'message'>Designation</span>
<input type='text' id='title' name='title' maxlength='35' size='36' value=''>
<span class='error' id='titleErrorSection'>
</span><br><br>
<table>
<tr>
<td>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</td>
<td>
<button type='button' onclick='updateDesignation()' id='editButton'>Update</button>
</form>
</td>
<td>
<button type='button' id='cancelButton' onclick='cancelUpdation()'>Cancel</button>
</td>
</tr>
</table>
<form action='/stylethree/Designations.jsp' id='cancelUpdationForm'>
</form>

<jsp:include page='/MasterPageBottomSection.jsp' />