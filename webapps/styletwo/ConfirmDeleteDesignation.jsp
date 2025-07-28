<jsp:useBean id='designationBean' scope='request' class='com.thinking.machines.hr.beans.DesignationBean' />
<jsp:useBean id='errorBean' scope='request' class='com.thinking.machines.hr.beans.ErrorBean' />
<!DOCTYPE HTML>
<html lang = 'en'>
<head>
<title>HR Application</title>
<script src='/styletwo/js/ConfirmDeleteDesignation.js'></script>
<link rel='stylesheet' type='text/css' href='/styletwo/css/styles.css'></link>
</head>
<body>
<!-- Main container starts here -->
<div class='main-container'>
<!-- Header container starts here -->
<div class='header-container'>
<img src='/styletwo/images/logo.png' class='logo'><div class='brand-name'>Thinking Machines</div>
</div><!-- Header container ends here -->
<!-- content-section starts here -->
<div class='content'>
<!-- left panel starts here -->
<div class='content-left-panel'>
<b>Designations</b><br>
<a href='/styletwo/Employees.jsp'>Employees</a>
</div><!-- left panel ends here -->
<!-- right panel starts here -->
<div class='content-right-panel'>
<h2>Designation (Delete Module)</h2>
<br>
<form method='post' action='/styletwo/DeleteDesignation.jsp' onsubmit='return validateForm(this)'>
<input type='hidden' id='code' name='code' value='<jsp:getProperty name='designationBean' property='code' />'>
Designation
<input readonly type='text' id='title' name='title' maxlength='35' size='36' value='<jsp:getProperty name='designationBean' property='title' />'>
<span class='error' id='titleErrorSection'>
<jsp:getProperty name='errorBean' property='error' />
</span><br>
Are you sure you want to delete?
<br><br>
<table>
<tr>
<td>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
</td>
<td>
<button type='submit'>Delete</button>
</form>
</td>
<td>
<button type='button' onclick='cancelDeletion()'>Cancel</button>
</td>
</tr>
</table>
</div><!-- right panel ends here -->
</div><!-- content-section ends here -->
<!-- footer starts here -->
<div class='footer'>
&copy; Thinking Machines 2025
</div> <!-- footer ends here -->
</div> <!-- Main container ends here -->
<form action='/styleone/Designations.jsp' id='cancelDeletionForm'>
</form>
</body>
</html>