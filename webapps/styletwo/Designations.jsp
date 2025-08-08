<%@taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<tm:CheckAuthentication />
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

<tm:Designations>
<tr>
<td style='text-align:right'>${serialNumber}</td>
<td>${designationBean.title}</td>
<td style='text-align:center'><a href='/styletwo/editDesignation?code=${designationBean.code}'>Edit</a></td>
<td style='text-align:center'><a href='/styletwo/confirmDeleteDesignation?code=${designationBean.code}'>Delete</a></td>
</tr>
</tm:Designations>

</tbody>
</table>

<jsp:include page='/MasterPageBottomSection.jsp' />
