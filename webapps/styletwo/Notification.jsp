<%@taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %> 
<jsp:useBean id='messageBean' scope='request' class='com.thinking.machines.hr.beans.MessageBean' />

<!DOCTYPE HTML>
<html lang = 'en'>
<head>
<title>HR Application</title>
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
<h2>${messageBean.heading}</h2>
${messageBean.message}
<br>

<!-- what if designer could write code -->

<tm:If condition='${messageBean.generateButtons}'>

<table>
<tr>
<td>
<form action='/styletwo/${messageBean.buttonOneAction}'>
<button type='submit'>${messageBean.buttonOneText}</button>
</form>
</td>

<tm:If condition='${messageBean.generateTwoButtons}'>

<td>
<form action='/styletwo/${messageBean.buttonTwoAction}'>
<button type='submit'>${messageBean.buttonTwoText}</button>
</form>
</td>

</tm:If>

</tr>
</table>

</tm:If>

</div><!-- right panel ends here -->
</div><!-- content-section ends here -->
<!-- footer starts here -->
<div class='footer'>
&copy; Thinking Machines 2025
</div> <!-- footer ends here -->
</div> <!-- Main container ends here -->
</body>
</html>