<%@taglib uri='WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<tm:Module name='EMPLOYEE' />
<jsp:include page='MasterPageTopSection.jsp' />
<script src='/stylethree/js/Employees.js'></script>
<link rel='stylesheet' type='text/css' href='/stylethree/css/employees.css'>
<h2>Employees</h2>
<div class='employeeGrid'> 
<!-- Employees div starts here -->
<table border='5' style="width:100%" id='employeesGridTable'>
<thead>
<tr>
<th colspan='6' class='employeeGridHeader'>
<a href='/stylethree/EmployeeAddForm.jsp'>Add Employee</a> 
</th>
</tr>
<tr>
<th class='employeeGridSNOColumnTitle'>S.No.</th>
<th class='employeeGridIdColumnTitle'>Id</th>
<th class='employeeGridNameColumnTitle'>Name</th>
<th class='employeeGridDesignationColumnTitle'>Designation</th>
<th class='employeeGridEditColumnTitle'>Edit</th>
<th class='employeeGridDeleteColumnTitle'>Delete</th>
</tr>
</thead>
<tbody>
<tr style='cursor:pointer'>
<td style='text-align:right' placeHolderId='serialNumber'></td>
<td placeHolderId='employeeId'></td>
<td placeHolderId='name'></td>
<td placeHolderId='title'></td>
<td placeHolderId='editOption'></td>
<td placeHolderId='deleteOption'></td>
</tr>

</tbody>
</table>
</div> <!-- Employees div ends here -->
<div style='height:24vh;margin-left:5px;margin-right:5px;margin-bottom:5px;margin-top:5px;padding:5px;border:1px solid black'> 
<!-- Employee details div starts here -->
<label style='background:gray;color:white;padding-left:5px;padding-right:5px;'>Details</label><br><br>
<table border='1' style='width:99%;'>
<tr>
<td style='width:33%'>Employee Id: <span id='detailPanel_employeeId'></span></td>
<td style='width:33%'>Name: <span id='detailPanel_name'></span></td>
<td style='width:33%'>Designation: <span id='detailPanel_designation'></span></td>
</tr>
<tr>
<td>Date of Birth: <span id='detailPanel_dateOfBirth'></span></td>
<td>Gender: <span id='detailPanel_gender'></span></td>
<td>Is Indian: <span id='detailPanel_isIndian'></span></td>
</tr>
<tr>
<td>Basic Salary: <span id='detailPanel_basicSalary'></span></td>
<td>PAN number: <span id='detailPanel_panNumber'></span></td>
<td>Aadhar Card Number: <span id='detailPanel_aadharCardNumber'></span></td>
</tr>
</table>
</div> <!-- Employee details div ends here -->
<jsp:include page='MasterPageBottomSection.jsp' />