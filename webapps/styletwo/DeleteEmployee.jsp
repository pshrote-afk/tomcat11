<%@taglib uri='/WEB-INF/mytags/tmtags.tld' prefix='tm' %>
<tm:Guard>
<jsp:forward page='/LoginForm.jsp'/>
</tm:Guard>
<tm:Module name='EMPLOYEE'></tm:Module>
<jsp:useBean id='employeeBean' scope='request' class='com.thinking.machines.hr.beans.EmployeeBean' />
<jsp:setProperty name='employeeBean' property='employeeId' />
<jsp:forward page='/deleteEmployee' />