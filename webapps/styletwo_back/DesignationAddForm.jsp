<%@taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<tm:Module name='DESIGNATION'></tm:Module>
<jsp:useBean id='designationBean' scope='request' class='com.thinking.machines.hr.beans.DesignationBean'/>
<jsp:useBean id='errorBean' scope='request' class='com.thinking.machines.hr.beans.ErrorBean'/>
<jsp:include page='/MasterPageTopSection.jsp' />
<script src='/styletwo/js/DesignationAddForm.js'></script>
<h2>Designation (Add Module)</h2>
<br>
<form method='post' action='/styletwo/AddDesignation.jsp' onsubmit='return validateForm(this)'>
Designation
<tm:FormId/>
<input type='text' id='title' name='title' maxlength='35' size='36' value='${designationBean.title}'>
<span class='error' id='titleErrorSection'>
<jsp:getProperty name='errorBean' property='error' />
</span><br><br>
<table>
<tr>
<td>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</td>
<td>
<button type='submit'>Add</button>
</form>
</td>
<td>
<button type='button' onclick='cancelAddition()'>Cancel</button>
</td>
</tr>
</table>
<form action='/styletwo/Designations.jsp' id='cancelDesignationAdditionForm'>
</form>

<jsp:include page='/MasterPageBottomSection.jsp' />