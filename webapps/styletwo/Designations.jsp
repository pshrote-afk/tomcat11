<%@taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<tm:Module name='DESIGNATION'></tm:Module>
<jsp:include page='/MasterPageTopSection.jsp' />

<table class='designation-view-table'>
<thead>
<tr>
<th class='add-new-designation-header' colspan='4'>
<a href='/styletwo/DesignationAddForm.jsp'>Add new designation</a>
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

<tm:EntityList populatorClass='com.thinking.machines.hr.bl.DesignationBL' populatorMethod='getAll' name='whatever'>
<tr>
<td style='text-align:right'>${serialNumber}</td>
<td>${whatever.title}</td>
<td style='text-align:center'><a href='/styletwo/editDesignation?code=${whatever.code}'>Edit</a></td>
<td style='text-align:center'><a href='/styletwo/confirmDeleteDesignation?code=${whatever.code}'>Delete</a></td>
</tr>
</tm:EntityList>

</tbody>
</table>

<jsp:include page='/MasterPageBottomSection.jsp' />
