<%@taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<!DOCTYPE HTML>
<html lang = 'en'>
<head>
<title>HR Application</title>
<link rel='stylesheet' text='html/css' href='/styletwo/css/styles.css'></link>
</head>
<body>
<!-- Main container starts here -->
<div class='main-container'>
<!-- Header container starts here -->
<div class='header-container'>
<a href='/styletwo/index.html'><img src='/styletwo/images/logo.png' class='logo'></a><div class='brand-name'>Thinking Machines</div>
</div><!-- Header container ends here -->
<!-- content-section starts here -->
<div class='content'>
<!-- left panel starts here -->
<div class='content-left-panel'>
Designations<br>
<a href='/styletwo/Employees.jsp'>Employees</a><br><br>
<a href='/styletwo/index.html'>Home</a>
</div><!-- left panel ends here -->
<!-- right panel starts here -->
<div class='content-right-panel'>
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
</div><!-- right panel ends here -->
</div><!-- content-section ends here -->
<!-- footer starts here -->
<div class='footer'>
&copy; Thinking Machines 2025
</div> <!-- footer ends here -->
</div><!-- Main container ends here -->
</body>
</html>
