<%@taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<tm:CheckAuthentication />
<tm:Module name='DESIGNATION'></tm:Module>

<jsp:useBean id='designationBean' scope='request' class='com.thinking.machines.hr.beans.DesignationBean' />
<jsp:useBean id='errorBean' scope='request' class='com.thinking.machines.hr.beans.ErrorBean' />

<jsp:include page='/MasterPageTopSection.jsp' />

<script src='/styletwo/js/DesignationEditForm.js'></script>
<h2>Designation (Edit Module)</h2>
<br>
<form method='post' action='/styletwo/EditDesignation.jsp' onsubmit='return validateForm(this)'>
<input type='hidden' id='code' name='code' value='<jsp:getProperty name='designationBean' property='code' />'>
Designation
<input type='text' id='title' name='title' maxlength='35' size='36' value='<jsp:getProperty name='designationBean' property='title' />'>
<span class='error' id='titleErrorSection'>
<jsp:getProperty name='errorBean' property='error' />
</span><br><br>
<table>
<tr>
<td>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</td>
<td>
<button type='submit'>Update</button>
</form>
</td>
<td>
<button type='button' onclick='cancelUpdation()'>Cancel</button>
</td>
</tr>
</table>
<form action='/styletwo/Designations.jsp' id='cancelUpdationForm'>
</form>

<jsp:include page='/MasterPageBottomSection.jsp' />