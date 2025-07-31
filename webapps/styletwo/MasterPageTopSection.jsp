<%@taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<tm:Set HOME='1'></tm:Set>
<tm:Set DESIGNATION='2'></tm:Set>
<tm:Set EMPLOYEE='3'></tm:Set>

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

<tm:If condition='${module==DESIGNATION}'>
<b>Designations</b><br>
</tm:If>
<tm:If condition='${module!=DESIGNATION}'>
<a href='/styletwo/Designations.jsp'>Designations</a><br>
</tm:If>

<tm:If condition='${module==EMPLOYEE}'>
<b>Employees</b><br><br>
</tm:If>
<tm:If condition='${module!=EMPLOYEE}'>
<a href='/styletwo/Employees.jsp'>Employees</a><br><br>
</tm:If>

<tm:If condition='${module!=HOME}'>
<a href='/styletwo/index.jsp'>Home</a><br>
</tm:If>

</div><!-- left panel ends here -->

<div class='content-right-panel'><!-- right panel starts here -->
