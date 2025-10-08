<%@taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<tm:Module name='DESIGNATION'></tm:Module>
<jsp:include page='/MasterPageTopSection.jsp' />

<script>
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
var table = document.getElementById('designationViewTable');
var tbody = document.getElementsByTagName('tbody')[0];
var templateRow = tbody.getElementsByTagName('tr')[0];
//remove template ROW from DOM (Document Object Model). (not from memory)
templateRow.remove();
var cellTemplate;
var k=0;
var dynamicRow;
var dynamicRowCells;
var placeHolderFor;
for(var i = 0;i<designations.length;i++)
{
dynamicRow = templateRow.cloneNode(true);
dynamicRowCells = dynamicRow.getElementsByTagName('td');
dynamicRowCells[0].textContent = k+1 + ".";
dynamicRowCells[1].textContent = designations[i].title;
dynamicRowCells[2].querySelector('a').href = '/stylethree/DesignationEditForm.jsp?code=' + designations[i].code;
dynamicRowCells[3].querySelector('a').href = '/stylethree/ConfirmDeleteDesignation.jsp?code=' + designations[i].code;
tbody.appendChild(dynamicRow);
k++;
}

}
}
};
xmlHttpRequest.open('GET','designations/getAll',true);
xmlHttpRequest.send();
}
window.addEventListener('load',populateDesignations);
</script>

<table class='designation-view-table' id='designationViewTable'>
<thead>
<tr>
<th class='add-new-designation-header' colspan='4'>
<a href='/stylethree/DesignationAddForm.jsp'>Add new designation</a>
</th>
</tr>
<tr>
<th class='s-no'>S.No.</th>
<th class='designation'>Designation</th>
<th class='edit'>Edit</th>
<th class='delete'>Delete</th>
</tr>
</thead>
<tbody>

<tr>
<td style='text-align:right'>${serialNumber}</td>
<td>${whatever.title}</td>
<td style='text-align:center'><a href='/stylethree/DesignationEditForm.jsp?code=${whatever.code}'>Edit</a></td>
<td style='text-align:center'><a href='/stylethree/ConfirmDeleteDesignation.jsp?code=${whatever.code}'>Delete</a></td>
</tr>


</tbody>
</table>

<jsp:include page='/MasterPageBottomSection.jsp' />
