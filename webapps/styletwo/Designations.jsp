<%@taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<!DOCTYPE HTML>
<html lang = 'en'>
<head>
<title>HR Application</title>
</head>
<body>
<!-- Main container starts here -->
<div style='width:90hw;height:auto;border:1px solid black'>
<!-- Header container starts here -->
<div style='margin:5px;width:90hw;height:auto;border:1px solid black'>
<a href='/styletwo/index.html'><img src='/styletwo/images/logo.png' style='float:left;width:35px;height:auto;padding:5px'></a><div style='margin-top:9px;margin-bottom:9px;font-size:20pt'>Thinking Machines</div>
</div><!-- Header container ends here -->
<!-- content-section starts here -->
<div style='width:90hw;height:70vh;margin:5px;border:1px solid white'>
<!-- left panel starts here -->
<div style='height:65vh;margin:5px;float:left;padding:5px;border:1px solid black'>
Designations<br>
<a href='/styletwo/Employees.jsp'>Employees</a><br><br>
<a href='/styletwo/index.html'>Home</a>
</div><!-- left panel ends here -->
<!-- right panel starts here -->
<div style='height:65vh;margin-left:105px;margin-right:5px;margin-bottom:5px;margin-top:5px;padding:5px;border:1px solid black;overflow:auto'>
<table border='5'>
<thead>
<tr>
<th colspan='4' style='text-align:right;padding: 5px'>
<a href='/styletwo/DesignationAddForm.jsp'>Add new designation</a>
</th>
</tr>
<tr>
<th style='width:60px;text-align:center'>S.No.</th>
<th style='width:200px;text-align:center'>Designation</th>
<th style='width:80px;text-align:center'>Edit</th>
<th style='width:80px;text-align:center'>Delete</th>
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
<div style='width:90hw;height:auto;margin:5px;text-align:center;border:1px solid footer'>
&copy; Thinking Machines 2025
</div> <!-- footer ends here -->
</div><!-- Main container ends here -->
</body>
</html>
